package com.jnshu.dreamteam.utils;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;


@Component
@ConfigurationProperties(prefix = "mes")
public class MessageUtil {

    private static String accountSid;
    private static String accountToken;
    private static String appId;
    private static String serverIP;
    private static String serverPort;

    private static CCPRestSmsSDK initCCPRestSmsSDK(){
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        restAPI.init(serverIP,serverPort);
        restAPI.setAccount(accountSid,accountToken);
        restAPI.setAppId(appId);
        return restAPI;
    }

    public static void sendMessage(String to,String data) throws Exception{
          CCPRestSmsSDK restAPI = initCCPRestSmsSDK();
          Map<String,Object> result = restAPI.sendTemplateSMS(to,"1",new String[]{data,"1"});
        if(!"000000".equals(result.get("statusCode"))){
            throw new Exception("错误码"+result.get("statusCode")+"错误信息="+result.get("statusMsg"));
        }
    }

    /**
     * 生成num位验证码
     * @param num
     * @return
     */
    public static String randomNum(int num){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for(int i = 0;i< num;i++){
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    public void setAccountSid(String accountSid) {
        MessageUtil.accountSid = accountSid;
    }

    public void setAccountToken(String accountToken) {
        MessageUtil.accountToken = accountToken;
    }

    public void setAppId(String appId) {
        MessageUtil.appId = appId;
    }

    public void setServerIP(String serverIP) {
        MessageUtil.serverIP = serverIP;
    }

    public void setServerPort(String serverPort) {
        MessageUtil.serverPort = serverPort;
    }
}
