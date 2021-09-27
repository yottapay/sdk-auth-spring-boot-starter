package com.yotta.sdk.auth.spring.config;

import com.yotta.sdk.auth.YpSdkAuth;
import com.yotta.sdk.auth.config.YpSdkAuthConfiguration;
import com.yotta.sdk.auth.spring.properties.YpSdkAuthSpringProperties;
import com.yotta.sdk.core.config.YpSdkConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({YpSdkAuth.class, YpSdkAuthConfiguration.class})
@EnableConfigurationProperties(YpSdkAuthSpringProperties.class)
public class YpSdkAuthSpringConfig {

    @Bean
    @ConditionalOnMissingBean(YpSdkConfiguration.class)
    public YpSdkAuthConfiguration getYottaSdkAuthConfiguration(YpSdkAuthSpringProperties properties) {
        YpSdkAuthConfiguration config = YpSdkAuthConfiguration.createDefault();

        configure(config, properties);
        configureCredentials(config, properties.getCredentials());
        configureEndpoints(config, properties.getEndpoints());

        return config;
    }

    protected void configure(YpSdkAuthConfiguration configuration, YpSdkAuthSpringProperties properties) {
        configuration.setServerBaseUrl(properties.getBaseUrl());
    }

    protected void configureCredentials(YpSdkAuthConfiguration configuration,
                                        YpSdkAuthSpringProperties.Credentials credentials) {
        configuration.setUsername(credentials.getUsername());
        configuration.setPassword(credentials.getPassword());
    }

    protected void configureEndpoints(YpSdkAuthConfiguration configuration,
                                      YpSdkAuthSpringProperties.Endpoints endpoints) {
        configuration.setCreateAuthEndpoint(endpoints.getCreateAuth());
        configuration.setCheckConsentEndpoint(endpoints.getCheckConsent());
        configuration.setExchangeTokenEndpoint(endpoints.getExchangeToken());
        configuration.setFullNameEndpoint(endpoints.getFullName());
        configuration.setEmailEndpoint(endpoints.getEmail());
        configuration.setPhoneNumberEndpoint(endpoints.getPhoneNumber());
        configuration.setDeliveryAddressEndpoint(endpoints.getDeliveryAddress());
    }

    @Bean
    @ConditionalOnMissingBean(YpSdkAuth.class)
    public YpSdkAuth getYottaSdkAuth(YpSdkAuthConfiguration config) {
        return YpSdkAuth.createFromConfiguration(config);
    }
}
