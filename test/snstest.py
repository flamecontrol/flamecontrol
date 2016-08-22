import requests
import json

s = requests.session()

# app短信get
# url = 'http://i.emao.com/api/v1/mobile.php?info={"tag":"register","mobile":"13478330122","code":1004}'
# r = s.get(url)
# print(r.text)

# url = 'http://passport.emao.com/sendphonevalicode'
# url = 'http://i.emao.com/api/v1/mobile.php'

# url = 'http://dealer.emao.com/test/post.php'

# url = 'http://wobangdajia.sinaapp.com/post.php'
# payload = {"tag":"register","mobile":"13301060017","code":1004}
# payload = {'c': 'c','d': 'd','file': open('1.txt', 'rb')}
# files = {'file': open('../PHP/1.txt', 'rb')}
# files = {'file': open('./test.txt', 'rb')}
# files = {'file': open('pic.jpg', 'rb')}
# files = {'file': ('pic.jpg', open('pic.jpg', 'rb'))}
# files = [('file', ('foo.png', open('foo.png', 'rb'), 'image/png')),('images', ('bar.png', open('foo.png', 'rb'), 'image/png'))]

# files = [('file', ('foo.png', open('foo.png', 'rb'), 'image/png'))]
# headers = {'test': 'test'}
# r = s.get(url)
r = s.post(url, data=json.dumps(payload))

# r = s.post(url , data = payload , files = files , headers = headers , timeout=1)

# r = s.post(url , data = payload , headers = headers)
# r = s.post(url , data = payload , files = files , headers = headers)

# print(r.url)
# print(r.status_code)
# print(r.headers)
# print(r.request.headers)
# print(r.headers['Server'])
print(r.text)
# print(r.content)
# print(r.history)
# print(r.json())
# print(r.json()['b'][1])
