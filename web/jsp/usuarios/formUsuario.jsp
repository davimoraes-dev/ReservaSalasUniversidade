<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${not empty usuario ? 'Editar' : 'Novo'} Usuário</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <%@ include file="/jsp/layout/header.jsp" %>
    <div class="page page-sm">
        <div class="header">
            <h1>
                <c:choose>
                    <c:when test="${not empty usuario}">Editar usuário</c:when>
                    <c:otherwise>Novo usuário</c:otherwise>
                </c:choose>
            </h1>
            <a href="${pageContext.request.contextPath}/listarUsuarios" class="back-link">← Voltar</a>
        </div>
        <div class="card-form">
            <c:if test="${not empty erro}">
                <div class="msg-erro">${erro}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/gerenciarUsuario" method="post">
                <input type="hidden" name="id" value="${usuario.id}">
                <div class="form-group">
                    <label for="nome">Nome do usuário</label>
                    <input type="text" id="nome" name="nome" value="${usuario.nome}" placeholder="Ex: João Silva" required autofocus>
                </div>
                <div class="form-group">
                    <label for="tipo">Tipo</label>
                    <select id="tipo" name="tipo" required>
                        <option value="">Selecione o tipo</option>
                        <option value="aluno" ${usuario.tipo == 'aluno' ? 'selected' : ''}>Aluno</option>
                        <option value="professor" ${usuario.tipo == 'professor' ? 'selected' : ''}>Professor</option>
                        <option value="admin" ${usuario.tipo == 'admin' ? 'selected' : ''}>Admin</option>
                    </select>
                </div>
                <div class="form-actions">
                    <a href="${pageContext.request.contextPath}/listarUsuarios" class="btn-voltar">Cancelar</a>
                    <button type="submit" class="btn-salvar">Salvar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>