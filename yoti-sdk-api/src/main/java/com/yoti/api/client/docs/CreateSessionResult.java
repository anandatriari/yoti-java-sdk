package com.yoti.api.client.docs;

public interface CreateSessionResult {

    int getClientSessionTokenTtl();

    String getClientSessionToken();

    String getSessionId();

}
