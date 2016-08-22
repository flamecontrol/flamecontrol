import re

s = '<html><head><title>Title</title>'
s = '1'
pattern = re.compile('<.*?>')
a = pattern.findall(s)
# b = re.match('<.*?>',s).span()


print(a)
# print(b)

teststring = '123.33sdhf3424.34fdg323.324'
pattern = re.compile(r'\d+.\d*')
s=pattern.findall(teststring)
print(s)
print(sum([float(var) for var in s]))