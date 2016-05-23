import types
def p(s):
    print(str(type(s)))
    if("int" in str(type(s))):
        print("整型")
    if("str" in str(type(s))):
        print("字符")


p("abc")
p(123)