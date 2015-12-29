
import unittest
import sys
sys.path.append("./utils")
from getScreenShot import screen

s = screen()
class HOME(unittest.TestCase):
    def setUp(self):
        # desired_caps = {}
        # desired_caps['platformName'] = 'Android'
        # desired_caps['platformVersion'] = '4.2'
        # desired_caps['deviceName'] = 'Android Emulator'
        # desired_caps['appPackage'] = 'com.emao.autonews'
        # desired_caps['appActivity'] = 'com.emao.autonews.ui.HomeActivity'
        # # 启动实例
        # driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)
        pass


    def test1(self,text):
        print(text)

    def testcase(self):
        # driver.find_elements_by_class_name("android.widget.TextView")[0].click() # 全部
        # driver.find_elements_by_class_name("android.widget.TextView")[2].click() # 评测
        # driver.find_elements_by_xpath("//android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.ImageView")[0].click() # 轮播图图片
        s.snapshot("test001.jpg")
        # driver.keyevent(4) # 后退

