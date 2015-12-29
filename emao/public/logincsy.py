# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webelement import WebElement
import os,time
from selenium.webdriver.chrome.options import Options
from ElementOperation import ElementOperation

time2 = 60
class Login:
    def __init__(self,driver):
        self.driver = driver 
    def login(self):
        driver = self.driver 
        c = ElementOperation(driver)
        c.Link('登录')
        driver.implicitly_wait(time2)
        c.ID('mobile', 'hp9005')
        driver.implicitly_wait(time2)
        c.ID('pwd', '111111')
        driver.implicitly_wait(time2)
        c.ID_click('loginButton')    
        
    def getName(self):
        driver = self.driver 
        driver.implicitly_wait(time2)
        print u'登录成功'
        return driver.find_element_by_partial_link_text(u"欢迎您").is_displayed()

    def exitSystem(self):
        driver = self.driver
        driver.find_element_by_xpath("/html/body/div[3]/div/ul/li[3]/ul/li[3]/a").click()       
            