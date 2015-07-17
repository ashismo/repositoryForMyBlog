<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head></head>
<body>
	<h2>Student Information</h2>
	<form:form method="POST" action="addStudent" modelAttribute="student">
		<table>
			<tr>
				<td><form:label path="name">Name: </form:label></td>
				<td><form:input path="name" /></td>
				<td><span style="color:red;"><form:errors path="name"/></span></td>
			</tr>
			<tr>
				<td><form:label path="age">Age: </form:label></td>
				<td><form:input path="age" /></td>
				<td><span style="color:red;"><form:errors path="age"/></span></td>
			</tr>
			<tr>
				<td><form:label path="id">Id: </form:label></td>
				<td><form:input path="id" /></td>
				<td><span style="color:red;"><form:errors path="id"/></span></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>