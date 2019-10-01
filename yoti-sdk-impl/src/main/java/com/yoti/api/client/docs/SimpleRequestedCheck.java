package com.yoti.api.client.docs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RequestedDocumentAuthenticityCheck.class, name = Constants.ID_DOCUMENT_AUTHENTICITY),
        @JsonSubTypes.Type(value = RequestedLivenessCheck.class, name = Constants.LIVENESS),
        @JsonSubTypes.Type(value = RequestedFaceMatchCheck.class, name = Constants.ID_DOCUMENT_FACE_MATCH)
})
public abstract class SimpleRequestedCheck<T extends RequestedCheckConfig> implements RequestedCheck {

    @JsonProperty("config")
    public abstract T getConfig();

}
