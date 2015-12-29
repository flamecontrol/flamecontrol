import unittest
class HOME(unittest.TestCase):
    def setUp(self):
        desired_caps = {}
        desired_caps['platformName'] = 'Android'
        desired_caps['platformVersion'] = '4.2'
        desired_caps['deviceName'] = 'Android Emulator'
        desired_caps['appPackage'] = 'com.emao.autonews'
        desired_caps['appActivity'] = 'com.emao.autonews.ui.HomeActivity'
        # 启动实例
        driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)

    def test1(self,text):
        print(text)

    def testcase(self):

