package com.shangjia.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.shangjia.model.SalerAccount;

/**
 * Created by dai on 2015/12/7.
 */
public class TemporyLoginService {

    private static Map<String, SalerAccount> passwordMap = new ConcurrentHashMap<>();

    public static String register(String name, String authCode, String password){
        if( passwordMap.containsKey(name.trim())){
            return "账号名"+ name +"已经存在。请重新输入新的账号名称。";
        }
        SalerAccount salerAccount = new SalerAccount(name, authCode,password);
        passwordMap.put(name, salerAccount);
        return null;
    }


    public static SalerAccount getUser(String name, String password){
        if( name!=null && password !=null ){
            SalerAccount salerAccount = passwordMap.get(name);
            if( salerAccount!=null && salerAccount.getPassword().endsWith(password)){
                return salerAccount;
            }
        }
        return null;
    }


    public static void changePassword() {



    }
}
