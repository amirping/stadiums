package com.example.pingghost.stadiums.remote;

/**
 * Created by pingghost on 09/12/18.
 * may the code be with pingghost
 */

public class APIUtils {
    private APIUtils(){
    };
    public static final String API_URL = "http://10.0.2.2:3001/";

    public static UserService getUserService(){
        return applicationAPI.getAPICLient(API_URL).create(UserService.class);
    }
    public static ContractService getContractService(){
        return applicationAPI.getAPICLient(API_URL).create(ContractService.class);
    }
    public static StadiumService getStadiumService(){
        return applicationAPI.getAPICLient(API_URL).create(StadiumService.class);
    }
}
