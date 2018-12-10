package com.divae.hazelcast.crawlingagent.services;

import com.divae.hazelcast.crawlingagent.messaging.HazelcastSendingService;
import com.divae.hazelcast.crawlingagent.messaging.WeburlRequestEvent;
import com.divae.hazelcast.crawlingagent.messaging.WeburlResponseEvent;
import org.springframework.stereotype.Service;

@Service
public class WebcrawlingService {

    private final HazelcastSendingService hazelcastSendingService;

    public WebcrawlingService(HazelcastSendingService hazelcastSendingService) {
        this.hazelcastSendingService = hazelcastSendingService;
    }


    public void handleWeburlRequestEvent(final WeburlRequestEvent event) {
        final WeburlResponseEvent responseEvent = new WeburlResponseEvent();
        responseEvent.setContent("This is the content for the url: " + event.getUrl());
        responseEvent.setTraceId(event.getTraceId());
        responseEvent.setUrl(event.getUrl());

        hazelcastSendingService.sendWeburlResponseEvent(responseEvent);
    }
}
