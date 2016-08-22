# -*- coding: utf-8 -*-
import sys
reload(sys)
sys.setdefaultencoding('utf8')
from seleniumDEMO import webdriver
from seleniumDEMO.webdriver.common.by import By
from seleniumDEMO.webdriver.support.ui import Select
from seleniumDEMO.common.exceptions import NoSuchElementException
from seleniumDEMO.webdriver.common.action_chains import ActionChains
from seleniumDEMO.webdriver.common.desired_capabilities import DesiredCapabilities
import unittest, time, re , os ,logging,traceback

time1 = 60
sys.path.append("/var/lib/jenkins/workspace/emao/public")
from Send import send
 
log_filename = 'log_emao.log'
class ElementOperation(unittest.TestCase):  
    def __init__(self,driver):
        self.driver = driver 
    
    def ID(self,element,content):
        driver = self.driver
        try:
            driver.implicitly_wait(time1)
            driver.find_element_by_id(element).send_keys(content)
        except NoSuchElementException,e:
            c = send()
            c.people(str(e))  

    def ID_click(self,element):
        driver = self.driver
        try:
            driver.implicitly_wait(time1)
            driver.find_element_by_id(element).click()
        except NoSuchElementException,e:
            c = send()
            c.people(str(e))  

    def IDList(self,pro,text):
        driver = self.driver
        try:
            driver.implicitly_wait(time1)
            elementList = driver.find_element_by_id(pro)
            select = Select(elementList)
            select.select_by_visible_text(text)    
        except NoSuchElementException,e:
            c = send()
            c.people(str(e)) 
        
    def Selector(self,element):
        driver = self.driver
        try:
            driver.implicitly_wait(time1)
            driver.find_element_by_css_selector(element).click()
        except NoSuchElementException,e:
            c = send()
            c.people(str(e)) 
        
    def CSSList(self,element,index):
        driver = self.driver
        try:
            driver.implicitly_wait(time1)
            List = driver.find_elements_by_css_selector(element)
            print len(List)
            List[index].click()
        except IndexError,e:
            c = send()
            c.people(str(e)) 
        except NoSuchElementException,e:
            c = send()
            c.people(str(e)) 

    def Classname(self,element):
        driver = self.driver
        try:
            driver.implicitly_wait(time1)
            driver.find_element_by_class_name(element).click()
        except NoSuchElementException,e:
            c = send()
            c.people(str(e)) 

    def Ergodic(self,element,content):
        driver = self.driver
        try:
            if content != None:
                driver.implicitly_wait(time1)
                List = driver.find_elements_by_css_selector(element)
                for opt in List:
                    if content == opt.text:
                        opt.click()
                        break
            if content == None:
                newsList = driver.find_elements_by_css_selector(element)
                for opt in newsList:
                    if opt.is_displayed():
                        opt.click()
                        break
        except Exception, e:
            raise e
        
    def Xpath(self,element):
        driver = self.driver
        try:
            driver.implicitly_wait(time1)
            driver.find_element_by_xpath(element)
        except NoSuchElementException,e:
            c = send()
            c.people(str(e)) 

    def switch_to(self,element):
        driver = self.driver
        try:
            driver.implicitly_wait(time1)
            driver.switch_to.frame(element)
        except NoSuchElementException,e:
            c = send()
            c.people(str(e)) 
        
    def Link(self,element):
        driver = self.driver
        try:
            driver.implicitly_wait(time1)
            driver.find_element_by_link_text(element).click() 
        except NoSuchElementException,e:
            c = send()
            c.people(str(e)) 
        
    def Partial_link(self,element):
        driver = self.driver
        if element == None:
            c = send()
            c.people(u'元素没有加载请手动检查页面')
            driver.quit()
        else:
            try:
                driver.implicitly_wait(time1)
                driver.find_element_by_partial_link_text(element).click()
            except NoSuchElementException,e:
                c = send()
                c.people(str(e)) 

    def Mouse(self,element,switch):
        driver = self.driver 
        # 0表示移动鼠标 1表示左键点击
        try:
            if switch == 0:
                driver.implicitly_wait(time1)
                menu = driver.find_element_by_css_selector(element)
                actions = ActionChains(driver)
                actions.move_to_element(menu)
                actions.perform()
            elif switch == 1:
                driver.implicitly_wait(time1)
                next = driver.find_element_by_class_name(element)
                ActionChains(driver).click(next).perform()
        except NoSuchElementException,e:
            c = send()
            c.people(str(e)) 
        except Exception,e:
            c = send()
            c.people(str(e)) 

    def Assert(self,content):
        try:
            driver = self.driver 
            for ur in content:
                if ur >= u'\u4e00' and ur<=u'\u9fa5':
                    assert(ur in driver.page_source)  
                if 'http' in ur:
                    self.assertEquals(ur,driver.current_url)
        except AssertionError, e:
            traceback.print_exception(*sys.exc_info())#将异常信息打印在解释器上
            #以下是写入文件
            fp=open('/var/lib/jenkins/workspace/emao_git/test_report/log_emao.log',"w")
            traceback.print_exception(*sys.exc_info(),file=fp)
            fp.close()
            c = send()
            c.people(str(e))
        

    def switch_to_window(self,now_handle,assertList):
        driver = self.driver
        try:
            all_handles = driver.window_handles #获取所有窗口句柄
            print len(all_handles)
            if len(all_handles) == 2:
                for han in all_handles:
                    if han != now_handle:
                        driver.switch_to_window(han)
                        s = ElementOperation(driver)
                        s.Assert(assertList)
        except:
            traceback.print_exception(*sys.exc_info())#将异常信息打印在解释器上
            #以下是写入文件
            fp=open('E:\\log_emao.log',"w")
            traceback.print_exception(*sys.exc_info(),file=fp)
            fp.close()
               
        finally:
            time.sleep(2)
            driver.close() #关闭当前窗口
            time.sleep(1)
            driver.switch_to_window(now_handle) #返回主窗口              
  
                    
    
