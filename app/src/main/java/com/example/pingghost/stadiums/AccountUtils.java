package com.example.pingghost.stadiums;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

/**
 * Created by pingghost on 07/11/18.
 */

class AccountUtils {
    public static final String ACCOUNT_TYPE = "StadiumsAPP";
    public static final String AUTH_TOKEN_TYPE = "StadiumsAPP.aaa";

    public static IServerAuthenticator mServerAuthenticator = new MyServerAuthenticator();

    public static Account getAccount(Context context, String accountName) {
        AccountManager accountManager = AccountManager.get(context);
        Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
        for (Account account : accounts) {
            if (account.name.equalsIgnoreCase(accountName)) {
                return account;
            }
        }
        return null;
    }
}
