package com.yoti.api.client.docs;

import java.util.List;

public abstract class SessionSpecBuilder {

    public abstract SessionSpecBuilder withClientSessionTokenTtl(int clientSessionTokenTtl);

    public abstract SessionSpecBuilder withResourcesTtl(int resourcesTtl);

    public abstract SessionSpecBuilder withUserTrackingId(String userTrackingId);

    public abstract SessionSpecBuilder withNotifications(NotificationConfig notifications);

    public abstract SessionSpecBuilder withRequestedCheck(RequestedCheck requestedCheck);

    public abstract SessionSpecBuilder withRequestedTask(RequestedTask requestedTasks);

    public abstract SessionSpecBuilder withSdkConfig(SdkConfig sdkConfig);

    public abstract SessionSpec build();

}
