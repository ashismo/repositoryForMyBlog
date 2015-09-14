<html>
<head>
    <title>Idle Timer Example</title>
</head>
<body>
    <div id="status" style="padding: 5px;">&nbsp;</div>
    <form>
        <label for="comment">Textarea:</label><br />
        <textarea rows="2" cols="30" id="comment" name="comment"></textarea><br />
        <input type="submit" value="Button" />
    </form>
	
	<a href="#" class="confirm" id="alert">Normal test</a>
   	<a href="?confirm=true" class="confirm">Normal test with a link</a>
   	<a href="?confirm=true" class="confirm" title="Are you sure you want to click on this link?">Normal test with a link</a>

    <script type="text/javascript">
    
    (function($){
      
        var timeout = 5000;

        $(document).bind("idle.idleTimer", function(){
            $("#status").html("User is idle :(").css("backgroundColor", "silver");
        });
        
        $(document).bind("active.idleTimer", function(){
             $("#status").html("User is active :D").css("backgroundColor", "yellow");
        });
        
        $.idleTimer(timeout);
        
        // correct the page
        $('#timeout').text(timeout/1000);
    
    
    })(jQuery);
    
    </script>
    
    
    <script type="text/javascript">
    
     (function($){

         var stimeout = 1800000;

         $('textarea').bind("idle.idleTimer", function(){
              $('textarea').val(function(i,v){
                return v + "textarea has been idle for "+ stimeout/1000 + ' seconds\n';
              }).css("backgroundColor", "silver");
              //MEDICINE.operationMessage("Logging out");
              /*$(".confirm").easyconfirm();
	          	$("#alert").click(function() {
	          		alert("You approved the action");
	          	});*/

              $.messager.confirm('Confirm','Please login again',function(r){  
            	    if (r){  
            	    	//MEDICINE.XHR('renewSession', 'json', '');  
            	    	window.location.href = '/store/admin/logout.action';
            	    } else {
                	    window.location.href = '/store/admin/logout.action';
            	    }
            	});
         });

         $('textarea').bind("active.idleTimer", function(){
            $('textarea').val(function(i,v){
               return v + "User is active, again! :D\n";
             }).css("backgroundColor", "yellow");
         });

         $('textarea').idleTimer(stimeout);

         // correct the page
         $('#stimeout').text(stimeout/1000);

     })(jQuery);

     </script>
    
    
    
    <a href="#">launch dialog</a>
    <div id="dia"><h4>I'm the dialog</h4></div>
    <script>
    
      function launch(){
    
        $('#dia').dialog({
    			bgiframe: true,
    			height: 140,
    			modal: true
    		});
       return false;
        
      }
      
  
      $(function(){
        $('#dia').prev().click(launch);
      })
      
    </script>
    
</body>
</html>