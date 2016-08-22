import os
import sys

rootdir = os.getcwd()
path = "git"
index = rootdir.find(path)
# print(rootdir)
#
# print(os.path.exists(path))
#
# print(os.listdir(rootdir))
# print(index)
# print(os.getcwd()[:os.getcwd().find(path) + len(path)])
# print(os.path.dirname(rootdir))
print(sys.argv)

# print(sys.argv[0])
# print(os.path.dirname(sys.argv[0]))
# os.chdir(os.path.dirname(sys.argv[0]))


print(os.path)