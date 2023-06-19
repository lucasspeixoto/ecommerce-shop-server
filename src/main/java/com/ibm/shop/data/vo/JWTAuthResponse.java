package com.ibm.shop.data.vo;

import java.util.Objects;

public class JWTAuthResponse {

    private String accessToken;
    private String tokenType = "Bearer";

    public JWTAuthResponse() {
    }

    public JWTAuthResponse(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JWTAuthResponse that)) return false;
        return Objects.equals(getAccessToken(), that.getAccessToken()) && Objects.equals(getTokenType(), that.getTokenType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccessToken(), getTokenType());
    }
}
