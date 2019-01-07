<?php


function CanonicalizedResource($bucket, $key)
{
    return "/" . $bucket . "/" . $key;
}

function CanonicalizedUCloudHeaders($headers)
{

    $keys = array();
    foreach($headers as $header) {
        $header = trim($header);
        $arr = explode(':', $header);
        if (count($arr) < 2) continue;
        list($k, $v) = $arr;
        $k = strtolower($k);
        if (strncasecmp($k, "x-ucloud") === 0) {
            $keys[] = $k;
        }
    }

    $c = '';
    sort($keys, SORT_STRING);
    foreach($keys as $k) {
        $c .= $k . ":" . trim($headers[$v], " ") . "\n";
    }
    return $c;
}


class UCloud_Auth{
	public $publicToken;
	public $privateToken;

	public function __construct($publicToken,$privateToken)
	{
		$this->publicToken 	= $publicToken;
		$this->privateToken = $privateToken;
	}

	public function signFileOpeate($data,$put_policy)
	{
		$sign = base64_encode(hash_hmac('sha1', $data, $this->privateToken, true));
        $singStr = "UCloud " . $this->publicToken . ":" . $sign;

        // 上传回调put_policy
        if ($put_policy) {
            $policystr = base64_encode(str_replace('"','\\"',json_encode($put_policy)));
            $singStr = $singStr . ":" . $policystr;
        }

        return $singStr;
	}

	/*  文件操作签名 */
	public function signFileOperateRequest($method, $bucket, $key, $content_md5, $content_type, $date, $put_policy)
    {
        $data = '';
        $data .= strtoupper($method) . "\n";
        $data .= $content_md5 . "\n";
        $data .= $content_type . "\n";
        $data .= $date . "\n";
        $data .= CanonicalizedResource($bucket, $key);
        error_log($data, 3, "/tmp/php.log");
        return $this->signFileOpeate($data, $put_policy);
    }

    /* 获取私有‘bucket’下文件的 URL 签名 */
    public function signFileAddressRequest($method,$bucket,$key,$expires)
    {
    	$data = '';
    	$data .= strtoupper($method) . "\n";
    	$data .= "\n";
        $data .= "\n";
        $data .= $expires . "\n";
        $data .= CanonicalizedResource($bucket, $key);

        $sign = base64_encode(hash_hmac('sha1', $data, $this->privateToken, true));
        return $sign;
    }

}

?>