import requests
import unittest
import sys

import xlrd
sys.path.append("../utils")
sys.path.append("../data")
from regex import Regex


print(Regex.re(1, 1))

class LatentAPI(unittest.TestCase):
    strBaseurl = 'http://www.baidu.com'
    url1 = 'https://api.opifices.com/v1/specifications.json'
    url2 = 'https://api.opifices.com/oauth/token'
    data = {
        'username': '13800138000',
        'password': '123456',
        'client_id': '1000a0200d80800dc40d322db6747f09b36825c418141a8f02449fdf1003fb55',
        'client_secret': 'a567554533c4c6ce3c6c4f1fe9fb02a1fa4d4ab582b6626fc1d5d6b3cc24ec2c',
        'grant_type': 'password'
    }

    def SengMSG(self):

        a = requests.get(self.url1).text
        b = requests.post(self.url2, self.data, verify=False).text
        print(Regex.re(a, a))
        print(Regex.re(a, b))

l = LatentAPI()
l.SengMSG()



