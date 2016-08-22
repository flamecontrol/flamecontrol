from AppiumTest import webdriver
import time
# 设置信息
desired_caps = {}
desired_caps['platformName'] = 'Android'
desired_caps['platformVersion'] = '6.0'
desired_caps['deviceName'] = '1b323440'
desired_caps['appPackage'] = 'com.ZhuluX'
desired_caps['appActivity'] = 'com.ZhuluX.MainActivity'
# 启动实例
driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)

# 登录
time.sleep(5)
# //android.widget.TextView[@text='密码登录']xs
driver.find_elements_by_xpath("//android.widget.TextView[@text='密码登录']")[0].click()
time.sleep(2)
driver.find_elements_by_xpath("//android.widget.TextView[@text='登录']")[0].click()
# 项目列表
time.sleep(5)
driver.find_elements_by_class_name("android.widget.ImageView")[0].click()
# 项目详情
print(1)
time.sleep(5)
driver.keyevent(4)  # 后退
print(2)
time.sleep(5)
driver.keyevent(4)  # 后退

# driver.close()
driver.quit()
