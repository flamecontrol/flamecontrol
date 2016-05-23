#coding:utf-8
import unittest
import sys
import HTMLTestRunner
sys.path.append("./utils")
sys.path.append("./script")
from mail import MAIL
from excel import Excel
from LatentApp import Latent

#
# fname = u"data/testdata.xlsx"
# mainname = u"setting"
#
# xlsx = Excel.Excel(fname)
# mainsheet = xlsx.getSheet(mainname)
# print(xlsx.getvalue(2,2))

testunit=unittest.TestSuite()        #定义一个单元测试容器
testunit.addTest(Latent("testcase"))  #将测试用例加入到测试容器中
filename="./report/AppReport.html"        #定义个报告存放路径，支持相对路径。
fp=open(filename,'wb')
runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='Report_title',description='Report_description')  #使用HTMLTestRunner配置参数，输出报告路径、报告标题、描述
runner.run(testunit)                 #自动进行测试
