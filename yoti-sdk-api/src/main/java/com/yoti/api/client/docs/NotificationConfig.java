package com.yoti.api.client.docs;

import java.util.List;

public interface NotificationConfig {

    String getAuthToken();

    String getEndpoint();

    List<String> getTopics();

}
