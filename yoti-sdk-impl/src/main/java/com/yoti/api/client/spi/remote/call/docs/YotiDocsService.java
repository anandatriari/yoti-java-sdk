package com.yoti.api.client.spi.remote.call.docs;

import static com.yoti.api.client.spi.remote.call.HttpMethod.HTTP_POST;
import static com.yoti.api.client.spi.remote.call.YotiConstants.DEFAULT_CHARSET;
import static com.yoti.api.client.spi.remote.call.YotiConstants.DEFAULT_YOTI_DOCS_URL;
import static com.yoti.api.client.spi.remote.call.YotiConstants.PROPERTY_YOTI_DOCS_URL;
import static com.yoti.api.client.spi.remote.util.Validation.notNull;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.util.Map;

import com.yoti.api.client.docs.CreateSessionResult;
import com.yoti.api.client.docs.SessionSpec;
import com.yoti.api.client.docs.YotiDocsException;
import com.yoti.api.client.spi.remote.call.JsonResourceFetcher;
import com.yoti.api.client.spi.remote.call.ResourceException;
import com.yoti.api.client.spi.remote.call.ResourceFetcher;
import com.yoti.api.client.spi.remote.call.UrlConnector;
import com.yoti.api.client.spi.remote.call.factory.HeadersFactory;
import com.yoti.api.client.spi.remote.call.factory.PathFactory;
import com.yoti.api.client.spi.remote.call.factory.SignedMessageFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class YotiDocsService {

    public static YotiDocsService newInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        return new YotiDocsService(new PathFactory(),
                objectMapper,
                SignedMessageFactory.newInstance(),
                new HeadersFactory(),
                JsonResourceFetcher.newInstance());
    }

    private static final Logger LOG = LoggerFactory.getLogger(YotiDocsService.class);

    private final PathFactory pathFactory;
    private final ObjectMapper objectMapper;
    private final SignedMessageFactory signedMessageFactory;
    private final HeadersFactory headersFactory;
    private final ResourceFetcher resourceFetcher;

    private final String apiUrl;

    private YotiDocsService(PathFactory pathFactory,
            ObjectMapper objectMapper,
            SignedMessageFactory signedMessageFactory,
            HeadersFactory headersFactory,
            ResourceFetcher resourceFetcher) {
        this.pathFactory = pathFactory;
        this.objectMapper = objectMapper;
        this.signedMessageFactory = signedMessageFactory;
        this.headersFactory = headersFactory;
        this.resourceFetcher = resourceFetcher;

        apiUrl = System.getProperty(PROPERTY_YOTI_DOCS_URL, DEFAULT_YOTI_DOCS_URL);
    }

    public CreateSessionResult createSession(String appId, KeyPair keyPair, SessionSpec sessionSpec) throws YotiDocsException {
        notNull(appId, "Application id");
        notNull(keyPair, "Application key Pair");
        notNull(sessionSpec, "sessionSpec");

        String path = pathFactory.createNewYotiDocsSessionPath(appId);
        LOG.info("Requesting Dynamic QR Code at {}", path);

        try {
            byte[] body = objectMapper.writeValueAsString(sessionSpec).getBytes(DEFAULT_CHARSET);
            String digest = signedMessageFactory.create(keyPair.getPrivate(), HTTP_POST, path, body);
            Map<String, String> headers = headersFactory.create(digest);
            UrlConnector urlConnector = UrlConnector.get(apiUrl + path);
            return resourceFetcher.postResource(urlConnector, body, headers, CreateSessionResult.class);
        } catch (GeneralSecurityException ex) {
            throw new YotiDocsException("Error signing the request: " + ex.getMessage(), ex);
        } catch (ResourceException ex) {
            throw new YotiDocsException("Error posting the request: " + ex.getMessage(), ex);
        } catch (IOException ex) {
            throw new YotiDocsException("Error building the request: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new YotiDocsException("Error initiating the share: " + ex.getMessage(), ex);
        }
    }

}
