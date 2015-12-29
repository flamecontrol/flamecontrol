# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re,os
import HTMLTestRunner
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
import sys
reload(sys)
sys.setdefaultencoding('utf-8')
sys.path.append("/var/lib/jenkins/workspace/emao/public")
from ElementOperation import ElementOperation
from logincsy import Login

class news(unittest.TestCase):
    def setUp(self):
        #self.firefoxBin = os.path.abspath(r"D:\Program Files\Mozilla Firefoxfirefox.exe")
        #os.environ["webdriver.firefox.bin"] = self.firefoxBin
        #self.driver = webdriver.Remote("http://localhost:4444/wd/hub",desired_capabilities=DesiredCapabilities.HTMLUNIT)
        #self.profileDir = "D:\Program Files\Mozilla Firefox\MozillaProfile"
        #self.profile = webdriver.FirefoxProfile(self.profileDir)
        #self.driver = webdriver.Firefox(self.profile)
        #self.driver = webdriver.Firefox()
        self.driver = webdriver.PhantomJS()
        self.driver.implicitly_wait(30)
        self.driver.get("http://news.emao.com/")
        self.driver.maximize_window()
        login = Login(self.driver)
        login.login()
        login.getName()
    def testnews(self):
        driver = self.driver
        now_handle = driver.current_window_handle #获取当前窗口句柄
        ElementOperate = ElementOperation(driver)

        print (u"开始验证轮播图**********************************")
        ElementOperate.Ergodic('.car-news-wrap .car-news-group img', None)
        assertList = ['评论']
        ElementOperate.switch_to_window(now_handle,assertList)     
        print (u"结束验证轮播图**********************************")

        print (u"开始验证列表************************************")
        ElementOperate.CSSList( '.mod-tt .select a', 2)
        assertList = ['http://news.emao.com/pingce/list.html#3','评测_一猫汽车资讯_一猫汽车网']
        ElementOperate.CSSList('.mod-tt .select a', 7)
        assertList = ['http://news.emao.com/pingce/list.html#3','评测_一猫汽车资讯_一猫汽车网']
        print (u"结束验证列表************************************")

        print (u"开始验证新闻详情************************************")
        ElementOperate.CSSList('.mod-tt .select a', 2)
        ElementOperate.CSSList('.mod-item .list-img a',1)
        assertList = ['一猫汽车网','车型动态']
        ElementOperate.switch_to_window(now_handle,assertList) 
        print (u"结束验证新闻详情************************************") 

        print (u"开始验证买家关注************************************") 
        ElementOperate.CSSList('.user-follow a', 1)
        assertList = ['买家关注','热门品牌','热门专题','热']
        ElementOperate.switch_to_window(now_handle,assertList) 
        print (u"结束验证买家关注************************************")  

        print (u"开始验证热门专题************************************")
        ElementOperate.CSSList('.sidebar-ad img', 1)
        assertList = ['猫友评论']
        ElementOperate.switch_to_window(now_handle,assertList)         
        print (u"结束验证热门专题************************************")   

        print (u"开始验证本地车市************************************")
        if 'right_bdcs' in driver.page_source:
            ElementOperate.CSSList('#right_bdcs a',0) 
            assertList = ['快速查找','本地车市','经销商查询','http://dealer.emao.com/beijing/']
            ElementOperate.switch_to_window(now_handle,assertList)               
        else:
            print u"本地车市没有加载" 
        print (u"结束验证本地车市************************************") 

        print (u"开始验证热门品牌************************************")
        ElementOperate.CSSList( '.news-r a', 9)
        assertList = ['品牌广场','知豆','http://brand.emao.com/']
        ElementOperate.switch_to_window(now_handle,assertList)
        driver.get('http://brand.emao.com/')
        ElementOperate.Ergodic( '.origin-box-left a', '福特')
        assertList = ['福特品牌故事','所有车型','http://brand.emao.com/32/']
        ElementOperate.switch_to_window(now_handle,assertList)
        driver.back()
        print (u"结束验证热门品牌************************************")
    def tearDown(self):
        self.driver.quit()
