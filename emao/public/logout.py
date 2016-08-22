#coding=utf-8
'''
Created on 2015年9月14日
@author: huangpei401
@fun：用户退出登录
@environment：通用环境
'''
from seleniumDEMO import webdriver
import time
from seleniumDEMO.webdriver.common.action_chains import ActionChains

time1=1
time2=60
def logout(driver): 
       
    #above=driver.find_element_by_class_name("global-set")
    above=driver.find_element_by_link_text(u"设置")
    ActionChains(driver).move_to_element(above).perform()
    time.sleep(time1)
    driver.implicitly_wait(time1)
    

    driver.find_element_by_link_text(u"退出登录").click()
    time.sleep(time1)
    driver.implicitly_wait(time1)

    

