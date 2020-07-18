<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>

<title>List Customer</title>

<!-- reference our style sheet  -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css " />


</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM customer Relationship Manager</h2>
		</div>
	</div>
	<div id="container">
		<div id="context">
			
			<!-- Put new button : Add Customer -->
			<input type = "button" value ="Add Customer"
				onclick="window.location.href = 'showFormForAdd'; return false;"
				class = "add-button"
			/>
			 <!--  add a search box -->
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>
			
			
			<!--  Add out HTML Table Here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<!-- Loop over and print our Customer -->
				<c:forEach var="tempCustomer" items="${customers}">
				
					<!-- Construct an "update"  link with customer id-->
					<c:url var ="updateLink" value= "/customer/showFormForUpdate">
							<c:param name="customerId" value = "${tempCustomer.id }"/>
					</c:url>
					
					<!-- Construct an "Delete"  link with customer id-->
					<c:url var ="deleteLink" value= "/customer/delete">
							<c:param name="customerId" value = "${tempCustomer.id }"/>
					</c:url>
					
					
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
						<!-- Display the update link -->
						<a href = "${updateLink}">Update</a>
						|
						<a href = "${deleteLink}"
						onclick="if(!(confirm('Are You Sure you want to delete thid customer?'))) return false"
						>Delete</a>
						</td>
						
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>

</body>


</html>