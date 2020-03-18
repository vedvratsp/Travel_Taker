package com.example.travel_taker.Login.Twitter.network.models;

import com.google.gson.annotations.SerializedName;

public class Oauth2Token {
    @SerializedName("access_token")
    private final String accessToken;

    public Oauth2Token(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
