<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<c:url value="/admin/records" var="recordsUrl"/>
<c:url value="/admin/create" var="addUrl"/>
<c:url value="/admin/update" var="editUrl"/>
<c:url value="/admin/delete" var="deleteUrl"/>

<html>
<head>
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery.ui.all.css" type="text/css">
        <link rel="stylesheet" href="resources/css/mint-choc/jquery-ui-1.9.2.custom.min.css" type="text/css" />
        <!-- Load jQuery first before jQuery UI! -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.7.1/jquery-ui.min.js"></script>
	<link rel='stylesheet' type='text/css' media='screen' href="/resources/css/style.css"/>
        <link rel='stylesheet' type='text/css' media='screen' href="/resources/css/admin.css"/>
	<script type='text/javascript' src="/resources/custom.js"></script>
       

	<title>User Records</title>
	
	<script type='text/javascript'>
	$(function() {
		// init
                
		urlHolder.records = '${recordsUrl}';
		urlHolder.add = '${addUrl}';
		urlHolder.edit = '${editUrl}';
		urlHolder.del = '${deleteUrl}';
		loadTable();
		
		$('#newBtn').click(function() { 
			toggleForms('new');
			toggleCrudButtons('hide');
		});
		
		$('#editBtn').click(function() { 
			if (hasSelected()) {
				toggleForms('edit');
				toggleCrudButtons('hide');
				fillEditForm();
			}
		});
		
		$('#reloadBtn').click(function() { 
			loadTable();
		});

		$('#deleteBtn').click(function() {
			if (hasSelected()) { 
				submitDeleteRecord();
			}
		});
		
		$('#newForm').submit(function() {              
			submitNewRecord();
                        event.preventDefault();
		});
		
		$('#editForm').submit(function() {
			event.preventDefault();
			submitUpdateRecord();
		});

		$('#closeNewForm').click(function() { 
			toggleForms('hide'); 
			toggleCrudButtons('show');
		});
		
		$('#closeEditForm').click(function() { 
			toggleForms('hide'); 
			toggleCrudButtons('show');
		});
	});
	</script>
</head>

<body>
        <!--span id="menu-username"><%=SecurityContextHolder.getContext().getAuthentication().getName()%></span -->
         <div id="header">
			<div id="header-topbar">
                          
                          <%@ include file="/WEB-INF/views/includes/navigationAdmin.jsp" %>         
                            <h1 id='banner'>Admin Record System</h1>
			</div>
		</div>
	
	<hr/>
	
	<table class="ui-widget ui-corner-all" id='tableUsers'>
		<caption></caption>
		<thead>
			<tr>
				<th></th>
				<th class="ui-widget ui-corner-all">Username</th>
				<th class="ui-widget ui-corner-all">Email</th>
				<th class="ui-widget ui-corner-all">Password</th>
				<th class="ui-widget ui-corner-all">Role</th>
			</tr>
		</thead>
	</table>
	
	<div id='controlBar'>
		<input class="ui-widget-header ui-widget ui-corner-all" type='button' value='New' id='newBtn' />
		<input class="ui-widget-header ui-widget ui-corner-all" type='button' value='Delete' id='deleteBtn' />
		<input class="ui-widget-header ui-widget ui-corner-all" type='button' value='Edit' id='editBtn' />
		<input class="ui-widget-header ui-widget ui-corner-all" type='button' value='Reload' id='reloadBtn' />
	</div>
	
	<div id='newForm'>
		<form>
  			<fieldset>
				<legend>Create New Record</legend>
				<label class="ui-widget-header ui-widget ui-corner-all" for='newUsername'>Username</label><input type='text' id='newUsername'/><br/>
				<label class="ui-widget-header ui-widget ui-corner-all" for='newPassword'>Password</label><input type='password' id='newPassword'/><br/>
				<label class="ui-widget-header ui-widget ui-corner-all" for='newFirstName'>Email</label><input type='text' id='newEmail'/><br/>
				<label class="ui-widget-header ui-widget ui-corner-all" for='newRole'>Role</label>
					<select id='newRole'>
						<option value='1'>Admin</option>
						<option value='2' selected='selected'>Regular</option>
					</select>
  			</fieldset>
			<input class="ui-widget-header ui-widget ui-corner-all" type='button' value='Close' id='closeNewForm' />
			<input class="ui-widget-header ui-widget ui-corner-all" type='submit' value='Submit'/>
		</form>
	</div>
	
	<div id='editForm'>
		<form>
  			<fieldset>
				<legend>Edit Record</legend>
				<label class="ui-widget-header ui-widget ui-corner-all" for='editFirstName'>Username</label><input type='text' id='editUsername'/><br/>
				<label class="ui-widget-header ui-widget ui-corner-all" for='editLastName'>Email</label><input type='text' id='editEmail'/><br/>
                                <label class="ui-widget-header ui-widget ui-corner-all" for='newPassword'>Password</label><input type='password' id='editPassword'/><br/>
				<label class="ui-widget-header ui-widget ui-corner-all" for='editRole'>Role</label>
					<select id='editRole'>
						<option value='1'>Admin</option>
						<option value='2' selected='selected'>Regular</option>
					</select>
			</fieldset>
			<input class="ui-widget-header ui-widget ui-corner-all" type='button' value='Close' id='closeEditForm' />
			<input class="ui-widget-header ui-widget ui-corner-all" type='submit' value='Submit'/>
		</form>
	</div>
	
</body>
</html>