# ufile-sdk-auth-server

## 说明
- 该项目用于Ufile SDK接入远端签名时使用
- 您需要clone对应语言的源码，在项目中配置public key和private key，避免了部分平台的SDK（特别是android和iOS平台）会将private key放在本地的不安全性。

## 接口说明
#### 请求对象操作的API签名
- **URL**: http://your_domain/applyAuth
- **http method**: POST
- **Content-Type**: application/json
- **Request Body**:
    
    ``` json
    {
        /**
         * Required
         * 你请求的Ufile API的Http Method
         */
        "method":"PUT",
        /**
         * Required
         * 你想操作的Bucket名称
         */
        "bucket":"bucketName",
        /**
         * Optional
         * 你想操作的云端对象的名称
         */
        "key":"object's key name"
        /**
         * Optional
         * 你请求Ufile API时的Content-Type
         */
        "content_type":"application/octet-stream",
         /**
         * Optional
         * 你在请求上传类Ufile API时的数据的MD5
         */
        "content_md5":"",
         /**
         * Optional
         * 你请求的时间字符串，格式：yyyyMMddHHmmss
         */
        "date":"yyyyMMddHHmmss"
         /**
         * Optional
         * 签名服务额外可选参数，你可以将你部署的签名服务API的鉴权数据放在该字段，部分语言版本的Ufile SDK可配置签名额外参数。
         * 格式：Json字符串，注意Json中引号需要转义
         */
        "optional":"{\"your optional data json\":\"This is your data value\"}"
    }
    ```
- **Response Body**: 签名结果字符串

#### 请求私有Bucket的对象下载链接签名
- **URL**: http://your_domain/applyAuth
- **http method**: POST
- **Content-Type**: application/json
- **Request Body**:
    
    ``` json
    {
        /**
         * Required
         * 你请求的Ufile API的Http Method
         */
        "method":"GET",
        /**
         * Required
         * 你想操作的Bucket名称
         */
        "bucket":"bucketName",
        /**
         * Required
         * 你想操作的云端对象的名称
         */
        "key":"object's key name"
        /**
         * Required
         * 私有Bucket的Object的下载链接的失效时间
         * 失效时间: 当前时间加上一个有效时间, 单位：Unix time second
         */
        "expires":123456789,
        /**
         * Optional
         * 签名服务额外可选参数，你可以将你部署的签名服务API的鉴权数据放在该字段，部分语言版本的Ufile SDK可配置签名额外参数。
         * 格式：Json字符串，注意Json中引号需要转义
         */
        "optional":"{\"your optional data json\":\"This is your data value\"}"
    }
    ```
- **Response Body**: 签名结果字符串