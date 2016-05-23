#coding=utf-8
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
import unittest, time, re
import HTMLTestRunner
class Baidu(unittest.TestCase):
    def setUp(self):
        pass

    def test_baidu_search(self):
        print(1)

    def tearDown(self):
        pass

if __name__ == "__main__":
    testunit = unittest.TestSuite()
    testunit.addTest(Baidu("test_baidu_search"))
    filename = "result1.html"
    fp = open(filename, 'wb')

    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,
                                           title=u'百度搜索测试报告',
                                           description=u'用例执行情况：')

    runner.run(testunit)