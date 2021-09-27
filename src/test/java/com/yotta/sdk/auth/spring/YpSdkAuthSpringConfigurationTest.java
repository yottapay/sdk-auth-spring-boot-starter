package com.yotta.sdk.auth.spring;

import com.yotta.sdk.auth.YpSdkAuth;
import com.yotta.sdk.auth.config.YpSdkAuthConfiguration;
import com.yotta.sdk.auth.domain.YpAuthCreateAuthorization;
import com.yotta.sdk.auth.domain.YpAuthCreateAuthorizationResult;
import com.yotta.sdk.auth.domain.YpAuthUserScope;
import com.yotta.sdk.auth.spring.config.YpSdkAuthSpringConfig;
import com.yotta.sdk.auth.spring.properties.YpSdkAuthSpringProperties;
import com.yotta.sdk.core.exception.YpRequiredPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("yp-sdk-auth-test")
@EnableAutoConfiguration
@Slf4j
public class YpSdkAuthSpringConfigurationTest {

    @Autowired
    private YpSdkAuthSpringProperties properties;

    @Test
    public void test_propertiesLoadedToContext() {
        assertNotNull("Properties are not loaded to context", properties);
    }

    @Test
    public void test_configuredPropertiesRoot() {
        assertEquals("localhost:8080", properties.getBaseUrl());
    }

    @Test
    public void test_configuredPropertiesCredentials() {
        YpSdkAuthSpringProperties.Credentials credentials = properties.getCredentials();

        assertEquals("username", credentials.getUsername());
        assertEquals("password", credentials.getPassword());
    }

    @Test
    public void test_configuredPropertiesEndpoints() {
        YpSdkAuthSpringProperties.Endpoints endpoints = properties.getEndpoints();
        assertEquals("/create-auth", endpoints.getCreateAuth());
        assertEquals("/exchange-token", endpoints.getExchangeToken());
        assertEquals("/check-consent", endpoints.getCheckConsent());
        assertEquals("/full-name", endpoints.getFullName());
        assertEquals("/email", endpoints.getEmail());
        assertEquals("/phone-number", endpoints.getPhoneNumber());
        assertEquals("/delivery-address", endpoints.getDeliveryAddress());
    }

    @Test
    public void test_configurationWithRequiredAndDefaultProperties() {
        YpSdkAuthSpringConfig configuration = new YpSdkAuthSpringConfig();
        YpSdkAuthSpringProperties properties = new YpSdkAuthSpringProperties();
        properties.getCredentials().setUsername("username");
        properties.getCredentials().setPassword("password");
        YpSdkAuthConfiguration sdkConfig = configuration.getYottaSdkAuthConfiguration(properties);
        assertNotNull(sdkConfig);
    }

    @Test
    public void test_customCreateFromProperties() {
        YpSdkAuthSpringConfig config = new YpSdkAuthSpringConfig();
        YpSdkAuthSpringProperties properties = new YpSdkAuthSpringProperties();
        properties.getCredentials().setUsername("username");
        properties.getCredentials().setPassword("password");
        YpSdkAuthConfiguration sdkConfig = config.getYottaSdkAuthConfiguration(properties);
        YpSdkAuth sdk = config.getYottaSdkAuth(sdkConfig);
        assertNotNull(sdk);
    }
}
