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

def generateFile(fileName, content):
    #print "generating file: " + fileName
    f = codecs.open(fileName,'w','utf-8')
    f.write(content)
    f.close()


def generalStampsXmlFiles(prefix, excelFile):
    #print "generalStampsXmlFiles: " + prefix

    # 目录文件
    indexFileName = "content."+prefix+".txt"
    indexFile = codecs.open(indexFileName,'w','utf-16')
    indexFileContent = u"|| border=1 width=100%\n||! 志号 ||! 名称 ||\n"

    workbook = xlrd.open_workbook(excelFile)
    nSheets = workbook.nsheets
    # go through each sheet/stamp
    for iSheet in range(1, nSheets+1):
        # xml
        impl = xml.dom.minidom.getDOMImplementation()
        dom = impl.createDocument(None, 'Record', None)
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


        indexFileContent += u"|| [[ Philately." + reference + u" | " + id + u" ]] || " + name + u" ||\n"

        # file content 
        strPageContent = ""

        strPageContent += "\
(:table border=1 width=100%25 align=left bgcolor=#eeeeee cellspacing=0 :)%0a\
(:cellnr colspan=4 align=center:)%0a"
        strPageContent += name
        strPageContent += "%0a"
        print id
        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25志号%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += id
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25版别%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += format
        strPageContent += "%0a"

        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25全套枚数%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += str(nSuite)
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25发行日期%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += issueDate
        strPageContent += "%0a"

        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25全套面值%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += faceValue
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25筋票%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += keyStamps
        strPageContent += "%0a"

        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25发行机构%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += issuedBy
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25印制机构%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += printedBy
        strPageContent += "%0a"

        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25原作者%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += author
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25设计者%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += designer
        strPageContent += "%0a"

        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25整版枚数%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += layout
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25参考价格%25%25%0a\
(:cell align=center:)%0a"
 
        strPageContent += "(:tableend:)%0a"

        strPageContent += "----%0a"


        # 每枚邮票，从第11列开始
        strPageContent += u"\
(:table border=1 width=100%25 align=left bgcolor=#eeeeee cellspacing=0 :)%0a\
(:cellnr align=center:)%0a\
%25color=blue%25编号%25%25%0a\
(:cell align=center:)%0a\
%25color=blue%25名称%25%25%0a\
(:cell align=center:)%0a\
%25color=blue%25面值（元）%25%25%0a\
(:cell align=center:)%0a\
%25color=blue%25规格（mm）%25%25%0a\
(:cell align=center:)%0a\
%25color=blue%25齿孔度数%25%25%0a\
(:cell align=center:)%0a\
%25color=blue%25发行量（万）%25%25%0a"

        stampList = dom.createElement('StampList')
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
            
            
            strPageContent += "(:cellnr align=center:)%0a"
            strPageContent += stampIndex
            strPageContent += "%0a"
            strPageContent += "(:cell align=center:)%0a"
            strPageContent += stampName
            strPageContent += "%0a"
            strPageContent += "(:cell align=center:)%0a"
            strPageContent += stampFaceValue
            strPageContent += "%0a"
            strPageContent += "(:cell align=center:)%0a"
            strPageContent += stampSize
            strPageContent += "%0a"
            strPageContent += "(:cell align=center:)%0a"
            strPageContent += stampPerforation
            strPageContent += "%0a"
            strPageContent += "(:cell align=center:)%0a"
            strPageContent += stampAmount
            strPageContent += "%0a"

        strPageContent += "(:tableend:)%0a"
        strPageContent += "----%0a"

        strPageContent += "%25width=560px newwin%25 [[Attach:" + reference + ".jpg | Attach:" + reference + ".jpg]]| [-" + id + " " + name +"-]%0a"

        strPageContent += "----%0a"
        strPageContent += notes
        strPageContent += "\n"

        # file name
        fileName = "output/Philately."+reference
        
        strPrePageContent = "version=pmwiki-2.2.6 ordered=1 urlencoded=1%0a\
agent=Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31\n\
author=\n\
charset=UTF-8\n\
csum=\n\
ctime=1366606162\n\
host=114.80.140.35\n\
name=Philately." + reference +"\n" + "\
rev=1\n\
targets=\n\
text="

        strPostPageContent = "time=1366606162\n\
author:1366606162=\n\
host:1366606162=114.80.140.35\n"

        generateFile(fileName, strPrePageContent+strPageContent+strPostPageContent)

        xmlTag = dom.createElement("extention")
        xmlTagValue = dom.createTextNode("")
        xmlTag.appendChild(xmlTagValue)
        root.appendChild(xmlTag)

        f = open("xml/" + reference + ".xml", 'w')
        dom.writexml(f, addindent='  ', newl='\n',encoding='utf-8')
        f.close()




    indexFile.write(indexFileContent)
    indexFile.close()

generalStampsXmlFiles("C", "C.xls")
generalStampsXmlFiles("S", "S.xls")
generalStampsXmlFiles("W", "W.xls")
generalStampsXmlFiles("N", "N.xls")
generalStampsXmlFiles("J", "J.xls")
generalStampsXmlFiles("T", "T.xls")

