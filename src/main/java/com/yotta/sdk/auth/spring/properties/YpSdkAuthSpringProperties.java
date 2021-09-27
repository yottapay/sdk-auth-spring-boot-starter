package com.yotta.sdk.auth.spring.properties;

import com.yotta.sdk.auth.config.YpSdkAuthConfiguration.AuthProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "yotta-pay.sdk.auth")
public class YpSdkAuthSpringProperties {

    private String baseUrl = AuthProperties.SERVER_BASE_URL.getDefaultValue();

    private final Credentials credentials = new Credentials();
    private final Endpoints endpoints = new Endpoints();

    @Data
    public static class Credentials {
        private String username;

        private String password;
    }

    @Data
    public static class Endpoints {
        private String createAuth = AuthProperties.ENDPOINT_CREATE_AUTH.getDefaultValue();
        private String exchangeToken = AuthProperties.ENDPOINT_EXCHANGE_TOKEN.getDefaultValue();
        private String checkConsent = AuthProperties.ENDPOINT_CHECK_CONSENT.getDefaultValue();
        private String FullName = AuthProperties.ENDPOINT_GET_FULL_NAME.getDefaultValue();
        private String Email = AuthProperties.ENDPOINT_GET_EMAIL.getDefaultValue();
        private String PhoneNumber = AuthProperties.ENDPOINT_GET_PHONE_NUMBER.getDefaultValue();
        private String DeliveryAddress = AuthProperties.ENDPOINT_GET_DELIVERY_ADDRESS.getDefaultValue();
    }
}
