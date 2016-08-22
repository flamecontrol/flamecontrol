from appium import webdriver
import time


desired_caps = {}
desired_caps['platformName'] = 'Android'
desired_caps['platformVersion'] = '6.0'
desired_caps['deviceName'] = '1b323440'
desired_caps['appPackage'] = 'com.entstudy.video'
desired_caps['appActivity'] = 'com.entstudy.video.activity.SplashActivity'
# 启动实例
driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)
time.sleep(5)

x = '//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]'
el = driver.find_element_by_xpath(x)
el.click()