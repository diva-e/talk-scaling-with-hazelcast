package com.divae.hazelcast.crawlingagent.messaging;

import com.divae.hazelcast.crawlingagent.services.WebcrawlingService;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HazelcastEventListener implements ItemListener<WeburlRequestEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(HazelcastEventListener.class);


    private final IQueue<WeburlRequestEvent> iQueue;
    private final WebcrawlingService webcrawlingService;

    public HazelcastEventListener(final HazelcastInstance hazelcastInstance, WebcrawlingService webcrawlingService) {
        this.iQueue = hazelcastInstance.getQueue(WebQueues.QUEUE_NAME_REQUEST);
        this.webcrawlingService = webcrawlingService;
        this.iQueue.addItemListener(this, true);
    }

    @Override
    public void itemAdded(ItemEvent<WeburlRequestEvent> itemEvent) {
        try {
            final WeburlRequestEvent event = iQueue.take();
            LOG.info("handle event: {}", event);
            webcrawlingService.handleWeburlRequestEvent(event);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void itemRemoved(ItemEvent<WeburlRequestEvent> itemEvent) {
    }
}
