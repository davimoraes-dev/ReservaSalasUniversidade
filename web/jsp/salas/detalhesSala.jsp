<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalhes da Sala</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <%@ include file="/jsp/layout/header.jsp" %>
    <div class="page page-lg">
        <div class="header">
            <h1>${sala.nome}</h1>
            <a href="${pageContext.request.contextPath}/listarSalas" class="back-link">← Voltar para salas</a>
        </div>
        <div class="info-card">
            <div class="info-item">
                <div class="info-label">Capacidade</div>
                <div class="info-value">${sala.capacidade} pessoas</div>
            </div>
            <div class="info-item">
                <div class="info-label">Localização</div>
                <div class="info-value">${sala.localizacao}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Equipamentos</div>
                <div class="info-value">${salaDecorada.descricao}</div>
            </div>
        </div>
        <p class="section-title">Reservas desta sala</p>
        <p class="total">${reservas.size()} reservas encontradas</p>
        <c:if test="${not empty reservas}">
            <div class="card">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th><th>Usuário</th><th>Data</th><th>Início</th><th>Fim</th><th>Motivo</th><th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="r" items="${reservas}">
                            <tr>
                                <td class="id-col">#${r.id}</td>
                                <td>${r.usuario.nome}</td>
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
                <p>Nenhuma reserva encontrada para esta sala.</p>
            </div>
        </c:if>
    </div>
</body>
</html>