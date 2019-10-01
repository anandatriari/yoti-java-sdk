package com.yoti.api.client.docs;

import java.util.Collections;
import java.util.List;

public interface SessionSpec {

    int getClientSessionTokenTtl();
    int getResourcesTtl();
    String getUserTrackingId();
    NotificationConfig getNotifications();
    List<RequestedCheck> getRequestedChecks();
    List<RequestedTask> getRequestedTasks();
    SdkConfig getSdkConfig();

}
