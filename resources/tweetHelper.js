
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
        'margin':'0 4px 8px 6px', 
        'padding':'0 2px 8px 2px', 
        'height':'22px'
    }
    var button = $.extend( cssObj, button_position );
    
     $( "#headerSearchButton" ).button().css(button);
     
     
     $( "#searchButton" ).button().css(button);
}