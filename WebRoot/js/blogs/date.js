// JavaScript Document
function montharr(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11) { 
this[0] = m0; 
this[1] = m1; 
this[2] = m2; 
this[3] = m3; 
this[4] = m4; 
this[5] = m5; 
this[6] = m6; 
this[7] = m7; 
this[8] = m8; 
this[9] = m9; 
this[10] = m10; 
this[11] = m11; 
} 


function calendar() { 
var monthNames = "JanFebMarAprMayJunJulAugSepOctNovDec"; 
var today = new Date(); 
var thisDay; 
var monthDays = new montharr(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31); 

year = today.getYear() + 1900; 
thisDay = today.getDate(); 

if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) monthDays[1] = 29; 

nDays = monthDays[today.getMonth()]; 
firstDay = today; 
firstDay.setDate(1); // works fine for most systems 
testMe = firstDay.getDate(); 
if (testMe == 2) firstDay.setDate(0); 
startDay = firstDay.getDay(); 

document.write('<table border="1" width="218" cellspacing="0" cellpadding="1" align="CENTER"><TR><TD><table border="0" width="218" cellspacing="1" cellpadding="1" >'); 

document.write('<TR><th colspan="7" >'); 
var dayNames = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
var monthNames = new Array("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"); 
var now = new Date(); 
document.write("<font style=font-size:9pt;Color:#60A179>" + now.getYear() + "年" + monthNames[now.getMonth()] + "" + now.getDate() + "日 " + dayNames[now.getDay()] + "</FONT>"); 
document.writeln('</TH></TR><TR><TH ><font style="font-size:9pt;Color:#ff6699">日</FONT></TH>'); 
document.writeln('<th ><font style="font-size:9pt;Color:#15ACA7">一</FONT></TH>'); 
document.writeln('<TH><font style="font-size:9pt;Color:#15ACA7">二</FONT></TH>'); 
document.writeln('<TH ><font style="font-size:9pt;Color:#15ACA7">三</FONT></TH>'); 
document.writeln('<TH><font style="font-size:9pt;Color:#15ACA7">四</FONT></TH>'); 
document.writeln('<TH ><font style="font-size:9pt;Color:#15ACA7">五</FONT></TH>'); 
document.writeln('<TH ><font style="font-size:9pt;Color:#ff6699">六</FONT></TH>'); 
document.writeln("</TR><TR>"); 
column = 0; 
for (i=0; i<startDay; i++) { 
document.writeln("\n<TD><FONT style=font-size:9pt> </FONT></TD>"); 
column++; 
} 

for (i=1; i<=nDays; i++) { 
if (i == thisDay) { 
document.writeln('</TD><td align="CENTER" bgcolor="#ffefef"><FONT style=font-size:9pt;Color:red><B>') 
} 
else { 
document.writeln('</TD><TD BGCOLOR="#FFFFFF" ALIGN="CENTER"><FONT style=font-size:9pt;font-family:Arial;font-weight:bold;>'); 
} 
document.writeln(i); 
if (i == thisDay) document.writeln("</FONT></TD>") 
column++; 
if (column == 7) { 
document.writeln("<TR>"); 
column = 0; 
} 
} 
document.writeln('<TR><td colspan="7" align="CENTER" valign="TOP" bgcolor="#DFEFFF"><form name="clock" onSubmit="0"><FONT style=font-size:9pt;>当前时间  <INPUT TYPE="Text" NAME="face"  size="12" ALIGN="TOP"></FONT></TD></TR></TABLE></TD></TR></TABLE></form>'); 
} 
// --> 
var timerID = null; 
var timerRunning = false; 
function stopclock (){ 
if(timerRunning) 
clearTimeout(timerID); 
timerRunning = false;} 

function showtime () { 
var now = new Date(); 
var hours = now.getHours(); 
var minutes = now.getMinutes(); 
var seconds = now.getSeconds() 
var timevalue = " " + ((hours >12) ? hours -12 :hours) 
timevalue += ((minutes < 10) ? ":0" : ":") + minutes 
timevalue += ((seconds < 10) ? ":0" : ":") + seconds 
timevalue += (hours >= 12) ? " PM" : " AM" 
document.clock.face.value = timevalue; 
timerID = setTimeout("showtime()",1000); 
timerRunning = true;} 
function startclock () { 
stopclock(); 
showtime();} 