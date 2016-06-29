/**
 * Created by Madhurjya on 12/25/2015.
 */
$(function() {
  $("body").on('click',function(e){
    if($("#startmenu").css('display') === 'block'){
      $("#startmenu").css('display','none');
    } ;
  });
  $("body").on('click','.start',function(e){
    e.stopImmediatePropagation();
    $("#startmenu").toggle();
  });
  $("body").on("click","#t-explor",function(e){
    e.stopImmediatePropagation();
    $(".window").toggle();
  });
  $("body").on("click","#t-calc",function(e){
    e.stopImmediatePropagation();
    $(".calc").toggle();
  });
  $('body').on("click",".min",function(){
    $(this).parent().parent().parent().hide();
  });
  $('body').on("click",".close",function(){
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

$('body').on('click','.close-popup',function(){
  $(this).parent().hide();
})
$('body').on('click','.add-module-rule',function(){
   if($(this).next().css('display') === 'none'){
    $(this).next().show();
  }
})
$('body').on('click','.close-rule-value',function(){
  console.log($(this).parent().css('display'))
  $(this).parent().css('display','none');
  console.log($(this).parent().css('display'))
})
$('body').on('click','.module-tr',function(){
  $('.module-tr').find('.rule-value-popup').css('display','none');
  if($(this).find('.rule-value-popup').css('display') === 'block'){
    $(this).find('.rule-value-popup').css('display','none');
  } else {
    $(this).find('.rule-value-popup').css('display','block');
  }
})

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
  $("body").on('keypress',function(e){
    Calc.doCal(String.fromCharCode(e.which));
  });
//calc UI click events
  $('body').on('click','.calc button',function(e){
    e.preventDefault();
    Calc.doCal(this.value);
  });
//date time
  var dt=new Date();
  $(".datetime").html(dt.getHours()+":"+dt.getMinutes()+"<br />"+dt.getDate()+"-"+dt.getMonth()+"-"+dt.getFullYear());

  $('body').on('click','md-tab-item',function(){
    localStorage.setItem('currentTabIndex', $('md-tab-item').index(this));
    if($('md-tab-item').index(this) === 1){
      $('.md-button-right-top').attr('ng-click','toggleRightRole($mdTabsCtrl)');
    }
  });

});
