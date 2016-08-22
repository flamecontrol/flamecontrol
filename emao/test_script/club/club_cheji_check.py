#coding=utf-8
'''
Created on 2015年9月11日
modified on 2015年9月28日
@fun：验证【社区首页】回归验证
@environment：通用环境
'''

from seleniumDEMO import webdriver
from seleniumDEMO.webdriver.common.by import By
from seleniumDEMO.webdriver.common.keys import Keys
from seleniumDEMO.webdriver.support.ui import Select
from seleniumDEMO.common.exceptions import NoSuchElementException
from seleniumDEMO.common.exceptions import NoAlertPresentException
import unittest, time, re, os
import HTMLTestRunner
from seleniumDEMO.webdriver.common.action_chains import ActionChains
import sys 
reload(sys) 
sys.setdefaultencoding('utf8')
import sys
sys.path.append("/var/lib/jenkins/workspace/emao/public")
import wait_time,ym_check_link,check_single_link
from logincsy import Login

class club_cheji_check(unittest.TestCase):
    def setUp(self):
        self.url= 'http://club.emao.com' 
        #self.firefoxBin = os.path.abspath(r"D:\Program Files\Mozilla Firefoxfirefox.exe")
        #os.environ["webdriver.firefox.bin"] = self.firefoxBin
        #self.driver = webdriver.Remote("http://localhost:4444/wd/hub",desired_capabilities=DesiredCapabilities.HTMLUNIT)
        #self.profileDir = "D:\Program Files\Mozilla Firefox\MozillaProfile"
        #self.profile = webdriver.FirefoxProfile(self.profileDir)
        #self.driver = webdriver.Firefox(self.profile)
        self.driver = webdriver.PhantomJS()
        wait_time.wait_time(self.driver,1,60)
        self.driver.maximize_window()
        self.driver.get(self.url)
        login = Login(self.driver)
        login.login()
        login.getName()
        self.driver.get(self.url)
        self.verificationErrors = []

        #是否继续接受下一个警告
        self.accept_next_alert = True
 
  
    def test_club_cheji_index_link(self):
        u'''验证【社区首页】链接'''        
        print u'开始验证【社区首页】链接：'
        club_url='http://club.emao.com'
        check_single_link.check_single_link(club_url)
        print u'验证【社区首页】链接结束！'
        print '*********************************************************'
        print u'开始验证轮播图链接：'
        lunbo_images=self.driver.find_elements_by_css_selector('.pic-group img')            
        current_handle = self.driver.current_window_handle
        for image in lunbo_images:
            if image.is_displayed():
                image.click()
                wait_time.wait_time(self.driver,1,60) 
                all_handles=self.driver.window_handles
                for handle in all_handles:
                    if handle != current_handle:
                        self.driver.switch_to_window(handle)             
                        lunbo_image_url= self.driver.current_url
                        check_single_link.check_single_link(lunbo_image_url)
                        wait_time.wait_time(self.driver,1,60)
                        self.driver.close()
                        self.driver.switch_to_window(current_handle)   
                        break
                break
        print u'验证轮播图链接结束！'
        print '*********************************************************'

        print u'开始验证【攻略首页】链接：'
        gonglue_url='http://club.emao.com/mygl/'
        check_single_link.check_single_link(gonglue_url)
        print u'验证【攻略首页】链接结束！' 
        print '*********************************************************'
        u'''验证【小组首页】链接''' 
        print u'开始验证【小组首页】链接：'
        group_url='http://club.emao.com/group.php'
        check_single_link.check_single_link(group_url)
        print u'验证【小组首页】链接结束！'

    def test_club_cheji_list_link(self):
        u'''验证【车记列表】链接'''  
        print u'开始验证【车记列表】链接：'
        cheji_list_links=self.driver.find_elements_by_css_selector('.tab-item dl dd h4 a')
        for cheji_link in cheji_list_links:
            print u'车记标题：'+cheji_link.get_attribute('title')
            check_single_link.check_single_link(cheji_link.get_attribute('href'))

        print u'验证【车记列表】链接结束！'           

 
    #tearDown方法在每个测试方法执行后调用，该方法完成所有测试用例执行完成的清理工作
    def tearDown(self):
        self.driver.close()
        #对前面的verificationErrors方法获得的列表进行比较，如果列表不为空，则输出列表中的报错信息
        self.assertEqual([], self.verificationErrors)
#unittest.main（）函数用来测试类中以test开头的测试用例
