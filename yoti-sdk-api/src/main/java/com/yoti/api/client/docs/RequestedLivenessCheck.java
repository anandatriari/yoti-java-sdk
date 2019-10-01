package com.yoti.api.client.docs;

public interface RequestedLivenessCheck extends RequestedCheck<RequestedLivenessConfig> {

    @Override
    RequestedLivenessConfig getConfig();

}
