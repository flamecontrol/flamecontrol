# coding=utf-8
from selenium import webdriver
import sys
import unittest
import os
import xlrd
import HTMLTestRunner
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

sys.path.append("../utils")
sys.path.append("../data")
by = By()

fname = "../data/testdata.xlsx"
settingsheet = u"setting"
testsheet = u"testcase"

class LatentWeb(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        url = 'https://m.opifices.com'
        self.driver.get(url)
        self.driver.maximize_window()
        self.wait_element = WebDriverWait(self.driver, 10, 0.5)
        pass

    def testcase(self):
        self.wait_element.until(
            EC.text_to_be_present_in_element((By.XPATH, "//span[text()='新加入项目']"), "资料提交审核成功")
        ).click()

        method = 'click'
        mby = getattr(by, 'ID')

        el = getElement(mby, value, index)       # TODO

        el.getattr(self.driver, method)()       # TODO

        self.assertEquals('1', '12')    # 111
        self.assertEquals('1', '12')    # 111


    def testcase1(self):
        self.assertEquals('1', '13')
        self.assertEquals('1', '13')

    def getElement(self, mby, value, index):
        el = self.driver.find_elements(by, value)[index]
        return el
