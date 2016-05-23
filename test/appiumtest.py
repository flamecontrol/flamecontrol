from appium import webdriver
# 设置信息
desired_caps = {}
desired_caps['platformName'] = 'Android'
desired_caps['platformVersion'] = '4.2'
desired_caps['deviceName'] = 'Android Emulator'
desired_caps['appPackage'] = 'com.emao.autonews'
desired_caps['appActivity'] = 'com.emao.autonews.ui.HomeActivity'
# 启动实例
driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)

# 首页
# 列标头
driver.find_elements_by_class_name("android.widget.TextView")[0].click() # 全部
driver.find_elements_by_class_name("android.widget.TextView")[1].click() # 新车
driver.find_elements_by_class_name("android.widget.TextView")[2].click() # 评测
driver.find_elements_by_class_name("android.widget.TextView")[3].click() # 比车
driver.find_elements_by_class_name("android.widget.TextView")[4].click() # 导购
driver.find_elements_by_class_name("android.widget.TextView")[5].click() # 新闻
driver.find_element_by_id("btn_more").click()    # 更多分类
driver.find_element_by_id("btn_search").click()    # 搜索
# 轮播图
driver.find_elements_by_xpath("//android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.ImageView")[0].click() # 轮播图图片
driver.find_elements_by_xpath("//android.widget.RelativeLayout/android.widget.TextView")[0].click() # 轮播图文字
# 正文
driver.find_elements_by_xpath("//android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.ImageView")[(1-1)].click() # 列表第一条图片
driver.find_elements_by_xpath("//android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView")[3*(1-1)+1].click() # 列表第一条时间
driver.find_elements_by_xpath("//android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView")[3*(1-1)+2].click() # 列表第一条时间
driver.find_element_by_xpath("//android.widget.ListView/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.TextView[3]").click()
# 其他正文
driver.find_elements_by_xpath("//android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.ImageView")[(2-1)].click() # 列表第二条图片
driver.find_elements_by_xpath("//android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView")[3*(2-1)+1].click() # 列表第二条时间
driver.find_elements_by_xpath("//android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView")[3*(2-1)+2].click() # 列表第二条时间
driver.find_element_by_xpath("//android.widget.ListView/android.widget.RelativeLayout[3]/android.widget.RelativeLayout/android.widget.TextView[3]").click()
# 底部按钮
driver.find_element_by_id("main_radio_tab1").click()    # 咨询
driver.find_element_by_id("main_radio_tab2").click()    # 选车
driver.find_element_by_id("main_radio_tab4").click()    # 个人

# 更多分类跳过

# 搜索
driver.find_element_by_id("edittext").send_keys("一猫汽车") # 输入有点问题
driver.find_element_by_id("poptext").click() # 设置搜索类型
# 弹出的类型框识别不出来
driver.find_element_by_id("bar_right_btn").click() # 关闭搜索
driver.find_elements_by_id("value")[0].click() # 第一条历史记录
driver.find_element_by_id("clear").click() # 关闭搜索

# 滑动
width = driver.get_window_size().get("width")
height = driver.get_window_size().get("height")
driver.swipe(width/2,height*3/4, width/2,height/4, 1000) # 滑动
driver.swipe(width/2,height/2, width/2,height/2, 1000) # 长压

# 系统功能
driver.keyevent(4) # 后退
# http://blog.csdn.net/feizhixuan46789/article/details/16801429
