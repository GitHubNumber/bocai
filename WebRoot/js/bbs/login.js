window.defaultStatus = "The Best BBS";	
/*login js*/
thisPLoc = parent.document.location.host;
function showPanel(){
	 window.scrollTo(0,0); 
	 document.getElementById('Panel').style.display = 'block';
	 var box1=document.getElementById('fade');
	 box1.style.display='block';
	 document.body.style.overflow='hidden';
	 box1.style.height=document.body.clientHeight;
	 document.getElementById('frame').src = "http://"+thisPLoc+"/Mango_BBS/user/getlogin";
	 document.body.scrollTo(0,0);
 }
 function hidePanel(){
	 document.getElementById('Panel').style.display = 'none';
	 document.getElementById('fade').style.display='none';
	 document.body.style.overflow='visible';
 }