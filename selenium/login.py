#coding=utf-8
from selenium import webdriver
import time
# 创建浏览器实例
driver = webdriver.Firefox()

# 设置基本信息
username = "json"           # 用户名
password = "123qwe"         # 密码
waittime = 10                # 超时时间
valuetext = ""
ISOTIMEFORMAT='%Y-%m-%d %X'

# 打开首页
print('启动页面')
starttime = time.time()
driver.get("http://www.emao.com/") # 进入首页
endtime = time.time()
totletime = endtime - starttime
print(totletime)
driver.maximize_window() # 窗口最大化

# 跳转登录
print('登录页面')
starttime = time.time()
driver.find_element_by_link_text(u"登录").click()
endtime = time.time()
totletime = endtime - starttime
print(totletime)

# 输入登录信息
driver.find_element_by_id("mobile").send_keys(username)
driver.find_element_by_id("pwd").send_keys(password)

print('登录成功')
starttime = time.time()
driver.find_element_by_id("loginButton").click()
endtime = time.time()
totletime = endtime - starttime
print(totletime)



