# -*- coding: utf-8 -*-
import sys
reload(sys)
sys.setdefaultencoding('utf8')
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re, os
import HTMLTestRunner
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
sys.path.append("/var/lib/jenkins/workspace/emao/public")
from Send import send
from logincsy import Login
from ElementOperation import ElementOperation

class CityStation(unittest.TestCase):
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
        self.driver.get('http://dealer.emao.com/beijing/')
        self.driver.maximize_window()
        login = Login(self.driver)
        login.login()
        login.getName()
        
    def test_citystation_login(self):
        driver = self.driver
        now_handle = driver.current_window_handle #获取当前窗口句柄
        ElementOperate = ElementOperation(driver)
          
        print u"开始点击加载更多验证***********************************"
        ElementOperate.Selector('#news_tuijian_div a')
        time.sleep(2)
        ElementOperate.Selector('#news_tuijian_div a')
        assertList = ['http://dealer.emao.com/beijing/hangqing/list-5-0-0-0_1.html']
        ElementOperate.switch_to_window(now_handle,assertList)   
        print u"结束点击加载更多验证***********************************"

        print u"开始点击轮播图验证*************************************"
        #driver.get('http://dealer.emao.com/beijing/')
        ElementOperate.Mouse('.focus-picture',0)
        if not driver.find_element_by_class_name("global-bn_arr_r").is_displayed():
             print "请先把鼠标移动到轮播图"
        else:
             print "可以点击下一张"
                  
        driver.execute_script("window.scrollBy(0,-document.body.scrollHeight)","")
        print '***'
        time.sleep(2)
        ElementOperate.Mouse('global-bn_arr_r',1)
        for i in range(10):
              time.sleep(0.8)
              ElementOperate.Mouse('global-bn_arr_r',1)
        ElementOperate.Ergodic('.focus-picture a',None)
        assertList = ['一猫供稿','北京推荐经销商']
        ElementOperate.switch_to_window(now_handle,assertList)
        print u"结束点击轮播图验证*************************************"
          
        print u"开始验证本地车市***************************************"
        ElementOperate.Partial_link( '本地车市')
        assertList = ['商家查询','车型详情','降价提醒','对比','报价查询']
        ElementOperate.Assert(assertList)
        print u"结束验证本地车市***************************************"    
          
        print u"开始验证车型对比***************************************"
        ElementOperate.CSSList( '.checkbox', 0)
        ElementOperate.CSSList( '.checkbox', 2)
        ElementOperate.CSSList( '.checkbox', 5)
        #ElementOperate.Selector( '.click-db-index')
        li = driver.find_elements_by_css_selector('.comparison-box li')
        print len(li)
        driver.find_element_by_css_selector('.click-db-index').click()
        assertList = ['排量','功率','规格','车型名称','厂商指导价']
        ElementOperate.switch_to_window(now_handle,assertList)
        print u"结束验证车型对比***************************************" 
          
        print u"开始验证经销商*****************************************"
        ElementOperate.Selector( '#dealer_nav a')
        for i in range(3):  
              ElementOperate.Classname('next')
              time.sleep(2)
        ElementOperate.CSSList('#case .case .case-text a',3)
        assertList = ['经销商首页','车型报价','联系我们','公司介绍']
        ElementOperate.switch_to_window(now_handle, assertList)
        print u"结束验证经销商*****************************************"

        print u"开始验证资讯行情***************************************"
        ElementOperate.Selector( '#hangqing_nav a')
        assertList = ['http://dealer.emao.com/beijing/hangqing/list-5-0-0-0_1.html','一猫推荐','北京推荐经销商']
        ElementOperate.Assert(assertList)
        for i in range(3):
              ElementOperate.Classname('next')
              time.sleep(2)
        ElementOperate.CSSList('.list-texts-con a',3)  
        assertList = ['经销商加盟','北京推荐经销商']
        ElementOperate.switch_to_window(now_handle,assertList,) 
        print u"结束验证资讯行情****************************************"
        
        driver.get('http://www.emao.com')
        if not driver.find_element_by_class_name('join').is_displayed() :
            print u'首页城市站链接没有加载'
    
    def tearDown(self):
        self.driver.quit()
    
