<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Paciente</title>
<style>
.campo-form {
  display: block;
  margin-bottom: 1em;
}
</style>
</head>
<body>
<form action="pacienteInclusao" method="post">
<label for="paciente-nome">Nome do paciente</label>
<input id="paciente-nome" type="text" name="paciente.nome" class="campo-form"> 

<input type="submit" value="Gravar">
</form>
</body>
</html>