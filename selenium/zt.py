# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest,time, re, os
import HTMLTestRunner
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
import sys
reload(sys)
sys.setdefaultencoding('utf-8')
sys.path.append("/var/lib/jenkins/workspace/emao/public")
from Send import send
from logincsy import Login
from ElementOperation import ElementOperation


class Subject(unittest.TestCase):
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
        self.driver.get("http://www.emao.com/zt/")
        self.driver.maximize_window()
        login = Login(self.driver)
        login.login()
        login.getName()
    def test_subject_login(self):
        driver = self.driver
        now_handle = driver.current_window_handle #获取当前窗口句柄
        ElementOperate = ElementOperation(driver)

        print (u"开始验证轮播图跳转******************************")
        ElementOperate.Mouse( 'next', 1)
        for i in range(10):
                time.sleep(0.5)
                ElementOperate.Mouse( 'next', 1)
        ElementOperate.Ergodic( '.car-news-wrap img', None)
        assertList = ['评论','车型库','经销商']
        ElementOperate.switch_to_window(now_handle,assertList)
        print (u"结束验证轮播图跳转******************************")


        print (u"开始验证列表详情********************************")
        ElementOperate.CSSList('.info-info img',5)
        assertList = ['评论','我要评论']
        ElementOperate.switch_to_window(now_handle,assertList)
        print (u"结束验证列表详情********************************")
    def tearDown(self):
        self.driver.quit()
