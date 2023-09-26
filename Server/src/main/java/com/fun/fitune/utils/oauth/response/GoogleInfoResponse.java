package com.fun.fitune.utils.oauth.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fun.fitune.utils.oauth.enums.OAuthProvider;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleInfoResponse implements OAuthInfoResponse{
    @JsonProperty("google_account")
    private GoogleAccount googleAccount;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class GoogleAccount {
        private GoogleProfile profile;
        private String email;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class GoogleProfile {
        private String nickname;
    }

    @Override
    public String getEmail() {
        return googleAccount.email;
    }

    @Override
    public String getNickname() {
        return googleAccount.profile.nickname;
    }

    @Override
    public OAuthProvider getOAuthProvider() {
        return OAuthProvider.GOOGLE;
    }
}
