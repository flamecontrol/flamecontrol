#coding=utf-8
from selenium import webdriver
import time
# 创建浏览器实例
driver = webdriver.Firefox()

# 设置基本信息
username = "json"           # 用户名
password = "123qwe"         # 密码
waittime = 2                # 超时时间

# 打开首页
driver.get("http://auto.emao.com/") # 进入首页
driver.maximize_window() # 窗口最大化

# # 跳转登录
# driver.find_element_by_link_text(u"登录").click()
# time.sleep(waittime)
# # 输入登录信息
# driver.find_element_by_id("mobile").send_keys(username)
# driver.find_element_by_id("pwd").send_keys(password)
# driver.find_element_by_id("loginButton").click()
# time.sleep(waittime)

# 输入搜索关键字
driver.find_element_by_id("searchTxt").send_keys(title) # 在搜索框中增加关键字
driver.find_element_by_id("btn").click() # 在新页面打开搜索结果
time.sleep(waittime)

# 选取筛选记录
driver.switch_to_window(driver.window_handles[-1])  # 跳转到新打开的页面
driver.find_element_by_partial_link_text(title).click() # 按搜索内容找到对应的文章（partial_link为部分匹配），在新页面打开
time.sleep(waittime)

# 填写评论信息报名
driver.switch_to_window(driver.window_handles[-1])  # 跳转到新打开的页面
driver.find_element_by_id("comment_textarea_0").send_keys(userinfo) # 设置报名信息
driver.find_element_by_name("comment_add").click() # 点击提交评论
time.sleep(waittime)
time.sleep(20)
driver.quit()
