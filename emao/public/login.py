#coding=utf-8
'''
Created on 2015年9月14日
@author: huangpei401
@fun：用户登录
@environment：通用环境
'''
from selenium import webdriver
import time

import sys
sys.path.append("E:\\svn\\develop\\AutoTest_Emao\\public")
import wait_time,ff_configure,ym_check_link,check_single_link


def login(driver,username,password): 


    driver.find_element_by_link_text(u"登录").click()

    wait_time.wait_time(driver,1,60)

    driver.find_element_by_id("mobile").clear()        
    driver.find_element_by_id("mobile").send_keys(username)
    wait_time.wait_time(driver,1,60)

    driver.find_element_by_id("pwd").clear()        
    driver.find_element_by_id("pwd").send_keys(password)
    wait_time.wait_time(driver,1,60)

    driver.find_element_by_id("loginButton").click()
    wait_time.wait_time(driver,1,60)

    ele=driver.find_element_by_partial_link_text(u"欢迎您").is_displayed() 
    
    if ele:
        print u'用户名:'+username+u'\t登录成功！'
    else: 
        print u'用户名:'+username+u'\t登录失败！'