package cn.ucloud.ufile.auth.bean;

import com.google.gson.Gson;

import javax.validation.constraints.NotEmpty;

/**
 * @description:
 * @author: joshua
 * @E-mail: joshua.yin@ucloud.cn
 * @date: 2018-11-29 15:15
 */
public class AuthRequest {
    @NotEmpty(message = "method is required!")
    private String method;
    @NotEmpty(message = "bucket is required!")
    private String bucket;

    private String key = "";
    private String content_type = "";
    private String content_md5 = "";
    private String date = "";
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

    public String getContent_type() {
        return content_type;
    }

    public String getContent_md5() {
        return content_md5;
    }

    public String getDate() {
        return date;
    }

    public String getOptional() {
        return optional;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
