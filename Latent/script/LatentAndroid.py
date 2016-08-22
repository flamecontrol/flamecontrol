from AppiumTest import webdriver
import unittest
import sys
import os
import xlrd
import HTMLTestRunner
sys.path.append("../utils")
sys.path.append("../data")


fname = "../data/testdata.xlsx"
settingsheet = u"setting"
testsheet = u"testcase"


class LatentAndroid(unittest.TestCase):
    def setUp(self):
        # self.setdata()
        # desired_caps = {}
        # desired_caps['platformName'] = self.setting.cell_value(5,1)
        # desired_caps['platformVersion'] = '4.2'
        # desired_caps['deviceName'] = 'Android Emulator'
        # desired_caps['appPackage'] = 'com.emao.autonews'
        # desired_caps['appActivity'] = 'com.emao.autonews.ui.HomeActivity'
        # # 启动实例
        # driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)
        pass

    def testcase(self):
        print("why")
        # print(self.setting.cell_value(5,1))
        pass

    def setdata(self):
        # self.data = xlrd.open_workbook(fname)
        # self.setting = self.data.sheet_by_name(settingsheet)
        # self.testdata = self.data.sheet_by_name(testsheet)
        pass


