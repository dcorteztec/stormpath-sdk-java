/*
 * Copyright 2014 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stormpath.sdk.impl.provider;

import com.stormpath.sdk.lang.Assert;
import com.stormpath.sdk.lang.Strings;
import com.stormpath.sdk.provider.CreateProviderRequest;
import com.stormpath.sdk.provider.GoogleCreateProviderRequestBuilder;

import java.util.Map;

/**
 * @since 1.0.beta
 */
public class DefaultGoogleCreateProviderRequestBuilder extends AbstractCreateProviderRequestBuilder<GoogleCreateProviderRequestBuilder> implements GoogleCreateProviderRequestBuilder {

    private String redirectUri;

    @Override
    public GoogleCreateProviderRequestBuilder setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    @Override
    protected String getConcreteProviderId() {
        return IdentityProviderType.GOOGLE.getNameKey();
    }

    @Override
    protected CreateProviderRequest doBuild(Map<String, Object> map) {
        Assert.state(Strings.hasText(this.redirectUri), "redirectUri is a required property. It must be provided before building.");

        DefaultGoogleProvider provider = new DefaultGoogleProvider(null, map);
        provider.setClientId(super.clientId);
        provider.setClientSecret(super.clientSecret);
        provider.setRedirectUri(this.redirectUri);
        if (super.userInfoMappingRules != null) {
            provider.setUserInfoMappingRules(super.userInfoMappingRules);
        }
        return new DefaultCreateProviderRequest(provider);
    }

}
