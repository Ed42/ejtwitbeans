
$(document).ready(function () {

    setUpAdminGui();
	

			
}); // end doc ready

var cssObj = {
    'font-weight' : '300',
    'font-size' : '12px',
    'letter-spacing':'1px'      
}


function setUpAdminGui() {

   var button_position = {
        'width' : '70px' , 
        'margin':'0 4px 0 6px', 
        'padding':'0 2px 4px 2px', 
        'height':'22px'
    }
    var button = $.extend( cssObj, button_position );
    
     $( "#searchButton" ).button().css(button);
}