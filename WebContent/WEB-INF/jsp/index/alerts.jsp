<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty vmessages.success}">
	<div class="alert alert-dismissible alert-success">
  		<button type="button" class="close" data-dismiss="alert">×</button>
  		<p class="text-center"><strong>${vmessages.success.from('usuario.autenticado')}</strong></p>
  		<p class="text-center"><strong>${vmessages.success.from('usuario.adicionado')}</strong></p>
	</div>
</c:if>

<c:if test="${not empty errors}">
	<div class="alert alert-dismissible alert-danger">
  		<button type="button" class="close" data-dismiss="alert">×</button>
  		<p class="text-center"><strong>${errors.from('usuario.negado')}</strong></p>
  		<p class="text-center"><strong>${errors.from('papel.negado')}</strong></p>
	</div>
</c:if>