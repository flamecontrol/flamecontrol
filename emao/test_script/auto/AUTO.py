# -*- coding: utf-8 -*-
#coding=utf-8
from selenium import webdriver
import unittest
import HTMLTestRunner

class AUTO(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        # EO = ElementOperation(driver)
        # pass
    def chicklink(self,url,text):
        href = self.driver.find_element_by_link_text(text).get_attribute("href")
        assert(url in href)
        self.linkdetail(url)
        print(u"验证通过：" + text)

    def linkdetail(self,url):
        print(url)

    def AUTO001(self):
        print("AUTO001")
        url = "http://auto.emao.com/"
        self.driver.get(url)
        self.chicklink("http://auto.emao.com/",u"车型")
        self.chicklink("http://auto.222222222222emao.com/pic/",u"车型图库")
        self.chicklink("http://auto.emao.com/xuancheshenqi_1.html",u"选车神器")
        self.chicklink("http://www.emao.com/",u"首页")
        self.chicklink("http://news.emao.com/",u"资讯")
        self.chicklink("http://auto.emao.com/",u"车型库")
        self.chicklink("http://mall.emao.com/",u"新车商城")
        self.chicklink("http://www.handaishu.com/",u"平行进口车")
        self.chicklink("http://dealer.emao.com/beijing/list-0-0-0_1.html",u"经销商")


    def test1(self):
        print 1

    def test2(self):
        print 2

    def tearDown(self):
        self.driver.quit()
        # pass
