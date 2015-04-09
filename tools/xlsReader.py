# -*- coding: gbk -*-
import xlrd
import codecs
from datetime import date


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


def generalStampsWikiPages(prefix, excelFile):
    #print "generalStampsWikiPages: " + prefix

    # Ŀ¼�ļ�
    indexFileName = "content."+prefix+".txt"
    indexFile = codecs.open(indexFileName,'w','utf-16')
    indexFileContent = u"|| border=1 width=100%\n||! ־�� ||! ���� ||\n"

    workbook = xlrd.open_workbook(excelFile)
    nSheets = workbook.nsheets
    # go through each sheet/stamp
    for iSheet in range(1, nSheets+1):
        sheet = workbook.sheet_by_name("Sheet" + str(iSheet))
        # ־��
        id = sheet.cell(0, 1).value
        index = getIndex(id)
        reference = prefix+str(index)

        if (id.find(u"��") >= 0):
            reference += "b"
        if (id.find(u"��") >= 0):
            reference += "e"
        if (id.find(u"��") >= 0):
            reference += "r"

        # ���
        format = sheet.cell(0, 3).value
        # ����
        name = sheet.cell(1, 1).value
        # ȫ��ö��
        nSuite = sheet.cell(2, 1).value
        # ��������
        if 1 == sheet.cell(2, 3).ctype:
            issueDate = sheet.cell(2, 3).value
        else:
            issueDate = str(sheet.cell(2, 3).value)
        if issueDate.find("-") == -1:
            print reference
            d = date.fromordinal(int(float(issueDate)) + 693594)
            issueDate = d.strftime("%Y-%m-%d")
        # ȫ����ֵ
        faceValue = sheet.cell(3, 1).value
        # ���л���
        issuedBy = sheet.cell(4, 1).value
        # ӡ�ƻ���
        printedBy = sheet.cell(4, 3).value
        # ԭ����
        author = sheet.cell(5, 1).value
        # �����
        designer = sheet.cell(5, 3).value
        # ����ö��
        layout = sheet.cell(6, 1).value
        # ��Ʊ
        keyStamps = sheet.cell(8, 3).value
        # ��ע
        notes = sheet.cell(9, 1).value

        indexFileContent += u"|| [[ Philately." + reference + u" | " + id + u" ]] || " + name + u" ||\n"

        # file content 
        strPageContent = ""

        strPageContent += "\
(:table border=1 width=100%25 align=left bgcolor=#eeeeee cellspacing=0 :)%0a\
(:cellnr colspan=4 align=center:)%0a"
        strPageContent += name
        strPageContent += "%0a"

        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25־��%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += id
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25���%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += format
        strPageContent += "%0a"

        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25ȫ��ö��%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += str(nSuite)
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25��������%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += issueDate
        strPageContent += "%0a"

        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25ȫ����ֵ%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += faceValue
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25��Ʊ%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += keyStamps
        strPageContent += "%0a"

        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25���л���%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += issuedBy
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25ӡ�ƻ���%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += printedBy
        strPageContent += "%0a"

        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25ԭ����%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += author
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25�����%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += designer
        strPageContent += "%0a"

        strPageContent += u"\
(:cellnr align=center:)%0a\
%25color=blue%25����ö��%25%25%0a\
(:cell align=center:)%0a"
        strPageContent += layout
        strPageContent += "%0a"

        strPageContent += u"\
(:cell align=center:)%0a\
%25color=blue%25�ο��۸�%25%25%0a\
(:cell align=center:)%0a"
 
        strPageContent += "(:tableend:)%0a"

        strPageContent += "----%0a"


        # ÿö��Ʊ���ӵ�11�п�ʼ
        strPageContent += u"\
(:table border=1 width=100%25 align=left bgcolor=#eeeeee cellspacing=0 :)%0a\
(:cellnr align=center:)%0a\
%25color=blue%25���%25%25%0a\
(:cell align=center:)%0a\
%25color=blue%25����%25%25%0a\
(:cell align=center:)%0a\
%25color=blue%25��ֵ��Ԫ��%25%25%0a\
(:cell align=center:)%0a\
%25color=blue%25���mm��%25%25%0a\
(:cell align=center:)%0a\
%25color=blue%25�ݿ׶���%25%25%0a\
(:cell align=center:)%0a\
%25color=blue%25����������%25%25%0a"

        for iStamp in range(0, int(nSuite)):
            # ͼ��
            stampIndex = sheet.cell(11+iStamp, 0).value
            stampIndex = str(stampIndex)
            if stampIndex.find("-") == -1:
                if (int(float(stampIndex))) > 1000:
                    d = date.fromordinal(int(float(stampIndex)) + 693594)
                    stampIndex = str(d.month) + "-" + str(d.day)
                else:
                    stampIndex = str(int(float(stampIndex)))
            # Ʊͼ����	
            stampName = sheet.cell(11+iStamp, 1).value
            # ��ֵ(Ԫ)	
            if 1 == sheet.cell(11+iStamp, 2).ctype:
                stampFaceValue = sheet.cell(11+iStamp, 2).value
            else:
                stampFaceValue = str(sheet.cell(11+iStamp, 2).value)
            # ���(mm)	
            if 1 == sheet.cell(11+iStamp, 3).ctype:
                stampSize = sheet.cell(11+iStamp, 3).value
            else:
                stampSize = str(sheet.cell(11+iStamp, 3).value)
            # �ݿ׶���	
            stampPerforation = sheet.cell(11+iStamp, 4).value
            # ������(��)
            if 1 == sheet.cell(11+iStamp, 5).ctype:
                stampAmount = sheet.cell(11+iStamp, 5).value
            else:
                stampAmount = str(sheet.cell(11+iStamp, 5).value)
            
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

    indexFile.write(indexFileContent)
    indexFile.close()

generalStampsWikiPages("C", "C.xls")
generalStampsWikiPages("S", "S.xls")
generalStampsWikiPages("W", "W.xls")
generalStampsWikiPages("N", "N.xls")
generalStampsWikiPages("J", "J.xls")
generalStampsWikiPages("T", "T.xls")


def test():
    myWorkbook = xlrd.open_workbook('C:\Users\efengzh\Dropbox\Philately\��ƱĿ¼\������Ʊ.xls')
    print myWorkbook.encoding
#mySheet = myWorkbook.sheet_by_name('��1ԭ��'.decode('gbk'))
    mySheet = myWorkbook.sheet_by_name('Sheet42')
    nSheets = myWorkbook.nsheets

    f = codecs.open('test.txt','w','utf-16')

    for i in range(0, mySheet.nrows):
        for j in range (0, mySheet.ncols):
            print i, j, mySheet.cell(i,j).ctype
            if 1 == mySheet.cell(i,j).ctype:
                f.write(mySheet.cell(i,j).value)
            else:
                f.write(str(mySheet.cell(i,j).value))
                f.write("\r\n")
                
    f.close()

