<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Reserva</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <%@ include file="/jsp/layout/header.jsp" %>
    <div class="page page-md">
        <div class="header">
            <h1>Editar reserva</h1>
            <a href="${pageContext.request.contextPath}/listarReservas" class="back-link">← Voltar</a>
        </div>
        <div class="card-form">
            <c:if test="${not empty param.erro}">
                <div class="msg-erro">${param.erro}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/EditarReservaServlet" method="post">
                <input type="hidden" name="id" value="${reserva.id}">
                <div class="form-group">
                    <label for="salaId">Sala</label>
                    <select name="salaId" id="salaId" required>
                        <option value="">-- Selecione uma sala --</option>
                        <c:forEach var="s" items="${salas}">
                            <option value="${s.id}" ${s.id == reserva.sala.id ? 'selected' : ''}>${s.nome} (Cap: ${s.capacidade})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="usuarioId">Usuário</label>
                    <select name="usuarioId" id="usuarioId" required>
                        <option value="">-- Selecione um usuário --</option>
                        <c:forEach var="u" items="${usuarios}">
                            <option value="${u.id}" ${u.id == reserva.usuario.id ? 'selected' : ''}>${u.nome} (${u.tipo})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="data">Data</label>
                    <input type="date" id="data" name="data" value="${reserva.data}" required>
                </div>
                <div class="form-row">
                    <div>
                        <label for="horaInicio">Hora início</label>
                        <input type="time" id="horaInicio" name="horaInicio" value="${reserva.horaInicio}" required>
                    </div>
                    <div>
                        <label for="horaFim">Hora fim</label>
                        <input type="time" id="horaFim" name="horaFim" value="${reserva.horaFim}" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="motivo">Motivo</label>
                    <textarea id="motivo" name="motivo" rows="3" required>${reserva.motivo}</textarea>
                </div>
                <div class="form-actions">
                    <a href="${pageContext.request.contextPath}/listarReservas" class="btn-voltar">Cancelar</a>
                    <button type="submit" class="btn-salvar">Salvar alterações</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>