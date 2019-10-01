package com.yoti.api.client.docs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(@JsonSubTypes.Type(value = SimpleRequestedTextExtractionTask.class, name = Constants.ID_DOCUMENT_TEXT_DATA_EXTRACTION))
public abstract class SimpleRequestedTask<T extends RequestedTaskConfig> {

    @JsonProperty("config")
    public abstract T getConfig();

}
