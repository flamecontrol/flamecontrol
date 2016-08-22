from AppiumTest import webdriver
from time import sleep

desired_caps = {}
desired_caps['platformName'] = 'Android'
desired_caps['platformVersion'] = '4.2'
desired_caps['deviceName'] = 'Android Emulator'
desired_caps['appPackage'] = 'com.entstudy.video'
desired_caps['appActivity'] = 'com.entstudy.video.activity.SplashActivity'
# 启动实例
driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)

sleep(5)
driver.find_element_by_name("取消").click()
driver.find_element_by_name("个人中心").click()
driver.find_element_by_name("设置").click()
driver.find_element_by_name("个人资料").click()
driver.find_element_by_name("保存").click()
driver.find_element_by_class_name("android.widget.ImageButton").click()