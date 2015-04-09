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


def generalStampsXmlFiles(prefix, excelFile):
    #print "generalStampsXmlFiles: " + prefix
    workbook = xlrd.open_workbook(excelFile)
    nSheets = workbook.nsheets
    # go through each sheet/stamp
    for iSheet in range(1, nSheets+1):
        # xml
        impl = xml.dom.minidom.getDOMImplementation()
        dom = impl.createDocument(None, 'record', None)
        root = dom.documentElement 

        sheet = workbook.sheet_by_name("Sheet" + str(iSheet))
        # 志号
        id = sheet.cell(0, 1).value
        index = getIndex(id)
        reference = prefix+str(index)

        if (id.find(u"齿") >= 0):
            reference += "b"
        if (id.find(u"东") >= 0):
            reference += "e"
        if (id.find(u"再") >= 0):
            reference += "r"

        # 版别
        format = sheet.cell(0, 3).value
        # 名称
        name = sheet.cell(1, 1).value
        # 全套枚数
        nSuite = sheet.cell(2, 1).value
        nSuite = str(int(float(nSuite)))
        # 发行日期
        if 1 == sheet.cell(2, 3).ctype:
            issueDate = sheet.cell(2, 3).value
        else:
            issueDate = str(sheet.cell(2, 3).value)
        if issueDate.find("-") == -1:
            print reference
            d = date.fromordinal(int(float(issueDate)) + 693594)
            issueDate = d.strftime("%Y-%m-%d")
        # 全套面值
        faceValue = sheet.cell(3, 1).value
        # 发行机构
        issuedBy = sheet.cell(4, 1).value
        # 印制机构
        printedBy = sheet.cell(4, 3).value
        # 原作者
        author = sheet.cell(5, 1).value
        # 设计者
        designer = sheet.cell(5, 3).value
        # 整版枚数
        layout = sheet.cell(6, 1).value
        # 筋票
        keyStamps = sheet.cell(8, 3).value
        # 备注
        notes = sheet.cell(9, 1).value

        xmlTag = dom.createElement("reference")
        xmlTagValue = dom.createTextNode(reference)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("id")
        xmlTagValue = dom.createTextNode(id)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("name")
        xmlTagValue = dom.createTextNode(name)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("format")
        xmlTagValue = dom.createTextNode(format)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("nSuite")
        xmlTagValue = dom.createTextNode(nSuite)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("issueDate")
        xmlTagValue = dom.createTextNode(issueDate)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("faceValue")
        xmlTagValue = dom.createTextNode(faceValue)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("issuedBy")
        xmlTagValue = dom.createTextNode(issuedBy)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("printedBy")
        xmlTagValue = dom.createTextNode(printedBy)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("author")
        xmlTagValue = dom.createTextNode(author)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("designer")
        xmlTagValue = dom.createTextNode(designer)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("layout")
        xmlTagValue = dom.createTextNode(layout)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("keyStamps")
        xmlTagValue = dom.createTextNode(keyStamps)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        xmlTag = dom.createElement("notes")
        xmlTagValue = dom.createTextNode(notes)
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        stampList = dom.createElement('stampList')
        root.appendChild(stampList)

        for iStamp in range(0, int(nSuite)):
            # 图序
            stampIndex = sheet.cell(11+iStamp, 0).value
            stampIndex = str(stampIndex)
            if stampIndex.find("-") == -1:
                if (int(float(stampIndex))) > 1000:
                    d = date.fromordinal(int(float(stampIndex)) + 693594)
                    stampIndex = str(d.month) + "-" + str(d.day)
                else:
                    stampIndex = str(int(float(stampIndex)))
            # 票图名称	
            stampName = sheet.cell(11+iStamp, 1).value
            # 面值(元)	
            if 1 == sheet.cell(11+iStamp, 2).ctype:
                stampFaceValue = sheet.cell(11+iStamp, 2).value
            else:
                stampFaceValue = str(sheet.cell(11+iStamp, 2).value)
            # 规格(mm)	
            if 1 == sheet.cell(11+iStamp, 3).ctype:
                stampSize = sheet.cell(11+iStamp, 3).value
            else:
                stampSize = str(sheet.cell(11+iStamp, 3).value)
            # 齿孔度数	
            stampPerforation = sheet.cell(11+iStamp, 4).value
            # 发行量(万)
            if 1 == sheet.cell(11+iStamp, 5).ctype:
                stampAmount = sheet.cell(11+iStamp, 5).value
            else:
                stampAmount = str(sheet.cell(11+iStamp, 5).value)

            xmlStampTag = dom.createElement("stamp")
            stampList.appendChild(xmlStampTag)

            xmlTag = dom.createElement("stampIndex")
            xmlTagValue = dom.createTextNode(stampIndex)
            xmlTag.appendChild(xmlTagValue)
            xmlStampTag.appendChild(xmlTag)

            xmlTag = dom.createElement("stampName")
            xmlTagValue = dom.createTextNode(stampName)
            xmlTag.appendChild(xmlTagValue)
            xmlStampTag.appendChild(xmlTag)
            
            xmlTag = dom.createElement("stampFaceValue")
            xmlTagValue = dom.createTextNode(stampFaceValue)
            xmlTag.appendChild(xmlTagValue)
            xmlStampTag.appendChild(xmlTag)
            
            xmlTag = dom.createElement("stampSize")
            xmlTagValue = dom.createTextNode(stampSize)
            xmlTag.appendChild(xmlTagValue)
            xmlStampTag.appendChild(xmlTag)
            
            xmlTag = dom.createElement("stampPerforation")
            xmlTagValue = dom.createTextNode(stampPerforation)
            xmlTag.appendChild(xmlTagValue)
            xmlStampTag.appendChild(xmlTag)
            
            xmlTag = dom.createElement("stampAmount")
            xmlTagValue = dom.createTextNode(stampAmount)
            xmlTag.appendChild(xmlTagValue)
            xmlStampTag.appendChild(xmlTag)
            
        # file name
        fileName = "output/Philately."+reference
        
        xmlTag = dom.createElement("extention")
        xmlTagValue = dom.createTextNode("")
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        f = open("xml/" + reference + ".xml", 'w')
        dom.writexml(f, addindent='  ', newl='\n',encoding='utf-8')
        f.close()


generalStampsXmlFiles("C", "xls/C.xls")
generalStampsXmlFiles("S", "xls/S.xls")
generalStampsXmlFiles("W", "xls/W.xls")
generalStampsXmlFiles("N", "xls/N.xls")
generalStampsXmlFiles("J", "xls/J.xls")
generalStampsXmlFiles("T", "xls/T.xls")

