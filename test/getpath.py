
import os
#获取当前文件所在路径
thePath = os.path.abspath(os.getcwd())
#截取字符串至项目名：Test\
print(thePath)
a = thePath[:thePath.find("test")]
print(a)
b = thePath[:thePath.rfind("/")]
print(b)