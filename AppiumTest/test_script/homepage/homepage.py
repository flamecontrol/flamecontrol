# -*- coding: utf-8 -*-
#coding=utf-8
import unittest
import sys
sys.path.append("../../utils")
from ScreenShot import screen

s = screen()
class HOME(unittest.TestCase):
    def setUp(self):
        # desired_caps = {}
        # desired_caps['platformName'] = 'Android'
        # desired_caps['platformVersion'] = '4.2'
        # desired_caps['deviceName'] = 'Android Emulator'
        # desired_caps['appPackage'] = 'com.emao.autonews'
        # desired_caps['appActivity'] = 'com.emao.autonews.ui.HomeActivity'
        # # 启动实例
        # driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)
        pass


    def test1(self,text):
        print(text)

    def testcase(self):
        # driver.find_elements_by_class_name("android.widget.TextView")[0].click() # 全部
        # driver.find_elements_by_class_name("android.widget.TextView")[2].click() # 评测
        # driver.find_elements_by_xpath("//android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.ImageView")[0].click() # 轮播图图片
        # s.snapshot("test001.jpg")
        # driver.keyevent(4) # 后退

    def testsearch(self):
        # driver.find_element_by_id("btn_search").click()    # 搜索
        # driver.find_element_by_id("edittext").send_keys("汽车") # 输入有点问题
        # driver.find_element_by_id("poptext").click() # 设置搜索类型
        # driver.find_element_by_id("bar_right_btn").click() # 关闭搜索
        # driver.find_elements_by_id("value")[0].click() # 第一条历史记录
        # driver.find_element_by_id("clear").click() # 关闭搜索