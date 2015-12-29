#-*- coding: UTF-8 -*-
import requests
import urllib
import json
import datetime

s = requests.session()
urllist = [# app现有功能，所使用到的接口整理：
'http://app.emao.com/v1.4/?info={"code":1201,"channel":1,"offset":0,"len":20}',
]

# url = 'http://app.emao.com/v1.4/?info={"ltime":0,"len":20,"docId":15581,"code":1212,"offset":0}'

outtime = 0
count = 0
for url in urllist:
    count = count + 1
    print(url)
    retime = []
    for i in range(2):
        r = s.get(url)
        costhttp = r.elapsed.total_seconds()
        retime.append(costhttp)
    av = "{:.3f}".format(sum(retime)/len(retime))
    ma = max(retime)
    mi = min(retime)
    # print(retime)
    if float(av) > outtime:
        text = r.text
        print(text)
        print("应答长度:" + str(len(text)))
        print("请求超时:" + str(av) + "(" + str(ma) + "," + str(mi) + ")")
    else:
        print(av)
    if outtime == 0:
        break

print(count)
