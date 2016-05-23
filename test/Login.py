# coding=utf-8
import sys
import time
from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
from time import ctime


driver = webdriver.Firefox()
driver.get("https://passport.opifices.com/?redirect=https%3A%2F%2Fopifices.com%2F%23%2F")
print("设置宽高")
driver.set_window_size(800, 1000)
File_Path = '/Users/jichen/Desktop/AndroidTool/iOSjichen04132016142311.png'
time.sleep(3)
tt = driver.implicitly_wait(3)
# 这个tt只是为了方便

driver.find_element_by_id('user_mobile').send_keys('18610152142')
# 输入账号
driver.find_element_by_id('user_password').send_keys('123qwe')
# 输入密码
driver.find_element_by_class_name('btn-fixed').click()
# 点击登录
tt
# 上传图片是个坑需要js调整css里的display
js_idcard = '$("[name=\'upload_idcard\']").show()'
driver.execute_script(js_idcard)
driver.find_element_by_name('upload_idcard').send_keys(File_Path)
time.sleep(3)
# 上传身份证
js_card = '$("[name=\'upload_card\']").show()'
driver.execute_script(js_card)
driver.find_element_by_name('upload_card').send_keys(File_Path)
time.sleep(3)
# 上传名片
js_Logo = '$("[name=\'bpFile\']").show()'
driver.execute_script(js_Logo)
driver.find_element_by_name('bpFile').send_keys(File_Path)
time.sleep(3)
# 上传Logo
driver.find_element_by_class_name('realname').send_keys(u'我测试我快乐')

driver.find_element_by_class_name('project_name').send_keys(u'我快乐就是我测试')


time.sleep(10)
driver.quit()

