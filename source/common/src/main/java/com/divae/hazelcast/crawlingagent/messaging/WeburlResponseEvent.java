package com.divae.hazelcast.crawlingagent.messaging;

import java.io.Serializable;

public class WeburlResponseEvent implements Serializable {


    private static final long serialVersionUID = 3738646119086300829L;
    private String traceId;
    private String url;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WeburlResponseEvent{" +
                "traceId='" + traceId + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
