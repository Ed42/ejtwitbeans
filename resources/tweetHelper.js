
$(document).ready(function () {
	
		 
    $( "#centerTabs" ).tabs();
    $('#centerTabs div').hide();
    setTabEvents();
	

			
}); // end doc ready

var cssObj = {
    'font-weight' : '300',
    'font-size' : '14px',
    'letter-spacing':'2px'      
}

function setTabEvents() {
		
    var tabContainers = $('div#centerTabs > div');
    $('div#centerTabs ul#viewInfoTabs a').click(function () {
        // removeDomHandlerdynQuery(); removeDomHandlerCsv();
        tabContainers.hide().filter(this.hash).show();
        if(this.hash =='#tab-1')$("#Table1").styleTable();
        if(this.hash =='#tab-2')$("#Table2").styleTable();
      
        $('div#centerTabs ul#viewInfoTabs li').removeClass('ui-tabs-selected ui-state-active ui-tabs-active');
        $(this).parent().addClass('ui-tabs-selected ui-state-active ui-tabs-active');		        
        return false;
    }).filter(':first').click();
		
}


function setUpAdminGui() {
    
    
    var button_position = {
        'width' : '180px' , 
        'margin':'12px', 
        'height':'28px'
    }
    var button = $.extend( cssObj, button_position );
     
      
    $( "#apuser" ).button().css(button);
    $( "#dpuser" ).button().css(button);
    // add admin user
    $("#apuser").live("click", function(event){
       
        $(dialog ).remove();$(dynaForm).remove();
      
        event.preventDefault();
        var dialog = 
        $('<div id = "dynQueryService"><a class = "saveUserButton" href="#" rel ="saveQ">Save User</a><div id ="aForm" ></div><div id = "qTa"></div></div>').dialog(	
        {
            autoOpen: false, 
            minWidth: 600, 
            height: 440,	
            title: 'Add Portal User',						
            buttons: [{
                text: "Close", 
                click: function() {
                    $(this).dialog("close");
                }
            }]	
        });
        // open the add  admin user dialog
        dialog.dialog('open');
        // second time for gui bug
          var button_position = {
        'width' : '180px' , 
        'margin':'12px', 
        'height':'28px'
            }
        var button = $.extend( cssObj, button_position );
        $( "a.saveUserButton" ).button().css(button);
         
         // add user form
        var dynaForm =  $( '<div class = "ui-widget" method="post" action="addUser">');
        $(dynaForm).append('<tr><td ><label class = "ui-widget-content" path="username" >User Name</label></td>');
        $(dynaForm).append('<td ><input class = "uname" path="username"  size="30" maxlength="255" style="height:28px;font-size:14px;"></input></td></tr>');
        $(dynaForm).append('<tr><td><label class = "ui-widget-content" path="email" style="width:200px;" >Email</label></td>');
        $(dynaForm).append('<td><input class = "email"  path="email"  size="30" maxlength="255" style="height:28px;font-size:14px;"></input></td></tr>');
        $(dynaForm).append('<tr><td><label class = "ui-widget-content" path="password" style="width:200px;">Password</label></td>');
        $(dynaForm).append('<td><input class = "pword" type="password" path="password"  size="30" maxlength="255" style="height:28px;font-size:14px;"></input></td></tr></div>');
                    
                    
        var form_position = {
            'padding' : '4px 0 14px 4px' , 
            'height' : '380px' , 
            'width' : '280px' , 
            'padding':'6px 0 12px 2px'
        } 
        var form = $.extend( cssObj, form_position);
        $('div#aForm').append(dynaForm).css(form);
                    
        $( "a.saveUserButton" ).live("click", function(event){    
            $.ajax({
                url:'/addPortalUser',
                type: "POST",
                contentType: 'application/json',
                data:  JSON.stringify({
                    username: $( "input.uname" ).val(), 
                    email:$( "input.email" ).val(), 
                    password:$( "input.pword" ).val()
                }),
                processData: false,
                dataType: "json",
                success: function(result) {
                    // var json = $.parseJSON(result);// sent as json so returns as json no need parse?
                   // alert(result.response);
                    if (result.response=='ok'){ 
                      //  alert('user added ok');               
                        dialog.dialog('close');                   
                        location.reload();
                    }
                }
            });// end post 
  
        });// click ends saveUserButton   
                    
                    
    });// ends dynamic query
}




(function ($) {
    $.fn.styleTable = function (options) {
        //Set default option for style
        var defaults = {
            css: 'styleTable'
        };
        options = $.extend(defaults, options);

        //Check if element is visible
        if($(this).is(':visible')){

            //Check if class is aleady applied
            if(! ($(this).hasClass(options.css)) ){                     

                //Loop through elements of table and apply styling
                return this.each(function () {

                    input = $(this);
                    input.addClass(options.css);

                    input.find("tr").live('mouseover mouseout', function (event) {
                        if (event.type == 'mouseover') {
                            $(this).children("td").addClass("ui-state-hover");
                        } else {
                            $(this).children("td").removeClass("ui-state-hover");
                        }
                    });

                    input.find("th").addClass("ui-state-default");
                    input.find("td").addClass("ui-widget-content");

                    input.find("tr").each(function () {
                        $(this).children("td:not(:first)").addClass("first");
                        $(this).children("th:not(:first)").addClass("first");
                    });
                });
            }
        }
    };
})(jQuery);


