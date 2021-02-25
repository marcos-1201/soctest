<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Resultado</title>
<style>
<
style>.campo-form {
	display: block;
	margin-bottom: 1em;
}
</style>
</head>
<body>
	<form action="salvaResultadoInclusao" method="post">
		<h1>Incluir Resultado de exame</h1>

		<label for="listaPacientes">Pacientes</label> 
		<select
			id="listaPacientes" name="resultado.paciente.codigo" class="campo-form">
			<c:forEach var="p" items="${pacientes}">
				<option value="${p.codigo}">${p.nome}</option>
			</c:forEach>
		</select> 
		
		<label for="listaExames">Exames</label> 
		<select 
			id="listaExames" name="resultado.exame.codigo" class="campo-form">
			<c:forEach var="e" items="${exames}">
				<option value="${e.codigo}">${e.nome}</option>
			</c:forEach>
		</select> 
		
		<label for="data-resultado">Data</label> 
		<input id="data-resultado" type="text" name="resultado.dataExame" "class="campo-form"> 
			
		<label for="resultado">Resultado</label> 
		<input id="resultado" type="text" name="resultado.resultado" "class="campo-form"> 
			
		<input type="submit" value="Gravar">
	</form>
</body>
</html>