# -*- coding: utf-8 -*-
import sys
import xlrd  
import os
from selenium import webdriver
import Login
import ElementOperation
from selenium.webdriver.common.action_chains import ActionChains



reload(sys)
sys.setdefaultencoding("utf-8")
import unittest    
import ddt 
time2 = 60
count = 1

def execute_case(driver,element,operate):
    elementList = element.split('\r\n')
    operateList = operate.split('\r\n')
    now_handle = driver.current_window_handle #获取当前窗口句柄
    global count
    for ope in operateList:
        if ope =='click':
            for ele in elementList:        
                if ele >= u'\u4e00' and ele<=u'\u9fa5':
                            #Partial_link(driver,element)
                            driver.find_element_by_partial_link_text(ele).click()
                            if len(driver.window_handles) > count:
                                count = count + 1
                                ElementOperation.switch_to_window(driver, now_handle)
                            break
                elif ele.startswith('#') or ele.startswith('.'):
                            #Selector(driver,element)
                            driver.find_element_by_css_selector(ele).click()
                            if len(driver.window_handles) > count:
                                count = count + 1
                                ElementOperation.switch_to_window(driver, now_handle)
                            break
                elif '/' in ele or '*' in ele:
                            driver.find_element_by_xpath(ele).click()
                            if len(driver.window_handles) > count:
                                count = count + 1
                                ElementOperation.switch_to_window(driver, now_handle)
                            break
                else:
                            driver.find_element_by_id(ele).click()
                            if len(driver.window_handles) > count:
                                count = count + 1
                                ElementOperation.switch_to_window(driver, now_handle)
                            break
                    
        elif ope == 'send_keys':
            pass   
         
        elif ope == u'移动鼠标':
            for ele in elementList:
                if ele.startswith('#') or ele.startswith('.'):
                    driver.implicitly_wait(time2)
                    img = driver.find_element_by_css_selector(ele)
                    actions = ActionChains(driver)
                    actions.move_to_element(img)
                    actions.perform()
                    print u"鼠标 已移动到轮播图"
                    driver.implicitly_wait(time2)
                    
                    return driver.find_element_by_class_name("global-bn_arr_r").is_displayed()
                    break