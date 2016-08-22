#coding=utf-8
import xlrd
fname = "launchData.xlsx"
fname = "/Users/gaojie/Documents/git/flamecontrol/Latent/data/testdata.xlsx"
data = xlrd.open_workbook(fname)
table = data.sheet_names()          #通过索引顺序获取

print(table)
table = data.sheet_by_index(0) #通过索引顺序获取
table = data.sheet_by_name(u'name1')#通过名称获取
print(table.row(0)[0].value)

table = data.sheet_by_name(u'name2')#通过名称获取
print(table.row(2)[2].value)
print(table.row(0)[0].value)

row = 0
col = 0
ctype = 1
value = '单元格的值'
xf = 0 # 扩展的格式化
table.put_cell(row, col, ctype, value, xf)

print(table.row(0)[0].value)