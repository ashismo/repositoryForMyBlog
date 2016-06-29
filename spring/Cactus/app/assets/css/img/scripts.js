/*--------------------------------------------------------------------------------------------
|    @desc:       The Ultimate Windows7 Desktop Using pure css3
|    @author:     Aravind Buddha
|    @url:        http://www.techumber.com
|    @date:       24 march 2012
|    @email:      aravind@techumber.com
|    @license:    'Free, but Some rights received' 
|                 
---------------------------------------------------------------------------------------------*/
$(function() {
$(".start").click(function(){ 
$("#startmenu").toggle();
});
$(".taskbar").delegate("#t-explor","click",function(){
	$(".window").toggle();
});
$(".taskbar").delegate("#t-calc","click",function(){
	$(".calc").toggle();
});
$('.wcmd').delegate(".min","click",function(){
	$(this).parent().parent().parent().hide();
});
$('.wcmd').delegate(".close","click",function(){
	$(this).parent().parent().parent().hide();
});
$( ".taskbar ul" ).sortable({scroll: false,containment: "parent",
revert: true,
start:function(){
$(this).children().undelegate("click");
},
stop: function() {
$(this).children().delegate("click");
}
});
$( ".taskbar ul" ).disableSelection();
$(".drag").draggable({handle: "div.head",scroll: false,stack: "#wrapper div"});


//Calc Object with display,clear,doCal methods
var Calc={
display:function(v){
	$("#display").val(v);
},
clear:function(){
	$("#display").val(" ");
},
doCal:function(val){
var result="";
var dtemp=$("#display").val();
if(dtemp=="0"){
	this.clear();
	result=val; 
}else if(val=="c"){
	this.clear();
}else if(val=="="){
	result=eval(dtemp);
}else{
	result=dtemp+val;
}
	this.display(result);
}
}
//Keyboard key press event.
$("body").keypress(function(e){
	Calc.doCal(String.fromCharCode(e.which));
});
//calc UI click events
$('.calc button').click(function(e){
	e.preventDefault();
	Calc.doCal(this.value);
});
//date time
var dt=new Date();
$(".datetime").html(dt.getHours()+":"+dt.getMinutes()+"<br />"+dt.getDate()+"-"+dt.getMonth()+"-"+dt.getFullYear());
});