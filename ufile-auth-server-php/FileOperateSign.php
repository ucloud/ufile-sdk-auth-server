<?php

require_once("UCloud_Auth.php");
require_once("Auth_Config.php");



if ($_SERVER['REQUEST_METHOD'] == "GET") {
    error_log("server author : does not support 'HTTP-GET' method...", 3, "/tmp/php.log");
    echo "does not support 'HTTP-GET' method...";
}

if ($_SERVER['REQUEST_METHOD'] == "POST") {
    $param_obj = json_decode(file_get_contents('php://input'));
    $method=$param_obj->method;
    $bucket=$param_obj->bucket;
    $key=$param_obj->key;
    $content_md5=$param_obj->content_md5;
    $content_type=$param_obj->content_type;
    $date=$param_obj->date;
    $put_policy=$param_obj->put_policy;

    $auth=new UCloud_Auth($UCLOUD_PUBLIC_KEY, $UCLOUD_PRIVATE_KEY);
    echo  $auth->signFileOperateRequest($method, $bucket, $key, $content_md5, $content_type, $date, $put_policy);
}

?>