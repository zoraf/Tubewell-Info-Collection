package com.example.tubewellinfocollection.Service;

public class ApiUtils {

    private ApiUtils() {}
    public static final String BASE_URL = "http://192.168.20.191:9090/api/";
    //    public static final String BASE_URL = "http://192.168.0.105/bramptionhardwood-Inventory/bramptionhardwood-Inventory/services/";
    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
