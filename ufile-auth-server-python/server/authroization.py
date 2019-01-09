import base64
import hmac
from hashlib import sha1

PUBLIC_KEY = 'Kf6owXtNZSimr0rR7w9ew6Iclm1w73QB8+jUkiV7hXgBYtV5BNWN1LlNUko='
PRIVATE_KEY = '9K91tK7hcpCFL+90HwVk8lGUwJrqqjzfUouDD47RLrs9f1Umt4gXx7LWwB4kE7um'


class Authroizator:
    json_data = {}

    def __init__(self, json_data):
        self.json_data = json_data
        print("[json_data]:%s" % self.json_data)

    def calculateAuthSignature(self):
        check = self.__checkAuthRequiredParams()
        if not check[0]:
            return check[1]

        method = self.json_data.get('method')
        bucket = self.json_data.get('bucket')
        content_type = self.json_data.get('content_type')
        if content_type is None:
            content_type = ''
        content_md5 = self.json_data.get('content_md5')
        if content_md5 is None:
            content_md5 = ''
        date = self.json_data.get('date')
        if date is None:
            date = ''
        key = self.json_data.get('key')
        if key is None:
            key = ''
        content = method + '\n' + content_md5 + '\n' + content_type + '\n' + date + '\n'
        content += '/' + bucket + '/' + key

        signature = self.__signature(content)
        return 'UCloud ' + PUBLIC_KEY + ':' + signature

    def calculatePrivateUrlAuthroization(self):
        check = self.__checkPrivateUrlAuthRequiredParams()
        if not check[0]:
            return check[1]

        method = self.json_data.get('method')
        bucket = self.json_data.get('bucket')
        key = self.json_data.get('key')
        expires = str(self.json_data.get('expires'))
        content = method + '\n\n\n' + expires + '\n'
        content += '/' + bucket + '/' + key

        return self.__signature(content)

    def __checkAuthRequiredParams(self):
        if self.json_data is None:
            return False, "Request body json is null"

        method = self.json_data.get('method')
        if method is None or method == '':
            return False, "'method' is required!"
        bucket = self.json_data.get('bucket')
        if bucket is None or bucket == '':
            return False, "'bucket' is required!"

        return True, ''

    def __checkPrivateUrlAuthRequiredParams(self):
        if self.json_data is None:
            return False, "Request body json is null"

        method = self.json_data.get('method')
        if method is None or method == '':
            return False, "'method' is required!"
        bucket = self.json_data.get('bucket')
        if bucket is None or bucket == '':
            return False, "'bucket' is required!"
        key = self.json_data.get('key')
        if key is None or key == '':
            return False, "'key' is required!"
        expires = self.json_data.get('expires')
        if expires is None or str(expires) == '':
            return False, "'expires' is required!"

        return True, ''

    def __signature(self, content):
        print(content)
        hmac_res = hmac.new(PRIVATE_KEY.encode(), content.encode(), sha1).digest()

        return base64.standard_b64encode(hmac_res).decode('utf-8')
