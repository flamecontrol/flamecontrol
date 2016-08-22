#!/usr/bin/python
# -*- coding: utf-8 -*-
from seleniumDEMO import webdriver
from seleniumDEMO.webdriver.common.by import By
from seleniumDEMO.webdriver.common.keys import Keys
from seleniumDEMO.webdriver.support.ui import Select
from seleniumDEMO.common.exceptions import NoSuchElementException
from seleniumDEMO.common.exceptions import NoAlertPresentException
from seleniumDEMO.webdriver.common.action_chains import ActionChains
from seleniumDEMO.webdriver.common.desired_capabilities import DesiredCapabilities
import unittest
import sys,os
reload(sys)
sys.setdefaultencoding('utf-8')
sys.path.append("/var/lib/jenkins/workspace/emao/public")
from ElementOperation import ElementOperation
from Send import send
from logincsy import Login

class homepage(unittest.TestCase):
    def setUp(self):      
        
            #self.firefoxBin = os.path.abspath(r"D:\Program Files\Mozilla Firefoxfirefox.exe")
            #os.environ["webdriver.firefox.bin"] = self.firefoxBin
            #self.driver = webdriver.Remote("http://localhost:4444/wd/hub",desired_capabilities=DesiredCapabilities.HTMLUNIT)
            #self.profileDir = "D:\Program Files\Mozilla Firefox\MozillaProfile"
            #self.profile = webdriver.FirefoxProfile(self.profileDir)
            #self.driver = webdriver.Firefox(self.profile)
            self.driver = webdriver.PhantomJS()
            self.driver.implicitly_wait(30)
            self.driver.get("http://www.emao.com")
            self.driver.maximize_window()
            login = Login(self.driver)
            login.login()
            login.getName()

    def testhomepage(self):
        driver = self.driver
        now_handle = driver.current_window_handle #获取当前窗口句柄
        ElementOperate = ElementOperation(driver)
        
        print u"开始证浮层及品牌选车****************************"
        ElementOperate.Mouse('.brand-btn', 0)#鼠标移动浮层
        ElementOperate.CSSList('.letter a',18)#点击浮层首字母
        ElementOperate.CSSList('.brand-list a',23)#点击车系
        assertList = ['猫友口碑','综述','对比']
        ElementOperate.switch_to_window(now_handle,assertList)
        print u"结束证浮层及品牌选车****************************"
        
        print "开始价格选车*************************************"
        ElementOperate.CSSList('.infos a',3)
        assertList = ['车型','品牌','热度','http://auto.emao.com/xuanche-8-0-0-0-0-0-0-0_0-0-1.html']
        ElementOperate.switch_to_window(now_handle,assertList)   
        print "结束价格选车*************************************"

        
        print "开始类型选车*************************************"
        ElementOperate.CSSList('.info a',-4)
        assertList = ['车型','品牌','热度','http://auto.emao.com/xuanche-0-0-8-0-0-0-0-0_0-0-1.html']
        ElementOperate.switch_to_window(now_handle,assertList)   
        print "结束类型选车*************************************"

        print "开始精准选车*************************************"
        ElementOperate.ID_click("accurateBtn")
        assertList = ['猫友口碑','综述','对比','http://auto.emao.com/8/']
        ElementOperate.switch_to_window(now_handle,assertList)
        print "结束精准选车*************************************"
        
        print u"开始验证城市*************************************"
        ElementOperate.Mouse(".city",0)#鼠标移动浮层
        ElementOperate.Selector(".city .arrow-down")
        ElementOperate.Ergodic( ".city-bd a", "江苏")
        ElementOperate.Ergodic( ".city-bd-choose a",'常州')#切换到常州
        ElementOperate.ID_click( 'tj-car-market_url')#进入新车商城
        assertList = ['商城首页','订单','特价车源','http://mall.emao.com/city/beijing/']
        ElementOperate.switch_to_window(now_handle,assertList)
        ElementOperate.Selector(".city .arrow-down")
        ElementOperate.Ergodic( ".city-bd a", "北京")
        ElementOperate.Ergodic( ".city-bd-choose a",'北京')#切换到北京    
        print u"结束验证城市*************************************"
        
        print "开始验证新车导购*********************************"
        ElementOperate.CSSList( '.newcar-box a', 5)
        assertList = ['车型动态','评论','相关文章']
        ElementOperate.switch_to_window(now_handle,assertList) 
        print "结束验证新车导购*********************************"
        
        print "开始验证本地车市*********************************" 
        ElementOperate.CSSList('.box1-left .sale-brand .car-brand img', 1)
        assertList = ['基本信息','查询']
        ElementOperate.switch_to_window(now_handle,assertList)
        ElementOperate.CSSList('.showPic .list a', 5)#点击进入本地行情
        assertList = ['经销商加盟','北京推荐经销商','一猫供稿']
        ElementOperate.switch_to_window(now_handle,assertList)
        ElementOperate.CSSList('.showPic .box1-left .local-markets .address a', 2)#点击进入本地经销商
        assertList = ['公司介绍','经销商地图']
        ElementOperate.switch_to_window(now_handle,assertList)
        print "结束验证本地车市*********************************"  
        
        print "开始验证加载更多内容*****************************"
        ElementOperate.Selector( '.load-more')
        ElementOperate.Selector('.load-more')#再次点击加载更多内容
        ElementOperate.CSSList('.load-more', 1)
        assertList = ['http://news.emao.com/xinche/list.html#3']
        ElementOperate.switch_to_window(now_handle,assertList)
        print "结束验证加载更多内容*****************************"
        
        print "开始验证购车资讯*********************************"
        ElementOperate.CSSList('.clearfix .boxs img', 4)
        assertList = ['车型动态','收藏','相关文章']
        ElementOperate.switch_to_window(now_handle,assertList)
        ElementOperate.CSSList('.clearfix .bt a', 1)
        assertList = ['车型动态','评论','相关文章']
        ElementOperate.switch_to_window(now_handle,assertList)                
        print "结束验证购车资讯*********************************"
        
        print "开始验证底层二维码*******************************"
        ElementOperate.CSSList( '.qr-code img', 0)
        assertList = ['http://app.emao.com/','一猫移动应用下载中心']
        ElementOperate.switch_to_window(now_handle,assertList)         
        print "结束验证底层二维码*******************************"
        
    def tearDown(self):
        self.driver.quit()
