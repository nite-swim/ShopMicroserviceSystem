package com.itcast.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SmsUtil {
    //替换成自己申请的accessKeyId
    private static String accessKeyId = "LTAI5tKMFXE2hDt4dXAMTCg8";
    //替换成自己申请的accessKeySecret
    private static String accessKeySecret = "MkhNY2tdBKVm5Z920dSWtxGzjOL2ox";
    static final String product = "Dysmsapi";
    static final String domain = "dysmsapi.aliyuncs.com";
    /**
     \* 发送短信
     *
     \* @param phoneNumbers 要发送短信到哪个手机号
     \* @param signName 短信签名[必须使用前面申请的]
     \* @param templateCode 短信短信模板ID[必须使用前面申请的]
     \* @param param 模板中${code}位置传递的内容
     */
    public static void sendSms(String phoneNumbers, String signName, String
            templateCode, String param)
    {
        try
        {
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                    accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
                    domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phoneNumbers);
            request.setSignName(signName);
            request.setTemplateCode(templateCode);
            request.setTemplateParam(param);
            request.setOutId("");
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            System.out.println(sendSmsResponse.getCode());
            if(!"OK".equals(sendSmsResponse.getCode()))
            {
                throw new RuntimeException(sendSmsResponse.getMessage());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("发送短信失败");
        }
    }
}
