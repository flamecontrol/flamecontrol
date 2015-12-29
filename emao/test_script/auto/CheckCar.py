# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re, os,sys
import HTMLTestRunner
from selenium.webdriver.common.action_chains import ActionChains
sys.path.append("../../public")
from Send import send
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from logincsy import Login
from ElementOperation import ElementOperation
reload(sys)
sys.setdefaultencoding('utf-8')

class CheckCar(unittest.TestCase):
    def setUp(self):   
        # self.firefoxBin = os.path.abspath(r"D:\Program Files\Mozilla Firefoxfirefox.exe")
        # os.environ["webdriver.firefox.bin"] = self.firefoxBin
        # self.driver = webdriver.Remote("http://localhost:4444/wd/hub",desired_capabilities=DesiredCapabilities.HTMLUNIT)
        # self.profileDir = "D:\Program Files\Mozilla Firefox\MozillaProfile"
        # self.profile = webdriver.FirefoxProfile(self.profileDir)
        # self.driver = webdriver.Firefox(self.profile)
        # self.driver = webdriver.Firefox()
        # self.driver = webdriver.PhantomJS()
        # self.driver.implicitly_wait(30)
        # self.driver.get("http://auto.emao.com/")
        # self.driver.maximize_window()
        # login = Login(self.driver)
        # login.login()
        # login.getName()
        pass
    def testCheckCar(self):
        print 0
        # u'''进入选车'''
        # driver = self.driver#可注释此句查看报告报错情况
        # now_handle = driver.current_window_handle #获取当前窗口句柄
        # ElementOperate = ElementOperation(driver)
        #
        # first_url = "http://auto.emao.com/"
        # second_url = "http://auto.emao.com/pic/"
        # third_url = "http://auto.emao.com/xuancheshenqi_1.html"

        # print (u"开始验证选车********************************")
        #
        # driver.get(first_url)
        # # ElementOperate.Partial_link('选车')
        # assertList = ['http://auto.emao.com/','热销车型','平行进口车','热门图片']
        # ElementOperate.switch_to_window(now_handle,assertList)
        # print (u"结束验证选车********************************")
        
        # driver.get(second_url)
        # assert(u"汽车图片大全" in driver.title)
        # assert(u"最新图片" in driver.page_source)
        # time.sleep(2)
        # driver.get(third_url)
        # assert(u"选车神器" in driver.title)
        # assert(u"开始计算" in driver.page_source)
        # time.sleep(2)
        # driver.back()
        # time.sleep(2)
        # driver.back()
        #
        # ElementOperate.Selector( '.g-custom .g-creyi')#点击自定义价格
        # source = driver.find_element_by_css_selector(".btn-left")#滚动条源地址
        # time.sleep(5)
        #
        # targetList = driver.find_elements_by_css_selector(".slider-ul i")#滚动条目标地址
        # actionChains = ActionChains(driver)
        # actionChains.drag_and_drop(source,targetList[5]).perform() #鼠标拖拽
        # #assert(u"25万以上" in driver.title)
        # #assert(u"25万以上" in driver.page_source)
        # driver.back()
        # ElementOperate.Selector('.show-btns')#展开血统配置座椅
        # ElementOperate.Ergodic( '.g-repaione a', '别克')
        # ElementOperate.Ergodic('.fast-car-box1 a', '美国')
        # ElementOperate.Ergodic('.fast-car-box1 a', '五座')
        # ElementOperate.Selector( '.car-tit .active')
        # ElementOperate.CSSList('.car-modle img', 3)
        # assertList = ['综述','猫友印象']
        # ElementOperate.switch_to_window(now_handle,assertList)
        # driver.back()
        #
        # print "已返回车型主页"

    def test1(self):
        print 1

    def test2(self):
        print 2

    def tearDown(self):
        # self.driver.quit()
        pass
