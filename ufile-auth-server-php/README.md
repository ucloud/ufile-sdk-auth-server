# Ufile SDK Auth-Server for PHP

### 目录说明

目录  | 说明
------------- | -------------
`root/ufile-auth-server-php/FileOperateSign` | 文件操作签名接口
`root/ufile-auth-server-php/FileAddressSign` | 获取私有`bucket`下文件url签名接口
`root/ufile-auth-server-php/Auth_Config` | 配置`bucket`操作的公私钥

### 使用方法

只需把此签名服务部署到你的服务器，然后在移动端(此处以ios为例)使用服务端签名(我们不推荐在移动端使用本地签名，如果使用本地签名方式则无需用到此项目)，具体配置如下： 

```
UFConfig *ufConfig = [UFConfig instanceConfigWithPrivateToken:nil publicToken:@"bucket公钥" bucket:@"bucket名称" fileOperateEncryptServer:@"http://your_php_server/FileOperateSign.php" fileAddressEncryptServer:@"http://your_php_server/FileAddressSign.php" proxySuffix:@"域名后缀"];
UFFileClient *fileClient =  [UFFileClient instanceFileClientWithConfig:ufConfig];
```
