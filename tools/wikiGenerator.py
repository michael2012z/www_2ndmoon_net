# -*- coding: utf-8 -*-
import xlrd
import codecs
from datetime import date
import xml.dom.minidom
import os
import sys
reload(sys)
sys.setdefaultencoding('utf-8')


def generateFile(fileName, content):
    #print "generating file: " + fileName
    f = codecs.open(fileName,'w','utf-8')
    f.write(content)
    f.close()


def getTagValue(parent, tag):
    if parent.getElementsByTagName(tag)[0].childNodes <> []:
        return parent.getElementsByTagName(tag)[0].childNodes[0].nodeValue
    else:
        return ""


def generateStampWikiPage(xmlFile):
    print xmlFile
    doc = xml.dom.minidom.parse(xmlFile)
    record = doc.documentElement

    reference = getTagValue(record, "reference")
    id = getTagValue(record, "id")
    name = getTagValue(record, "name")
    format = getTagValue(record, "format")
    nSuite = getTagValue(record, "nSuite")
    issueDate = getTagValue(record, "issueDate")
    faceValue = getTagValue(record, "faceValue")
    issuedBy = getTagValue(record, "issuedBy")
    printedBy = getTagValue(record, "printedBy")
    author = getTagValue(record, "author")
    designer = getTagValue(record, "designer")
    layout = getTagValue(record, "layout")
    keyStamps = getTagValue(record, "keyStamps")
    notes = getTagValue(record, "notes")
    extention = getTagValue(record, "extention")

    if notes.find("\n") > 0:
        if extention == "":
            extention = notes
        notes = ""

    # file content 
    strPageContent = ""

    strPageContent += "\
(:table border=0 width=100%25 align=left bgcolor=#ffffff cellspacing=0 :)%0a\
(:cellnr colspan=4 align=center:)%0a"
    strPageContent += "!!! " + name
    strPageContent += "%0a"
    strPageContent += "(:tableend:)%0a"

    strPageContent += "\
(:table border=1 width=100%25 align=left bgcolor=#eeeeee cellspacing=0 :)%0a"

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

    strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25备注%25%25%0a\
(:cell colspan=3 align=center:)%0a"
    strPageContent += notes
    strPageContent += "%0a"
 
    strPageContent += "(:tableend:)%0a"
    
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

    stamps = record.getElementsByTagName("stampList")[0].childNodes
    for stamp in stamps:
        if stamp.nodeType == stamp.TEXT_NODE:
            continue
        stampIndex = getTagValue(stamp, "stampIndex")
        stampName = getTagValue(stamp, "stampName")
        stampFaceValue = getTagValue(stamp, "stampFaceValue")
        stampSize = getTagValue(stamp, "stampSize")
        stampPerforation = getTagValue(stamp, "stampPerforation")
        stampAmount = getTagValue(stamp, "stampAmount")

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

    strPageContent += "%25width=684px newwin%25 [[Attach:" + reference + ".jpg | Attach:" + reference + ".jpg]]| [-" + id + " " + name +"-]%0a"

    if extention <> "":
        strPageContent += "----%0a"
        strPageContent += "!!!! 背景知识%0a"
        extention = extention.replace("\n", "\\\\%0a")
        strPageContent += extention

    strPageContent += "\n"


    # file name
    wikiFileName = "output/Philately."+reference
    
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

    generateFile(wikiFileName, strPrePageContent+strPageContent+strPostPageContent)
    return

xmlFiles = os.listdir("xml")
xmlFiles.sort()
for xmlFile in xmlFiles[::-1]:
    if os.path.isfile("xml/" + xmlFile) and os.path.splitext(xmlFile)[1] == ".xml":
        generateStampWikiPage("xml/" + xmlFile)

