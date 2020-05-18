package com.example.laboratorio2.entidades;

import com.google.gson.annotations.SerializedName;

public class ApiKey {
    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
    @SerializedName(value = "api-key")
    private String apikey;
}
