#coding=utf-8
'''
Created on 2015年9月14日
@author: huangpei401
@fun：等待时间
@environment：通用环境
'''
import time
def wait_time(ff_driver,time1,time2):
	time.sleep(time1)
	ff_driver.implicitly_wait(time2)
