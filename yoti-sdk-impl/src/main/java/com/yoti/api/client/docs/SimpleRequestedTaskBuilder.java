package com.yoti.api.client.docs;

public class SimpleRequestedTaskBuilder extends RequestedTaskBuilder {

    @Override
    public RequestedTextExtractionTaskBuilder forTextExtractionTask() {
        return new SimpleRequestedTextExtractionTaskBuilder();
    }

}
