package cn.ucloud.ufile.auth.bean;

import com.google.gson.Gson;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: joshua
 * @E-mail: joshua.yin@ucloud.cn
 * @date: 2018-11-29 15:15
 */
public class AuthPrivateUrlRequest {
    @NotEmpty(message = "method is required!")
    private String method;
    @NotEmpty(message = "bucket is required!")
    private String bucket;
    @NotEmpty(message = "key is required!")
    private String key;
    @NotNull(message = "expires is required!")
    private Long expires;
    private String optional;

    public String getMethod() {
        return method;
    }

    public String getBucket() {
        return bucket;
    }

    public String getKey() {
        return key;
    }

    public long getExpires() {
        return expires.longValue();
    }

    public String getOptional() {
        return optional;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
