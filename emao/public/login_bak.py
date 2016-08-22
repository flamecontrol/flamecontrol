#coding=utf-8

from seleniumDEMO import webdriver
import time
def login(driver,username,password): 
    time1=1
    time2=60

    driver.find_element_by_link_text(u"登录").click()

    time.sleep(time1)
    driver.implicitly_wait(time2)

    driver.find_element_by_id("mobile").clear()        
    driver.find_element_by_id("mobile").send_keys(username)
    time.sleep(time1)
    driver.implicitly_wait(time2)

    driver.find_element_by_id("pwd").clear()        
    driver.find_element_by_id("pwd").send_keys(password)
    time.sleep(time1)
    driver.implicitly_wait(time2)

    driver.find_element_by_id("loginButton").click()
    time.sleep(time1)
    driver.implicitly_wait(time2)  