#!/usr/bin/python
#coding=utf-8
import unittest
import time
#把test_case目录添加到path下，这里用的是相对路径
import sys
import HTMLTestRunner
sys.path.append("/var/lib/jenkins/workspace/emao/test_script/homepage")
sys.path.append("/var/lib/jenkins/workspace/emao/test_script/news")
sys.path.append("/var/lib/jenkins/workspace/emao/test_script/zt")
sys.path.append("/var/lib/jenkins/workspace/emao/test_script/CityStation")
sys.path.append("/var/lib/jenkins/workspace/emao/test_script/auto")
sys.path.append("/var/lib/jenkins/workspace/emao/test_script/club")
#print sys.path
from club_cheji_check import club_cheji_check
from club_homepage_mycenter_check import club_homepage_mycenter_check
from homepage import homepage
from news import news
from Subject import Subject
from CityStation import CityStation
from CheckCar import CheckCar

#print sys.path

testunit=unittest.TestSuite()
#将测试用例加入到测试套件中
testunit.addTest(unittest.makeSuite(homepage))
testunit.addTest(unittest.makeSuite(news))
testunit.addTest(unittest.makeSuite(Subject))
testunit.addTest(unittest.makeSuite(CheckCar))
testunit.addTest(unittest.makeSuite(club_cheji_check))
testunit.addTest(unittest.makeSuite(club_homepage_mycenter_check))
testunit.addTest(unittest.makeSuite(CityStation))


#执行测试套件
#runner=unittest.TextTestRunner()
#runner.run(testunit)s

#获取当前时间
now=time.strftime("%Y-%m-%d-%H_%M_%S",time.localtime(time.time()))
#定义报告存放路径，支持相对路径
#"wb" 以二进制写方式打开，只能写文件， 如果文件不存在，创建该文件
#如果文件已存在，先清空，再打开文件 
filename="/var/lib/jenkins/workspace/emao/test_report/"+now+'result.html'
fp=file(filename,'wb')

#定义测试报告
#stream定义报告所写入的文件
runner=HTMLTestRunner.HTMLTestRunner(
    stream=fp,
    title=u'一猫网站回归测试报告举例',
    description=u'回归测试执行结果：')

#运行测试用例，并将结果写入报告中
runner.run(testunit)

