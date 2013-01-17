/**
 * Contains custom JavaScript code
 */
var urlHolder = new Object();

function loadTable() {

    
    
    $.get(urlHolder.records, function(response) {
		
        $('#tableUsers').find('tbody').remove();
 		
        for (var i=0; i<response.users.length; i++) {
            var row = '<tr >';
            row += '<td><input type="radio" name="index" id="index" value="'+i+'"></td>';
            row += '<td class="ui-widget ui-corner-all adminRow">' + response.users[i].username + '</td>';
            row += '<td class="ui-widget ui-corner-all adminRow">' + response.users[i].email + '</td>';
            row += '<td class="ui-widget ui-corner-all adminRowPwd">' + response.users[i].password + '</td>';
            row += '<td class="ui-widget ui-corner-all adminRow">' + getRole(response.users[i].role) + '</td>';
            row += '</tr>';
            $('#tableUsers').append(row);
        }
 		
        $('#tableUsers').data('model', response.users);
        toggleForms('hide'); ;
    });
}
// some reason need xtra parameters here !! note async false
function submitNewRecord() {
    var uname = $('#newUsername').val();
    var pwd = $('#newPassword').val();
    var email = $('#newEmail').val();
    var role = $('#newRole').val();

    $.ajax({ 
        url: urlHolder.add, 
        type: 'POST', 
        data: {
            username:uname,
            password:pwd,
            email:email,
            role:role
        }, 
        dataType: 'text html',
        async: false,
        success: function(response) {
            if (response != null) {
                loadTable();
                toggleForms('hide');
                ;
                toggleCrudButtons('show');
                alert('Success! Record has been added.');
            } else {
                alert('Failure! An error has occurred!');
            }
        }
    });

        
}

function submitDeleteRecord() {
    var selected = $('input:radio[name=index]:checked').val();
	
    $.post(urlHolder.del, {
        username: $('#tableUsers').data('model')[selected].username
    }, 
    function(response) {
        if (response == true) {
            loadTable(urlHolder.records);
            alert('Success! Record has been deleted.');
        } else {
            alert('Failure! An error has occurred!');
        }
    }
    );
}

function submitUpdateRecord() {
    $.post(urlHolder.edit, {
        username: $('#editUsername').val(),
        email: $('#editEmail').val(),
        password: $('#editPassword').val(),
        originaluname: urlHolder.unameStore, 
        role: $('#editRole').val()
    }, 
    function(response) {
        if (response != null) {
            loadTable();
            toggleForms('hide');
            ;
            toggleCrudButtons('show');
            alert('Success! Record has been edited.');
        } else {
            alert('Failure! An error has occurred!');
        }
    }
    );
}

function getRole(role) {
    if (role == 1) {
        return 'Admin';
    } else if (role == 2) {
        return 'Regular';
    } else {
        return 'Unknown';
    } 
}

function hasSelected() {
    var selected = $('input:radio[name=index]:checked').val();
    if (selected == undefined) { 
        alert('Select a record first!');
        return false;
    }
	
    return true;
}

function fillEditForm() {
    var selected = $('input:radio[name=index]:checked').val();
    $('#editUsername').val( $('#tableUsers').data('model')[selected].username );
    $('#editEmail').val( $('#tableUsers').data('model')[selected].email );
    $('#editPassword').val( $('#tableUsers').data('model')[selected].password );
    $('#editRole').val( $('#tableUsers').data('model')[selected].role );
     urlHolder.unameStore = $('#tableUsers').data('model')[selected].username;
}

function resetNewForm() {
    $('#newUsername').val('');
    $('#newPassword').val('');
    $('#newEmail').val('');
    $('#newRole').val('2');
}

function resetEditForm() {
    $('#editUsername').val('');
    $('#editEmail').val('');
    $('#editRole').val('2');
}

function toggleForms(id) {
    if (id == 'hide') {
        $('#newForm').hide();
        $('#editForm').hide();
		
    } else if (id == 'new') {
        resetNewForm();
        $('#newForm').show();
        $('#editForm').hide();
 		
    } else if (id == 'edit') {
        resetEditForm();
        $('#newForm').hide();
        $('#editForm').show();
    }
}

function toggleCrudButtons(id) {
    if (id == 'show') {
        $('#newBtn').removeAttr('disabled');
        $('#editBtn').removeAttr('disabled');
        $('#deleteBtn').removeAttr('disabled');
        $('#reloadBtn').removeAttr('disabled');
		
    } else if (id == 'hide'){
        $('#newBtn').attr('disabled', 'disabled');
        $('#editBtn').attr('disabled', 'disabled');
        $('#deleteBtn').attr('disabled', 'disabled');
        $('#reloadBtn').attr('disabled', 'disabled');
    }
}
