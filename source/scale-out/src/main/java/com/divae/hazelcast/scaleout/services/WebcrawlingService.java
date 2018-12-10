package com.divae.hazelcast.scaleout.services;

import com.divae.hazelcast.crawlingagent.messaging.WeburlRequestEvent;
import com.divae.hazelcast.scaleout.messaging.HazelcastSendingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WebcrawlingService {

    private static final Logger LOG = LoggerFactory.getLogger(WebcrawlingService.class);


    private final HazelcastSendingService hazelcastSendingService;

    public WebcrawlingService(HazelcastSendingService hazelcastSendingService) {
        this.hazelcastSendingService = hazelcastSendingService;
    }


    public void addCrawlingUrl(final String url){

        final WeburlRequestEvent requestEvent = new WeburlRequestEvent();
        requestEvent.setTraceId(UUID.randomUUID().toString());
        requestEvent.setUrl(url);

        LOG.info("send new request event: {}", requestEvent);


        hazelcastSendingService.sendWeburlRequestEvent(requestEvent);

    }
}
