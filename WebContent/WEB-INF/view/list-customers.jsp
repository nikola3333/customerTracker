<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>

<html>

<head>
	<title>List Customers</title>
	
	<!-- reference our style sheet -->
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css" />
	
	
	
</head>

<body>
	
	<div id="wrapper">
	
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
			<input  hidden="true" name = "senderId" value= "${customer.id}">
		</div>
	
	</div>
	
	
		Logged Customer: <c:out value="${customer.Customer}" />

	
	
	<div id="container">
		
		<div id="content">
		
		<!-- put new button ADD CUSTOMER -->
		<input type="button" value="Add Customer" 
				onClick="window.location.href='showFormForAdd'; return false;"
				class="add-button"
		/>
			
			<table>
				
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempCustomer" items="${customers}">
				
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					<c:url var="addFriend" value="/customer/addFriend">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					<c:url var="sendMessage" value="/customer/sendMessage">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
				
					<tr>
					
						<td> ${tempCustomer.firstName} </td>
						<td> ${tempCustomer.lastName} </td>
						<td> ${tempCustomer.email} </td>
						
						<td>
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure?'))) return false">Delete</a>
							|
							<a href="${addFriend}">Add Friend</a>
							|
							<a href="${sendMessage}">Send Message</a>
						</td>
						
							
					</tr>
					
				</c:forEach>
			
			</table>
				
		</div>
		
	</div>
	
	<div id="wrapper2">
	
		<div id="header2">
			<h2>Friends</h2>
		</div>
	
	</div>
	
	<div id="container">
		
		<div id="content">
			
			<table>
				
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempCustomer" items="${friends}">
					
					<c:url var="removeFriend" value="/customer/removeFriend">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
				
					<tr>
					
						<td> ${tempCustomer.firstName} </td>
						<td> ${tempCustomer.lastName} </td>
						<td> ${tempCustomer.email} </td>
						
						<td>

							<a href="${removeFriend}"
								onclick="if (!(confirm('Are you sure?'))) return false">Remove Friend</a>
						</td>
						
							
					</tr>
					
				</c:forEach>
			
			</table>
				
		</div>
		
	</div>
	
</body>

</html>