package com.yoti.api.client;

import java.io.IOException;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Build a YotiClient for an application and its corresponding key pair. You
 * only need to provide your application id (provided by Yoti) and your
 * public-private key pair (generated by you or your browser).
 *
 */
final class ServiceLocatorYotiClientBuilder extends YotiClientBuilder implements YotiClientConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceLocatorYotiClientBuilder.class);
    private KeyPairSource keyPairSource;
    private String applicationId;

    @Override
    public YotiClientBuilder forApplication(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    @Override
    public YotiClientBuilder withKeyPair(KeyPairSource keyPairSource) throws IOException {
        this.keyPairSource = keyPairSource;
        return this;
    }

    @Override
    public YotiClient build() throws InitialisationException {
        checkBuilderState();

        ServiceLoader<YotiClientFactory> loader = ServiceLoader.load(YotiClientFactory.class);
        YotiClient result = null;
        for (YotiClientFactory client : loader) {
            if (client.accepts(applicationId)) {
                result = client.getInstance(this);
            }
        }
        if (result == null) {
            throw new IllegalStateException("No available clients found for application " + applicationId);
        } else {
            LOG.debug("Found Yoti client: {}", result.getClass().getName());
        }
        return result;
    }

    @Override
    public KeyPairSource getKeyPairSource() {
        return keyPairSource;
    }

    @Override
    public String getApplicationId() {
        return applicationId;
    }

    private void checkBuilderState() {
        if (keyPairSource == null) {
            throw new IllegalStateException("No key pair supplied");
        }
        if (applicationId == null) {
            throw new IllegalStateException("No application id supplied");
        }
    }
}
