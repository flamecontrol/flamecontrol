# -*- coding: utf-8 -*-
import sys
import xlrd
import xlwt
import os
from xlrd import open_workbook
from xlutils.copy import copy
reload(sys)
sys.setdefaultencoding("utf-8")

filename = (r"E:\TestCase.xls").decode('utf-8').encode('gbk')

class ExcelUtil(object):  
  
    def __init__(self, excelPath, sheetName):  
        self.data = xlrd.open_workbook(excelPath)  
        self.table = self.data.sheet_by_name(sheetName)  
          
        #get titles  
        self.row = self.table.row_values(0)  
          
        #get rows number  
        self.rowNum = self.table.nrows  
          
        #get columns number  
        self.colNum = self.table.ncols  
          
        #the current column  
        self.curRowNo = 1  
        
    def next(self):  
        r = []  
        while self.hasNext():  
            s = {}  
            col = self.table.row_values(self.curRowNo)  
            i = self.colNum  
            for x in range(i):  
                s[self.row[x]] = col[x]  
            r.append(s)  
            self.curRowNo += 1  
        
        return r         
      
    def hasNext(self):  
        if self.rowNum == 0 or self.rowNum <= self.curRowNo :  
            return False  
        else:  
            return True 
    def listnext(self,list):
        status = list
        print "list hahahahah"
        print status
     
    def write(self):
        
        rb = open_workbook(filename) 
        wb = copy(rb)
        sheet =wb.get_sheet(0)

        for x in range(self.rowNum -1):
            sheet.write(x+1,5,'true')
        os.remove(filename)
        wb.save(filename)