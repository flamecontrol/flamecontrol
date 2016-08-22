import requests



s = requests.Session()
url1 = 'https://api.opifices.com/v1/specifications.json'
url2 = 'https://api.opifices.com/v1/verifications.json'
url3 = 'https://api.opifices.com/v1/verifications.json'

a = s.get(url1)
print(a.text)

a = s.options(url2)
print(a)

data = {'verification': {'mobile': '13800138000', 'service': 'login'}, 'skip_geetest_valid': True}
a = s.post(url3, data)
print(a)
# print(a.text)