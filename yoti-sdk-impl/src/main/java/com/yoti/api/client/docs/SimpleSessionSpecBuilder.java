package com.yoti.api.client.docs;

import java.util.ArrayList;
import java.util.List;

public class SimpleSessionSpecBuilder extends SessionSpecBuilder {

    private int clientSessionTokenTtl;
    private int resourcesTtl;
    private String userTrackingId;
    private NotificationConfig notifications;
    private final List<RequestedCheck> requestedChecks = new ArrayList<>();
    private final List<RequestedTask> requestedTasks = new ArrayList<>();
    private SdkConfig sdkConfig;

    @Override
    public SessionSpecBuilder withClientSessionTokenTtl(int clientSessionTokenTtl) {
        this.clientSessionTokenTtl = clientSessionTokenTtl;
        return this;
    }

    @Override
    public SessionSpecBuilder withResourcesTtl(int resourcesTtl) {
        this.resourcesTtl = resourcesTtl;
        return this;
    }

    @Override
    public SessionSpecBuilder withUserTrackingId(String userTrackingId) {
        this.userTrackingId = userTrackingId;
        return this;
    }

    @Override
    public SessionSpecBuilder withNotifications(NotificationConfig notifications) {
        this.notifications = notifications;
        return this;
    }

    @Override
    public SessionSpecBuilder withRequestedCheck(RequestedCheck requestedCheck) {
        this.requestedChecks.add(requestedCheck);
        return this;
    }

    @Override
    public SessionSpecBuilder withRequestedTask(RequestedTask requestedTask) {
        this.requestedTasks.add(requestedTask);
        return this;
    }

    @Override
    public SessionSpecBuilder withSdkConfig(SdkConfig sdkConfig) {
        this.sdkConfig = sdkConfig;
        return this;
    }

    // FIXME: We need validation for ALL the builders

    @Override
    public SessionSpec build() {
        return new SimpleSessionSpec(clientSessionTokenTtl, resourcesTtl, userTrackingId, notifications, requestedChecks, requestedTasks, sdkConfig);
    }

}
