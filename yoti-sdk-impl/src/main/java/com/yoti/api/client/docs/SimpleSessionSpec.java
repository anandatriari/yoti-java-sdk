package com.yoti.api.client.docs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleSessionSpec implements SessionSpec {

    @JsonProperty("client_session_token_ttl")
    private final int clientSessionTokenTtl;

    @JsonProperty("resources_ttl")
    private final int resourcesTtl;

    @JsonProperty("user_tracking_id")
    private final String userTrackingId;

    @JsonProperty("notifications")
    private final NotificationConfig notifications;

    @JsonProperty("requested_checks")
    private final List<RequestedCheck> requestedChecks;

    @JsonProperty("requested_tasks")
    private final List<RequestedTask> requestedTasks;

    @JsonProperty("sdk_config")
    private final SdkConfig sdkConfig;

    public SimpleSessionSpec(int clientSessionTokenTtl,
            int resourcesTtl,
            String userTrackingId,
            NotificationConfig notifications,
            List<RequestedCheck> requestedChecks, List<RequestedTask> requestedTasks, SdkConfig sdkConfig) {
        this.clientSessionTokenTtl = clientSessionTokenTtl;
        this.resourcesTtl = resourcesTtl;
        this.userTrackingId = userTrackingId;
        this.notifications = notifications;
        this.requestedChecks = requestedChecks;
        this.requestedTasks = requestedTasks;
        this.sdkConfig = sdkConfig;
    }

    @Override
    public int getClientSessionTokenTtl() {
        return clientSessionTokenTtl;
    }

    @Override
    public int getResourcesTtl() {
        return resourcesTtl;
    }

    @Override
    public String getUserTrackingId() {
        return userTrackingId;
    }

    @Override
    public NotificationConfig getNotifications() {
        return notifications;
    }

    @Override
    public List<RequestedCheck> getRequestedChecks() {
        return requestedChecks;
    }

    @Override
    public List<RequestedTask> getRequestedTasks() {
        return requestedTasks;
    }

    @Override
    public SdkConfig getSdkConfig() {
        return sdkConfig;
    }

}
