<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Salas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <%@ include file="/jsp/layout/header.jsp" %>
    <div class="page page-lg">
        <div class="header">
            <h1>Salas</h1>
            <a href="${pageContext.request.contextPath}/gerenciarSala?acao=novo" class="btn-novo">+ Nova sala</a>
        </div>
        <c:if test="${not empty param.sucesso}">
            <div class="msg sucesso">${param.sucesso}</div>
        </c:if>
        <c:if test="${not empty param.erro}">
            <div class="msg erro">${param.erro}</div>
        </c:if>
        <c:if test="${not empty salas}">
            <div class="card">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th><th>Nome</th><th>Capacidade</th><th>Localização</th><th>Projetor</th><th>Computador</th><th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="s" items="${salas}">
                            <tr>
                                <td class="id-col">#${s.id}</td>
                                <td><a href="${pageContext.request.contextPath}/detalhesSala?id=${s.id}" style="color:#111;text-decoration:underline;">${s.nome}</a></td>
                                <td>${s.capacidade} pessoas</td>
                                <td>${s.localizacao}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${s.temProjetor}"><span class="badge badge-sim">Sim</span></c:when>
                                        <c:otherwise><span class="badge badge-nao">Não</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${s.temComputador}"><span class="badge badge-sim">Sim</span></c:when>
                                        <c:otherwise><span class="badge badge-nao">Não</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <div class="acoes">
                                        <a href="${pageContext.request.contextPath}/gerenciarSala?acao=editar&id=${s.id}" class="btn-editar">Editar</a>
                                        <a href="${pageContext.request.contextPath}/gerenciarSala?acao=excluir&id=${s.id}" class="btn-excluir" onclick="return confirm('Excluir esta sala?')">Excluir</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${empty salas}">
            <div class="card vazio">
                <p>Nenhuma sala cadastrada.</p>
                <p style="margin-top: 8px;"><a href="${pageContext.request.contextPath}/gerenciarSala?acao=novo">Cadastrar primeira sala</a></p>
            </div>
        </c:if>
    </div>
</body>
</html>