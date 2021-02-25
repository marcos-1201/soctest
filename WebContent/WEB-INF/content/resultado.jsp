<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Resultado</title>
<style>
<style>
.campo-form {
  display: block;
  margin-bottom: 1em;
}
</style>
</head>
<body>
<form action=salvaResultadoAlteracao method=post>
<h1>Alterar Resultado de exame</h1>

<input type="hidden" name="resultado.codigo" value="${resultado.codigo}">

<label for="listaExames">Paciente</label>
<input id="resultado" type="text" name="resultado.paciente.nome" value="${resultado.paciente.nome}" class="campo-form" disabled>

<label for="listaExames">Exame</label>
<input id="resultado" type="text" name="resultado.exame.nome" value="${resultado.exame.nome}" class="campo-form" disabled>

<label for="data-resultado">Data</label>
<input id="data-resultado" type="text" name="resultado.dataExame" value="<fmt:formatDate pattern = "dd/MM/yyyy" 
         value = "${resultado.dataExame}" />" class="campo-form">    

<label for="resultado">Resultado</label>
<input id="resultado" type="text" name="resultado.resultado" value="${resultado.resultado}" class="campo-form">
    
<input type="submit" value="Gravar">
<a href=excluirResultado?resultado.codigo=${resultado.codigo}>Excluir</a>
</form>
</body>
</html>