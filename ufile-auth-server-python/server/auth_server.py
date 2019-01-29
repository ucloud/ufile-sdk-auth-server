import json

from flask import Flask
from flask import request
from server.authroization import Authroizator

app = Flask(__name__)


@app.route('/applyAuth', methods=['POST'])
def applyAuth():
    error = None
    print("[request]:%s" % request)
    headers = request.headers
    print("[headers]:%s" % headers)
    data = request.get_data()
    print("[body]:%s" % data)

    checkRes = checkRequestParams(data)
    if not checkRes[0]:
        return checkRes[1]
    json_data = checkRes[1]
    auth = Authroizator(json_data)

    return auth.calculateAuthSignature()


@app.route('/applyPrivateUrlAuth', methods=['POST'])
def applyPrivateUrlAuth():
    error = None
    print("[request]:%s" % request)
    headers = request.headers
    print("[headers]:%s" % headers)
    data = request.get_data()
    print("[body]:%s" % data)

    checkRes = checkRequestParams(data)
    if not checkRes[0]:
        return checkRes[1]
    json_data = checkRes[1]
    auth = Authroizator(json_data)

    return auth.calculatePrivateUrlAuthroization()


def checkRequestParams(data):
    # 参数为空
    if data is None or data == b'':
        return False, "Request body is null!"

    return True, json.loads(data.decode('utf-8'))


if __name__ == '__main__':
    app.run(host='0.0.0.0', port='8000', debug=True)
