<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SOCTest</title>
<style>
.listas {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid black;
}
</style>
</head>
<body>
<form action=novoPaciente method=post>
<h1>Pacientes</h1>
<input type="submit" value="Incluir paciente">
<table>
<thead>
<tr>
<th>PACIENTE</th>
</tr>
</thead>
<tbody>
<c:forEach var="p" items="${pacientes}">
<tr>
<td><a href=paciente?paciente.codigo=${p.codigo}> ${p.nome} </a></td>
</tr>
</c:forEach>
</tbody>
</table>
</form>

<form action=novoResultado method=post>
<h1>Resultados de exame</h1>
<input type="submit" value="Incluir resultado">
<table class="listas">
<thead>
<tr>
<th>PACIENTE</th>
<th>EXAME</th>
<th>DATA</th>
<th>RESULTADO</th>
</tr>
</thead>
<tbody>
<c:forEach var="r" items="${resultados}">
<tr>
<td>${r.paciente.nome}</td>
<td>${r.exame.nome}</td>
<td><a href=resultado?resultado.codigo=${r.codigo}> <fmt:formatDate pattern = "dd/MM/yyyy" 
         value = "${r.dataExame}" /> </a></td>
<td>${r.resultado}</td>
</tr>
</c:forEach>
</tbody>
</table>
</form>
</body>
</html>