<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalhes do Usuário</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <%@ include file="/jsp/layout/header.jsp" %>
    <div class="page page-lg">
        <div class="header">
            <h1>${usuario.nome}</h1>
            <a href="${pageContext.request.contextPath}/listarUsuarios" class="back-link">← Voltar para usuários</a>
        </div>
        <div class="info-card">
            <div class="info-item">
                <div class="info-label">Tipo</div>
                <div class="info-value"><span class="badge badge-${usuario.tipo}">${usuario.tipo}</span></div>
            </div>
            <div class="info-item">
                <div class="info-label">Email</div>
                <div class="info-value">${usuario.email}</div>
            </div>
        </div>
        <p class="section-title">Reservas deste usuário</p>
        <p class="total">${reservas.size()} reservas encontradas</p>
        <c:if test="${not empty reservas}">
            <div class="card">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th><th>Sala</th><th>Data</th><th>Início</th><th>Fim</th><th>Motivo</th><th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="r" items="${reservas}">
                            <tr>
                                <td class="id-col">#${r.id}</td>
                                <td>${r.sala.nome}</td>
                                <td>${r.data}</td>
                                <td>${r.horaInicio}</td>
                                <td>${r.horaFim}</td>
                                <td>${r.motivo}</td>
                                <td><span class="badge badge-${r.status}">${r.status}</span></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${empty reservas}">
            <div class="card vazio">
                <p>Nenhuma reserva encontrada para este usuário.</p>
            </div>
        </c:if>
    </div>
</body>
</html>