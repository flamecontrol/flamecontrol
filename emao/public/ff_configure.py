#coding=utf-8
'''
Created on 2015年9月11日
@author: huangpei401
@fun：读取Firefox配置文件
@environment：通用环境
'''

def ff_configure():
	from selenium import webdriver

	#import sys 
	#reload(sys) 
	#sys.setdefaultencoding('utf8')

	#ff_driver=webdriver.Firefox()

	profile = webdriver.FirefoxProfile("E:\\svn\\develop\\AutoTest_Emao\\public\\ff_config")
	ff_driver = webdriver.Firefox(profile)

	return ff_driver