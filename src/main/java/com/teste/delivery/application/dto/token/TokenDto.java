package com.teste.delivery.application.dto.token;

public class TokenDto {

    private String type;
    private String token;
    private String expires;

    public TokenDto(String type, String token, String expires) {
        this.type = type;
        this.token = token;
        this.expires = expires;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }
}
