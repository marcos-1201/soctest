<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.campo-form {
	display: block;
	margin-bottom: 1em;
}
</style>
</head>
<body>
<form action="atualizarPaciente" method="post">
<input type="hidden" name="paciente.codigo" value="${paciente.codigo}">
<label for="paciente-nome">Nome do paciente</label>
<input id="paciente-nome" type="text" name="paciente.nome" value="${paciente.nome}" class="campo-form"> 

<input type="submit" value="Gravar">
<a href=excluirPaciente?paciente.codigo=${paciente.codigo}>Excluir</a>
</form>
</body>
</html>