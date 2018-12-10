package com.divae.hazelcast.scaleout.messaging;

import com.divae.hazelcast.crawlingagent.messaging.WebQueues;
import com.divae.hazelcast.crawlingagent.messaging.WeburlRequestEvent;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HazelcastSendingService {

    private static final Logger LOG = LoggerFactory.getLogger(HazelcastSendingService.class);


    private final HazelcastInstance hazelcastInstance;

    public HazelcastSendingService(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public void sendWeburlRequestEvent(final WeburlRequestEvent responseEvent) {
        hazelcastInstance.getQueue(WebQueues.QUEUE_NAME_REQUEST).offer(responseEvent);
    }
}
