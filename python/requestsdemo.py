import requests
import json

s = requests.session()
# url = 'http://www.emao.com/404.html'
url = 'http://dealer.emao.com/test/json.php'

# url = 'http://dealer.emao.com/test/post.php'

# url = 'http://wobangdajia.sinaapp.com/post.php'
payload = {'c': 'c','d': 'd'}
# payload = {'c': 'c','d': 'd','file': open('1.txt', 'rb')}
files = {'file': open('../PHP/1.txt', 'rb')}
files = {'file': open('./1.txt', 'rb')}
# files = {'file': open('pic.jpg', 'rb')}
# files = {'file': ('pic.jpg', open('pic.jpg', 'rb'))}
# files = [('file', ('foo.png', open('foo.png', 'rb'), 'image/png')),('images', ('bar.png', open('foo.png', 'rb'), 'image/png'))]

# files = [('file', ('foo.png', open('foo.png', 'rb'), 'image/png'))]
headers = {'test': 'test'}
r = s.post(url, data=json.dumps(payload))

# r = s.post(url , data = payload , files = files , headers = headers , timeout=1)

# r = s.post(url , data = payload , headers = headers)
# r = s.post(url , data = payload , files = files , headers = headers)

print(r.url)
print(r.status_code)
print(r.headers)
print(r.request.headers)
print(r.headers['Server'])
print(r.text)
print(r.content)
print(r.history)
# print(r.json())
# print(r.json()['b'][1])