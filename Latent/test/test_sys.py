import os

print(os.path.abspath(os.path.join(os.path.dirname(__file__), os.pardir, os.pardir)))
# __file__：当前文件路径
# os.path.dirname(file): 某个文件所在的目录路径
# os.path.join(a, b, c,....): 路径构造 a/b/c
# os.path.abspath(path): 将path从相对路径转成绝对路径
# os.pardir: Linux下相当于"../"


print(os.getcwd())
print(os.path.abspath(os.getcwd()))