<%@page import="com.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Product Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery.min.js"></script>
		<script src="Components/Product.js"></script>
	</head>
	<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Products Management V10.1</h1>
				<form id="formProduct" name="formProduct">
					Product name:
					<input id="pname" name="pname" type="text" class="form-control form-control-sm">
					<br> Product type:
					<input id="ptype" name="ptype" type="text" class="form-control form-control-sm">
					<br> Product description:
					<input id="description" name="description" type="text" class="form-control form-control-sm">
					<br> Product price:
					<input id="price" name="price" type="text" class="form-control form-control-sm">
					<br> Product quantity:
					<input id="quantity" name="quantity" type="text" class="form-control form-control-sm">
					<br> Product project Id:
					<input id="projId" name="projId" type="text" class="form-control form-control-sm">
					<br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					<input type="hidden" id="pid" name="pid" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divProductGrid">
					<%
					Product itemObj = new Product();
					out.print(itemObj.readProduct());
					%>
				</div>
			</div> 
		</div>
	</div>
	</body>
</html>