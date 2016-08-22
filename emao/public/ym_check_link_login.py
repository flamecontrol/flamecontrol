#coding=utf-8
'''
Created on 2015年9月11日
modified on 2015年9月14日
@author: huangpei401
@fun：验证是否登录的链接有效性
@environment：通用环境
'''
def ym_check_link_login(ff_driver,url,islogined):
	from seleniumDEMO import webdriver
	import time,urllib2,sys
	import codecs

	sys.path.append("E:\\svn\\develop\\AutoTest_Emao\\public")
	import login,logout,ff_configure,check_link_login,wait_time

	if islogined==True:
		check_link_login.check_link_login(ff_driver,'E:/svn/develop/AutoTest_Emao/public/test_report/emao_log_ok.txt',\
			'E:/svn/develop/AutoTest_Emao/public/test_report/emao_log_error.txt')
		print u'验证完毕！'

	else:	
		ff_driver=ff_configure.ff_configure()
		ff_driver.get(url)
		wait_time.wait_time(ff_driver,1,60)

		ff_driver.maximize_window()
		wait_time.wait_time(ff_driver,1,60)

		check_link_login.check_link_login(ff_driver,'E:/svn/develop/AutoTest_Emao/public/test_report/emao_log_ok.txt',\
			'E:/svn/develop/AutoTest_Emao/public/test_report/emao_log_error.txt')
		print u'验证结束！'