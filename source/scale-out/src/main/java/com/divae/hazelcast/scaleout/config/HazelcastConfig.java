package com.divae.hazelcast.scaleout.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HazelcastConfig {

    private static final Logger LOG = LoggerFactory.getLogger(HazelcastConfig.class);

    @Bean
    HazelcastInstance hazelcastInstance(Config hazelCastConfig) {
        final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(hazelCastConfig);
        return hazelcastInstance;
    }


    @Bean
    Config hazelCastConfig(HazelcastProperties hazelcastProperties) {

        LOG.info("Configuring Hazelcast with the following Properties: {}", hazelcastProperties.toString());

        Config config = new Config();
        config.getGroupConfig().setName(hazelcastProperties.getGroupName());
        configureNetwork(hazelcastProperties, config);
        config.setProperty("hazelcast.logging.type", "slf4j");
        config.setProperty("hazelcast.shutdownhook.enabled", "false");

        return config;
    }


    private void configureNetwork(HazelcastProperties hazelcastProperties, Config config) {
        NetworkConfig network = config.getNetworkConfig();
        network.setPort(hazelcastProperties.getPort());
        network.setPortAutoIncrement(false);
        JoinConfig join = network.getJoin();
        join.getAwsConfig().setEnabled(false);
        final List<String> members = hazelcastProperties.getMembers();
        if (members.isEmpty() || members.get(0).equals("")) {
            join.getTcpIpConfig().setEnabled(false);
            join.getMulticastConfig().setEnabled(true);
        } else {
            join.getMulticastConfig().setEnabled(false);
            join.getTcpIpConfig().setEnabled(true);
            for (final String member : members) {
                join.getTcpIpConfig().addMember(member);
            }

        }
    }

}
