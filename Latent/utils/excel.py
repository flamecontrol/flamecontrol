#coding=utf-8
import xlrd

class Excel():
    def __init__(self, path):
        self.data = xlrd.open_workbook(path)

    def reload(self, path):
        self.data = xlrd.open_workbook(path)

    def getSheetName(self):
        self.sheets = self.data.sheets()

    def getSheet(self,sheetinfo):
        if("int" in str(type(sheetinfo))):
            self.table = self.data.sheet_by_index(sheetinfo)
        if("str" in str(type(sheetinfo))):
            self.table = self.data.sheet_by_name(sheetinfo)
        self.nrows = self.table.nrows
        self.ncols = self.table.ncols
        return self.table

    def getvalue(self,row,col):
        return self.table.row(row)[col].value

    def getrow(self,row):
         return self.table.row_values(row)

    def getcol(self,col):
         return self.table.col_values(col)

    def savefile(self,path):
        pass

# fname = u"./../data/testdata.xlsx"
# mainname = u"setting"
# xlsx = Excel(fname)
# mainsheet = xlsx.getSheet(mainname)
# print(xlsx.getvalue(2,2))
