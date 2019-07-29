<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>

	<title>Messaging</title>
	
</head>

<body>

	<div>
		<h3>Chat</h3>
				<input type = "text"  hidden="true" name = "senderId" value= ${customer.id}>
			<p>
			<b>Sender: </b>${senderId.firstName} ${senderId.lastName}<br>
			<b>Reciever: </b>${customerReciever.firstName} ${customerReciever.lastName}	
			</p>

			
			
		
		<form action="MessageSent" modelAttribute="customer" method="POST" path="customer"]>

		<input type = "text"  hidden="true" name = "id" value= ${customerReciever.id}>
		
		<label>Message:</label>
		<input name="Message" type="text" required size="90"/>
		
		<input type="submit" value="SendMessage" />
		
		</form>
		
	</div>


</body>
</html>