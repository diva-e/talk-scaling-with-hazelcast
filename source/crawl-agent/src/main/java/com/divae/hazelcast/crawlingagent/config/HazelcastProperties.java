package com.divae.hazelcast.crawlingagent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HazelcastProperties {

    private final String groupName;
    private final Integer port;
    private final List<String> members;

    public HazelcastProperties(
            @Value("${hazelcast.groupName}") String groupName,
            @Value("${hazelcast.port}") Integer port,
            @Value("${hazelcast.members}") List<String> members) {
        this.groupName = groupName;
        this.port = port;
        this.members = members;
    }

    public String getGroupName() {
        return groupName;
    }

    public Integer getPort() {
        return port;
    }

    public List<String> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "HazelcastProperties{" +
                "groupName='" + groupName + '\'' +
                ", port=" + port +
                ", members=" + members +
                '}';
    }
}
