
$(document).ready(function () {

    setUpAdminGui();
	

			
}); // end doc ready

var cssObj = {
    'font-weight' : '300',
    'font-size' : '9px',
    'letter-spacing':'1px'      
}


function setUpAdminGui() {

   var button_position = {
        'width' : '50px' , 
        'margin':'0 4px 8px 6px', 
        'padding':'0 2px 8px 2px', 
        'height':'20px'
    }
    var button = $.extend( cssObj, button_position );
    
     $( "#headerSearchButton" ).button().css(button);
     
     
     $( "#searchButton" ).button().css(button);
}