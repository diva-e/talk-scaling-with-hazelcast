package com.divae.hazelcast.scaleout.messaging;

import com.divae.hazelcast.crawlingagent.messaging.WeburlResponseEvent;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HazelcastEventListener implements ItemListener<WeburlResponseEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(HazelcastEventListener.class);


    private final IQueue<WeburlResponseEvent> iQueue;

    public HazelcastEventListener(final HazelcastInstance hazelcastInstance) {
        this.iQueue = hazelcastInstance.getQueue("webresponse");
        this.iQueue.addItemListener(this, true);
    }

    @Override
    public void itemAdded(ItemEvent<WeburlResponseEvent> itemEvent) {

        try {
            final WeburlResponseEvent event = iQueue.take();
            LOG.info("content: {}", event.getContent());
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }

    }

    @Override
    public void itemRemoved(ItemEvent<WeburlResponseEvent> itemEvent) {

    }
}
