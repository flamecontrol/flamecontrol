#coding=utf-8
'''
Created on 2015年9月11日
@author: huangpei401
@fun：检查单个链接的有效性
@environment：通用环境
'''
import time,urllib2

def check_single_link(url):

    try:
        f=urllib2.urlopen(url)
        print '被测url：'+url,f.getcode()
    except Exception, e:
        print '被测url：'+url,e.getcode()
        print e



