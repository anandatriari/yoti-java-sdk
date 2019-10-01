package com.yoti.api.client.docs;

import java.util.ArrayList;
import java.util.List;

public class SimpleNotificationConfigBuilder extends NotificationConfigBuilder {

    private String authToken;
    private String endpoint;
    private final List<String> topics = new ArrayList<>();

    @Override
    public NotificationConfigBuilder withAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    @Override
    public NotificationConfigBuilder withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    @Override
    public NotificationConfigBuilder forResourceUpdate() {
        return withTopic(Constants.RESOURCE_UPDATE);
    }

    @Override
    public NotificationConfigBuilder forTaskCompletion() {
        return withTopic(Constants.TASK_COMPLETION);
    }

    @Override
    public NotificationConfigBuilder forCheckCompletion() {
        return withTopic(Constants.CHECK_COMPLETION);
    }

    @Override
    public NotificationConfigBuilder forSessionCompletion() {
        return withTopic(Constants.SESSION_COMPLETION);
    }

    @Override
    public NotificationConfigBuilder withTopic(String topicName) {
        this.topics.add(topicName);
        return this;
    }

    @Override
    public NotificationConfig build() {
        return new SimpleNotificationConfig(authToken, endpoint, topics);
    }

}
