#coding=utf-8
import xlrd

class Excel():
    def __init__(self, path):
        self.table = xlrd.open_workbook(path)
        self.nrows = self.table.nrows
        self.ncols = self.table.ncols

    def gettable(self):
        return self.table

    def getSheetName(self):
        table = self.table.sheets()

    def getSheet(self,sheetinfo):
        table = self.table.sheets()

    def getvalue(self,row,col):
        return self.table.row(row)[col].value

    def getrow(self,row):
         return self.table.row_values(row)

    def getcol(self,col):
         return self.table.col_values(col)

    def savefile(self,path):
        pass



