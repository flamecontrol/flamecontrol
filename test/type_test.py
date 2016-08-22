import types
import json

# a = True
# t_a = type(a)
# print(t_a)

a = '[{"enum":str,"zh_CN":str},{"enum":str,"zh_CN":str},{"enum":str,"zh_CN":str},{"enum":str,"zh_CN":str},{"enum":str,"zh_CN":str}]'

ea = eval(a)
if ea[1] == ea[2]:
    x = ea[1]
    print(x)
    print(x.keys())
    print(x.values())
