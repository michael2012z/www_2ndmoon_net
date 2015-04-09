# -*- coding: utf-8 -*-
import xlrd
import codecs
from datetime import date
import xml.dom.minidom

import sys
reload(sys)
sys.setdefaultencoding('utf-8')

def getIndex(id):
    indexStr = ""
    for a in id:
        if a.isdigit():
            indexStr += a
    index = int(indexStr)
    return index

def generateContentFile(prefix, excelFile):
    #print "generateContentFile: " + prefix

    # 目录文件
    indexFileName = "content/content."+prefix+".txt"
    indexFile = codecs.open(indexFileName,'w','utf-8')
    indexFileContent = u"|| border=1 width=100%\n||! 志号 ||! 名称 ||\n"

    workbook = xlrd.open_workbook(excelFile)
    nSheets = workbook.nsheets
    # go through each sheet/stamp
    for iSheet in range(1, nSheets+1):
        sheet = workbook.sheet_by_name("Sheet" + str(iSheet))
        id = sheet.cell(0, 1).value
        index = getIndex(id)
        reference = prefix+str(index)
        if (id.find(u"齿") >= 0):
            reference += "b"
        if (id.find(u"东") >= 0):
            reference += "e"
        if (id.find(u"再") >= 0):
            reference += "r"
        name = sheet.cell(1, 1).value
        indexFileContent += u"|| [[ Philately." + reference + u" | " + id + u" ]] || " + name + u" ||\n"
    indexFile.write(indexFileContent)
    indexFile.close()

generateContentFile("C", "xls/C.xls")
generateContentFile("S", "xls/S.xls")
generateContentFile("W", "xls/W.xls")
generateContentFile("N", "xls/N.xls")
generateContentFile("J", "xls/J.xls")
generateContentFile("T", "xls/T.xls")

