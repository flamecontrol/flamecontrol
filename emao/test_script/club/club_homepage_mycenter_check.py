#coding=utf-8
'''
Created on 2015年9月11日
modified on 2015年9月28日
@fun：验证【社区-个人中心】回归验证
@environment：线上环境
'''

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re, os
import HTMLTestRunner
from selenium.webdriver.common.action_chains import ActionChains
import sys 
reload(sys) 
sys.setdefaultencoding('utf8')

import sys
sys.path.append("/var/lib/jenkins/workspace/emao/public")
from logincsy import Login
import wait_time, ym_check_link,check_single_link

class club_homepage_mycenter_check(unittest.TestCase):
    def setUp(self):
        self.url= 'http://club.emao.com' 
        #self.firefoxBin = os.path.abspath(r"D:\Program Files\Mozilla Firefoxfirefox.exe")
        #os.environ["webdriver.firefox.bin"] = self.firefoxBin
        #self.driver = webdriver.Remote("http://localhost:4444/wd/hub",desired_capabilities=DesiredCapabilities.HTMLUNIT)
        #self.profileDir = "D:\Program Files\Mozilla Firefox\MozillaProfile"
        #self.profile = webdriver.FirefoxProfile(self.profileDir)
        #self.driver = webdriver.Firefox(self.profile)
        self.driver = webdriver.PhantomJS()
        wait_time.wait_time(self.driver,1,60)
        self.driver.maximize_window()
        self.driver.get(self.url)
        self.driver.maximize_window()
        self.verificationErrors = []

        #是否继续接受下一个警告
        self.accept_next_alert = True

    def test_club_mycenter_index_link(self):
        u'''验证【个人中心首页】链接'''  
        print u'开始验证【用户登录】：'
        login = Login(self.driver)
        login.login()
        login.getName()        
        print u'验证【用户登录】结束！'
        print '*****************************************************************************'
        print u'开始验证【个人中心首页-最佳阅读】链接：'        
        wait_time.wait_time(self.driver,1,60)
        self.driver.find_element_by_partial_link_text(u'欢迎您').click()
        wait_time.wait_time(self.driver,1,60)
        first_image=self.driver.find_element_by_css_selector('.car-ucenter-ct-reading-pic li:first-child a') 
        first_image_url=first_image.get_attribute('href') 
        check_single_link.check_single_link(first_image_url)    
        print u'【个人中心首页-最佳阅读】链接验证结束！'
        print '*****************************************************************************'
        print u'开始验证【个人中心首页-平行进口车】链接：'
        handaishu_image=self.driver.find_elements_by_css_selector('.car-ucenter-ct-import-box a') 
        handaishu_image_url=first_image.get_attribute('href') 
        check_single_link.check_single_link(handaishu_image_url)    
        print u'【个人中心首页-平行进口车】链接验证结束！'

   
    def test_club_mycenter_leftmenu_link(self):
        u'''验证【个人中心左侧导航】链接'''
        print u'开始验证【个人中心左侧导航】链接：'
        login = Login(self.driver)
        login.login()
        login.getName()  
        wait_time.wait_time(self.driver,1,60)
        self.driver.find_element_by_partial_link_text(u'欢迎您').click()
        wait_time.wait_time(self.driver,1,60)
        
        mycenter_leftmenu_links=self.driver.find_elements_by_css_selector('.car-ucenter-sidebar ul li a')
        for leftmenu_link in mycenter_leftmenu_links:
            print leftmenu_link.get_attribute('text')
            check_single_link.check_single_link(leftmenu_link.get_attribute('href'))
        print u'验证【个人中心左侧导航】链接结束！'           

    #tearDown方法在每个测试方法执行后调用，该方法完成所有测试用例执行完成的清理工作
    def tearDown(self):
        self.driver.close()
        #对前面的verificationErrors方法获得的列表进行比较，如果列表不为空，则输出列表中的报错信息
        self.assertEqual([], self.verificationErrors)
#unittest.main（）函数用来测试类中以test开头的测试用例