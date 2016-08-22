# -*- coding: utf-8 -*-
#coding=utf-8
import requests
# import urllib, urllib.parse
import json
import datetime

s = requests.session()
urllist = [# app现有功能，所使用到的接口整理：
# 资讯的接口：
#【1200】资讯频道
'http://app.emao.com/v1.4/?info={"code":1200}',
#【1201】资讯首页
#资讯各个频道列表接口；
# 1,"全部"  102,"新车"  103,"评测"  104,"比车"   105,"导购"
# 108,"新闻"   106,"用车"   107,"说吧"   125,"行业"    200,"自媒体"
'http://app.emao.com/v1.4/?info={"code":1201,"channel":1,"offset":0,"len":20}',
'http://app.emao.com/v1.4/?info={"code":1201,"channel":102,"offset":0,"len":20}',
'http://app.emao.com/v1.4/?info={"code":1201,"channel":103,"offset":0,"len":20}',
'http://app.emao.com/v1.4/?info={"code":1201,"channel":104,"offset":0,"len":20}',
'http://app.emao.com/v1.4/?info={"code":1201,"channel":105,"offset":0,"len":20}',
'http://app.emao.com/v1.4/?info={"code":1201,"channel":106,"offset":0,"len":20}',
'http://app.emao.com/v1.4/?info={"code":1201,"channel":107,"offset":0,"len":20}',
'http://app.emao.com/v1.4/?info={"code":1201,"channel":108,"offset":0,"len":20}',
#【1202】 新闻正文
'http://app.emao.com/v1.4/?info={"code":1202,"docId":16864}',
#【1006】获取广告
'http://app.emao.com/v1.4/?info={"code":"1006","deviceId":"iPhone OS","buildNo":"1.4.1","channelNum":"0","deviceVersion":"iPhone 6P","platform":"iOS"}',
#【1204】获得推广内容（应该是已删除）
'http://app.emao.com/v1.4/process/?info={"code":1204,"docId":11,platform":"android","channel":1,"location":1}',

#【1205】获得文章所有图片(未用)
# 'http://app.emao.com/v1.4/process/?info={"code":1202,"docId":16819}',

#【1211】发表评论
u'http://app.emao.com/v1.4/process/?info={"code":"1211","content":"世界无酒假日","parentId":0,"token":"995895896","docId":"16862"}',
#【1212】评论列表
'http://app.emao.com/v1.4/process/?info={"code":"1212","ltime":0,"len":20,"offset":0,"docId":"16819"}',
#【1213】评论评价（赞）
'http://app.emao.com/v1.4/process/?info={ "id":139917,"token":"995895896","code":1213}',
#【1214】收藏  (未用)
#  收藏直接收藏到本地数据库，不请求接口
#【1216】新闻点赞
'http://app.emao.com/v1.4/?info={"code":"1216","coordinate": 116.3204924355856,39.99074837676179","userId":"93014","docId":"16854"}',

# 选车的接口：
#【1311】获得所有品牌
'http://app.emao.com/v1.4/?info={"updatetime":"1449815453","code":"1311"}',
#【1312】获得品牌下所有车系
'http://app.emao.com/v1.4/?info={"id":"2","code":"1312"}',
# 4.2.3 车系综述页
#【1313】车系首页
'http://app.emao.com/v1.4/?info={"id":"8","code":"1313"}',
#【1314】图片首页（删除）
#【1315】所有图片
'http://app.emao.com/v1.4/?info={"code":"1315","id":"9","len":20,"categoryId":193,"offset":0}',
#【1317】新闻列表
'http://app.emao.com/v1.4/?info={"code":"1317","id":"8","len":20,"offset":0,"ltime":"0"}',
#【1318】新闻详情   参见：3.2 [1202]新闻正文
'http://app.emao.com/v1.4/?info={"code":1202,"docId":"16584"}',

#【1320】评测口碑综述
'http://app.emao.com/v1.4/?info={"id":"8","code":"1320"}',
#【1321】网友口碑–新增
'http://app.emao.com/v1.4/?info={"code":"1321","id":"8","len":20,"offset":0}',
#【1322】口碑详情–新增
'http://app.emao.com/v1.4/?info={"id":"4448","code":"1322"}',
#【1323】网友口碑评论列表–新增
'http://app.emao.com/v1.4/process/?info={"code":"1323","ltime":0,"len":20,"offset":0,"docId":"4448"}',
#【1324】评论口碑–新增
u'http://app.emao.com/v1.4/process/?info={"code":"1324","content":"奥迪评价不错",parentId":0,"token":"995895896","docId":"4448"}',

#【1325】口碑(赞)–新增
'http://app.emao.com/v1.4/process/?info={"id":305,"token":"995895896","code":1325}',
#【2023】4.2.314 对比 参考：5.2.4#【2023】对比
'http://app.emao.com/v1.4/?info={"id":"14749,14748","code":"2023"}',

# 4.2.4  车款页
#【1330】款首页
'http://app.emao.com/v1.4/?info={"id":"14667","code":"1330"}',

# 经销商接口
#【5000】经销商列表
'http://app.emao.com/v1.4/dealer/?info={ "code": "5000","offset":2,"len": 2,"seriesid":"9","cityid":"1"}',
'http://app.emao.com/v1.4/dealer/?info={ "code": "5000","offset":0,"len": 300,"seriesid":"9","cityid":"1"}',

#【8001】提交申请表单
#预约试驾提交 8001  200OK
u'http://app.emao.com/v1.4/dealer/?info={"dealer_ids":["7639","7612","7580"],"c_model":"14748","mobile":"15101139105","province_id":"3","code":"8001","source":"app","tag":"shijia","param":{"remark":"喜欢红色的"},"sex":1,"name":"张三"}',
# 4.3 快速选车 ---价格
#【1501】筛选结果
'http://app.emao.com/v1.4/?info={"source":"","code":"1501","price":"5-18","level":"4","No":"1449826017","gearbox":"","displacement":""}',
#【1502】筛选列表
'http://app.emao.com/v1.4/?info={"source":"3","offset":0,"sort":"4","len":20,"code":"1502","price":"6-22","level":"256","gearbox":"","displacement":""}',
# 4.4 搜索
#【1700】车系搜索
u'http://app.emao.com/v1.4/?info={"code":"1700","ltime":0,"len":20,"offset":0,"tag":"0","keyword":"奥迪"}',


# 5.2.2  添加关注车型
#【1311】获得所有品牌  同4.2.1 [1311]获得所有品牌
'http://app.emao.com/v1.4/?info={"updatetime":"1450064957","code":"1311"}',
#【1312】 获得所有车型  同4.2.2 [1312]获得所有车型
'http://app.emao.com/v1.4/?info={"id":"5","code":"1312"}',
#【1313】添加关注的车
'http://app.emao.com/v1.4/?info={"id":"127,"code":"1313"}',

#【2023】对比
'http://app.emao.com/v1.4/?info={"id":"14749,14748","code":"2023"}',

# 5.3 我的评论（已登录）
#【2024】我发表的评论
'http://app.emao.com/v1.4/process/?info={"code":"2024","len":20,"offset":0,"token":"8790408764"}',
#【2025】别人回复我的
'http://app.emao.com/v1.4/process/?info={"code":"2025","len":20,"offset":0,"token":"8790408764"}',
#【2026】删除我发表的评论
'http://app.emao.com/v1.4/process/?info={"token":"8790408764","id":143186,"code":2026}',
# 5.6 设置
#【2028】意见反馈
u'http://app.emao.com/v1.4/?info={"token":"8790408764","content":"文章内容越多越好","code":"2028"}',
#【2070】登录(v1.1)（app ios 1.4.1版本用的登录接口，同时请求2012和2070接口）
u'http://app.emao.com/v1.4/?info={"code":"2070","account":"樱桃小丸子","buildNo":"1.4.1","deviceId":"1F0D18D2-B03C-4CA5-B953-B923C5201E30","pwd":"TnSMZOIJarmEUUs\/53uSj0NbH97P3FoCBem3wGWsu1zORFSYF57vxZJOQIqnDuVu8y5YQwemklIxRIz9+J2AeN7pCTMp5kAuXUkv01jVPjHXpJh0qBUfGOWnDCuDzAkM1r9MG7tu5mCwi2\/owNADkiBk5a9Tca\/2Znj2otNjQ58=","platform":"Iphone"}',
#【2170】联合登录(微博账户登录)
'http://app.emao.com/v1.4/?info={"code":"2170","deviceId":"1F0D18D2-B03C-4CA5-B953-B923C5201E30","buildNo":"1.4.1","source":"weibo","token":"2.00peisQDGsZ13C18e4f44069JJ321C","platform":"Iphone"}',
#【2170】联合登录(QQ账户登录)
'http://app.emao.com/v1.4/?info={"code":"2170","deviceId":"1F0D18D2-B03C-4CA5-B953-B923C5201E30","buildNo":"1.4.1","source":"qq","token":"F29BCC31C6BDE15811E127D0A68F04DA","platform":"Iphone"}',
#【2170】联合登录(微信账户登录)
'http://app.emao.com/v1.4/?info={"deviceId":"1F0D18D2-B03C-4CA5-B953-B923C5201E30","source":"weixin","openid":"oXE7xt_fySL9CYiRKqEUTVl2LDm8","code":"2170","platform":"Iphone","buildNo":"1.4.1","token":"OezXcEiiBSKSxW0eoylIeMaF-0cOeIKfP2pp543kIBXr_wbtObqRSYcVdYe3BFKHeLHJSW2_KIMS_cQN-0wccYHJntcMtkNgc_wr9iGuuvQU3hLv8eBjCBGtM2GnePfaocqAw5tMUEv2-Yo1Zh1F7A"}',

#【2073】退出登录
'http://app.emao.com/v1.4/?info={"code":"2073","deviceId": "1F0D18D2-B03C-4CA5-B953-B923C5201E30","buildNo":"1.4.1","platform":"Iphone"}'

]

# url = 'http://app.emao.com/v1.4/?info={"ltime":0,"len":20,"docId":15581,"code":1212,"offset":0}'


count = 0
for url in urllist:
    count = count + 1
    print(url)
    retime = []
    for i in range(1):
        r = s.get(url)
        costhttp = r.elapsed.total_seconds()
        retime.append(costhttp)
    av = "{:.3f}".format(sum(retime)/len(retime))
    ma = max(retime)
    mi = min(retime)
    # print(retime)
    # if float(av) > 0.0:
    if float(av) > 0.0:
        text = r.text
        print("-----------" + text)
        # print("应答长度:" + str(len(text)))
        # print("请求超时:" + str(av) + "(" + str(ma) + "," + str(mi) + ")")
    else:
        print(av)

print(count)
