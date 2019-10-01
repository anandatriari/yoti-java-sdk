package com.yoti.api.client.spi.remote.call.factory;

import static java.lang.System.nanoTime;
import static java.util.UUID.randomUUID;

public class PathFactory {

    private static final String SIGNATURE_PARAMS = "nonce=%s&timestamp=%s";

    private UnsignedPathFactory unsignedPathFactory;

    public PathFactory() {
        this.unsignedPathFactory = new UnsignedPathFactory();
    }

    public String createSignatureParams() {
        return String.format(SIGNATURE_PARAMS, randomUUID(), createTimestamp());
    }

    public String createProfilePath(String appId, String connectToken) {
        return unsignedPathFactory.createProfilePath(appId, connectToken) + createSignatureParams();
    }

    public String createAmlPath(String appId) {
        return unsignedPathFactory.createAmlPath(appId) + createSignatureParams();
    }

    public String createDynamicSharingPath(String appId) {
        return unsignedPathFactory.createDynamicSharingPath(appId) + createSignatureParams();
    }

    protected long createTimestamp() {
        return nanoTime() / 1000;
    }

    public String createNewYotiDocsSessionPath(String appId) {
        return format(DOCS_SESSION_PATH_TEMPLATE, appId, randomUUID(), createTimestamp());
    }

}
