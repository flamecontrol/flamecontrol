#coding=utf-8
from selenium import webdriver
import time
 
driver = webdriver.Firefox()
 
driver.get("http://www.baidu.com")
driver.find_element_by_id("kw").send_keys(u"python编程开发")
driver.find_element_by_id("su").click()
 
time.sleep(5)
 
driver.quit()