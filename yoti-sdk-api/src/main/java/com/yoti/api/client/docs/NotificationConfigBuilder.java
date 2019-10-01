package com.yoti.api.client.docs;

public abstract class NotificationConfigBuilder {

    public abstract NotificationConfigBuilder withAuthToken(String authToken);

    public abstract NotificationConfigBuilder withEndpoint(String endpoint);

    public abstract NotificationConfigBuilder forResourceUpdate();

    public abstract NotificationConfigBuilder forTaskCompletion();

    public abstract NotificationConfigBuilder forCheckCompletion();

    public abstract NotificationConfigBuilder forSessionCompletion();

    public abstract NotificationConfigBuilder withTopic(String topicName);

    public abstract NotificationConfig build();

}
