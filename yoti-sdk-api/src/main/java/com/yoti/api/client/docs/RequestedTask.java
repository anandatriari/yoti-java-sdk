package com.yoti.api.client.docs;

public interface RequestedTask<T extends RequestedTaskConfig> {

    T getConfig();

}
