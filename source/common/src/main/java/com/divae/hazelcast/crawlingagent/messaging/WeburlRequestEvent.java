package com.divae.hazelcast.crawlingagent.messaging;

import java.io.Serializable;

public class WeburlRequestEvent implements Serializable {

    private static final long serialVersionUID = -6246473785134398765L;

    private String traceId;
    private String url;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WeburlRequestEvent{" +
                "traceId='" + traceId + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
