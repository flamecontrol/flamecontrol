# coding=utf-8
import sys
import time
from seleniumDEMO import webdriver
from seleniumDEMO.webdriver.common.action_chains import ActionChains
from time import ctime


driver = webdriver.Firefox()
driver.get("https://opifices.com/#/approval_content/startup")

time.sleep(1)

tt = driver.implicitly_wait(3)

# el = driver.find_element_by_css_selector('span[ng-if="!vm.approval.idcard_pic"]')

# print(el)

# el.click()
el = driver.find_element_by_css_selector('input[name="idcard"]')
time.sleep(1)

el.send_keys('/Users/gaojie/Documents/123.jpg')


time.sleep(10)
# driver.quit()

