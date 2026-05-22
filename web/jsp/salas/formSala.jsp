<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${not empty sala ? 'Editar' : 'Nova'} Sala</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <%@ include file="/jsp/layout/header.jsp" %>
    <div class="page page-sm">
        <div class="header">
            <h1><c:choose><c:when test="${not empty sala}">Editar sala</c:when><c:otherwise>Nova sala</c:otherwise></c:choose></h1>
            <a href="${pageContext.request.contextPath}/listarSalas" class="back-link">← Voltar</a>
        </div>
        <div class="card-form">
            <c:if test="${not empty erro}">
                <div class="msg-erro">${erro}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/gerenciarSala" method="post">
                <input type="hidden" name="id" value="${sala.id}">
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" value="${sala.nome}" placeholder="Ex: Sala 101" required autofocus>
                </div>
                <div class="form-row">
                    <div>
                        <label for="capacidade">Capacidade</label>
                        <input type="number" id="capacidade" name="capacidade" value="${sala.capacidade}" placeholder="Ex: 30" min="1" required>
                    </div>
                    <div>
                        <label for="localizacao">Localização</label>
                        <input type="text" id="localizacao" name="localizacao" value="${sala.localizacao}" placeholder="Ex: Predio 1" required>
                    </div>
                </div>
                <div class="form-group">
                    <label>Projetor</label>
                    <label class="checkbox-group">
                        <input type="checkbox" name="temProjetor" ${sala.temProjetor ? 'checked' : ''}>
                        <span>Possui projetor</span>
                    </label>
                </div>
                <div class="form-group">
                    <label>Computador</label>
                    <label class="checkbox-group">
                        <input type="checkbox" name="temComputador" ${sala.temComputador ? 'checked' : ''}>
                        <span>Possui computador</span>
                    </label>
                </div>
                <div class="form-actions">
                    <a href="${pageContext.request.contextPath}/listarSalas" class="btn-voltar">Cancelar</a>
                    <button type="submit" class="btn-salvar">Salvar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>