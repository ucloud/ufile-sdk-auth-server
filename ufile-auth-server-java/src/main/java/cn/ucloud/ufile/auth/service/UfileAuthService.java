package cn.ucloud.ufile.auth.service;

import cn.ucloud.ufile.auth.bean.AuthPrivateUrlRequest;
import cn.ucloud.ufile.auth.util.ParameterValidator;
import cn.ucloud.ufile.auth.bean.AuthRequest;
import cn.ucloud.ufile.auth.util.Encryptor;
import org.springframework.stereotype.Service;
import sun.security.validator.ValidatorException;

import java.util.Base64;


/**
 * @description:
 * @author: joshua
 * @E-mail: joshua.yin@ucloud.cn
 * @date: 2018-11-29 14:20
 */

@Service
public class UfileAuthService {
    private static final String publicKey = 您的公钥;
    private static final String privateKey = 您的私钥;

    public String calculateAuthroization(AuthRequest request) {
        try {
            ParameterValidator.validator(request);

            StringBuffer signData = new StringBuffer();
            signData.append(request.getMethod() + "\n");
            signData.append(request.getContent_md5() + "\n");
            signData.append(request.getContent_type() + "\n");
            signData.append(request.getDate() + "\n");
            signData.append("/" + request.getBucket());
            signData.append("/" + request.getKey());
            if (request.getPut_policy() != null && !request.getPut_policy().isEmpty()) {
                signData.append(request.getPut_policy());
            }

            String signature = signature(privateKey, signData.toString());

            StringBuilder res = new StringBuilder("UCloud ")
                    .append(publicKey)
                    .append(":")
                    .append(signature);

            if (request.getPut_policy() != null && !request.getPut_policy().isEmpty()) {
                res.append(":").append(request.getPut_policy());
            }

            return res.toString();
        } catch (
                ValidatorException e) {
            return e.getMessage();
        } catch (
                Exception e) {
            return e.getMessage();
        }

    }

    public String calculatePrivateUrlAuthroization(AuthPrivateUrlRequest request) {
        try {
            ParameterValidator.validator(request);

            StringBuffer signData = new StringBuffer();
            signData.append(request.getMethod() + "\n");
            signData.append("\n");
            signData.append("\n");
            signData.append(request.getExpires() + "\n");
            signData.append("/" + request.getBucket());
            signData.append("/" + request.getKey());

            return signature(privateKey, signData.toString());
        } catch (ValidatorException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private String signature(String key, String data) throws Exception {
        byte[] hmacSha1 = null;
        hmacSha1 = Encryptor.Hmac_SHA1(key, data);

        if (hmacSha1 == null || hmacSha1.length == 0)
            throw new Exception("Encrypt Hmac-SHA1 failed!");

        return Base64.getEncoder().encodeToString(hmacSha1);
    }
}
