<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuários</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <%@ include file="/jsp/layout/header.jsp" %>
    <div class="page page-lg">
        <div class="header">
            <h1>Usuários</h1>
            <a href="${pageContext.request.contextPath}/gerenciarUsuario?acao=novo" class="btn-novo">+ Novo usuário</a>
        </div>
        <c:if test="${not empty param.sucesso}">
            <div class="msg sucesso">${param.sucesso}</div>
        </c:if>
        <c:if test="${not empty param.erro}">
            <div class="msg erro">${param.erro}</div>
        </c:if>
        <c:if test="${not empty usuarios}">
            <div class="card">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th><th>Nome</th><th>Tipo</th><th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="u" items="${usuarios}">
                            <tr>
                                <td class="id-col">#${u.id}</td>
                                <td>${u.nome}</td>
                                <td><span class="badge badge-${u.tipo}">${u.tipo}</span></td>
                                <td>
                                    <div class="acoes">
                                        <a href="${pageContext.request.contextPath}/gerenciarUsuario?acao=editar&id=${u.id}" class="btn-editar">Editar</a>
                                        <a href="${pageContext.request.contextPath}/gerenciarUsuario?acao=excluir&id=${u.id}" class="btn-excluir" onclick="return confirm('Excluir este usuário?')">Excluir</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${empty usuarios}">
            <div class="card vazio">
                <p>Nenhum usuário cadastrado.</p>
                <p style="margin-top: 8px;"><a href="${pageContext.request.contextPath}/gerenciarUsuario?acao=novo">Cadastrar primeiro usuário</a></p>
            </div>
        </c:if>
    </div>
</body>
</html>