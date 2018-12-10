package com.divae.hazelcast.crawlingagent.messaging;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.stereotype.Service;

@Service
public class HazelcastSendingService {

    private final HazelcastInstance hazelcastInstance;

    public HazelcastSendingService(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public void sendWeburlResponseEvent(final WeburlResponseEvent responseEvent) {

        hazelcastInstance.getQueue("webresponse").offer(responseEvent);

    }
}
