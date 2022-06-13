@echo off
set CUR_YYYY=%date:~10,4%
set CUR_MM=%date:~4,2%
set CUR_DD=%date:~7,2%
set CUR_HH=%time:~0,2%
if %CUR_HH% lss 10 (set CUR_HH=0%time:~1,1%)

set CUR_NN=%time:~3,2%
set CUR_SS=%time:~6,2%
set CUR_MS=%time:~9,2%

set SUBFILENAME=%CUR_DD%%CUR_MM%%CUR_YYYY%-%CUR_HH%%CUR_NN%%CUR_SS%
set LOGFILE=LogFiles\RA_PDFsCopy_%SUBFILENAME%.log

call :LOG > %LOGFILE%
:LOG

echo on
set location=%cd%
call xcopy "%location%\TestReport" "D:\PDFCopyFolder\QMS\Risk Assessment" /h /i /c /k /e /r /y
cd location\TestReport
del *.pdf
exit

