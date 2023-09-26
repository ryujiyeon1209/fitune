package com.fun.fitune.utils.oauth.service;

import com.fun.fitune.utils.oauth.enums.OAuthProvider;
import com.fun.fitune.utils.oauth.request.OAuthLoginParams;
import com.fun.fitune.utils.oauth.response.OAuthInfoResponse;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}
