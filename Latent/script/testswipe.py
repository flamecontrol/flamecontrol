# -*- coding: utf-8 -*-
#coding=utf-8
from appium import webdriver
import unittest
from time import sleep



desired_caps = {}
desired_caps['platformName'] = 'Android'
desired_caps['platformVersion'] = '4.4.4'
desired_caps['deviceName'] = 'Android Emulator'
desired_caps['appPackage'] = 'com.entstudy.enjoystudy'
desired_caps['appActivity'] = 'com.entstudy.enjoystudy.activity.SplashActivity'
# 启动实例
driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)


sleep(10)
print("kaishi")

# driver.swipe(300,600,300,300)

driver.find_element_by_name("圈子").click()

driver.find_element_by_name("积分商城").click()

# driver.find_element_by_name("").click()
# driver.find_element_by_name("").click()
# driver.find_element_by_name("").click()


sleep(10)
print("kaishi2")

# driver.tap()

c = driver.contexts

print(c)
print(c[1])
print(c[-1])

# driver.switch_to_window(c[1])
driver.switch_to.context(c[1])
# driver.context(c[1])


driver.swipe(300,600,300,300)



