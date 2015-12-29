#coding=utf-8
'''
Created on 2015年9月11日
modified on 2015年9月14日
@author: huangpei401
@fun：验证是否登录的链接有效性
@environment：通用环境
'''
def ym_check_link(url,islogined):
	from selenium import webdriver
	import time,urllib2,sys
	import codecs

	sys.path.append("/root/home/projects/emao/public")
	import login,logout,ff_configure,check_link,wait_time


	if islogined==True:

		check_link.check_link(url,'/root/home/projects/emao/test_report/emao_log_ok.txt',\
			'/root/home/projects/emao/test_report/emao_log_error.txt')
		print u'验证完毕！'	

	else:
		#ff_driver=ff_configure.ff_configure()		
		ff_driver = webdriver.PhantomJS()
		ff_driver.get(url)
		wait_time.wait_time(ff_driver,1,60)

		ff_driver.maximize_window()
		wait_time.wait_time(ff_driver,1,60)

		check_link.check_link(url,'/root/home/projects/emao/test_report/emao_log_ok.txt',\
			'/root/home/projects/emao/test_report/emao_log_error.txt')
		print u'验证完毕！'	
		ff_driver.close()
