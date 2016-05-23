#coding:utf-8
import unittest
import xlrd
import HTMLTestRunner
import sys
sys.path.append("./utils")
sys.path.append("./test_script/homepage")
from selenium import webdriver
from homepage import HOME
from mail import MAIL


fname = u"test_data/testdata.xlsx"
Ctitle = 1
Cname = 2
Clevel = 4
Cstatus = 5
Ccomment = 6
testlevel = 1.0
teststatus == "YES"
data = xlrd.open_workbook(fname)
def gettest(mainsheet):
    arr = []
    table = data.sheet_by_name(mainsheet)#通过名称获取
    nrows = table.nrows
    for i in range(nrows):
        # print(table.row_values(i))
        Ilevel = table.row_values(i)[Clevel]
        Istatus = table.row_values(i)[Cstatus]
        if(Ilevel <= testlevel and Istatus == "YES"):
            arr.append(table.row_values(i))
    return arr

testunit=unittest.TestSuite()        #定义一个单元测试容器
# mainsheet = u"目录"
# arr = gettest(mainsheet)
# for a in arr:
#     testunit.addTest(AUTO(a[Cname]))

# testunit.addTest(CheckCar("test1"))  #将测试用例加入到测试容器中
testunit.addTest(HOME("testcase"))  #将测试用例加入到测试容器中
filename="./AUTOReport.html"        #定义个报告存放路径，支持相对路径。
fp=file(filename,'wb')
runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='Report_title',description='Report_description')  #使用HTMLTestRunner配置参数，输出报告路径、报告标题、描述
runner.run(testunit)                 #自动进行测试
