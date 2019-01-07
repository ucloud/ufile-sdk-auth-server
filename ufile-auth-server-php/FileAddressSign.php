<?php

require_once("UCloud_Auth.php");
require_once("Auth_Config.php");


if ($_SERVER['REQUEST_METHOD'] == "GET") {
    error_log("file address author : does not support 'HTTP-GET' method...", 3, "/tmp/php.log");
    echo "file address author: does not support 'HTTP-GET' method...";
}

if ($_SERVER['REQUEST_METHOD'] == "POST") {
    $param_obj = json_decode(file_get_contents('php://input'));
    $method=$param_obj->method;
    $bucket=$param_obj->bucket;
    $key=$param_obj->key;
    $expires=$param_obj->expires;

    $auth=new UCloud_Auth($UCLOUD_PUBLIC_KEY, $UCLOUD_PRIVATE_KEY);
    echo  $auth->signFileAddressRequest($method, $bucket, $key, $expires);
}

?>