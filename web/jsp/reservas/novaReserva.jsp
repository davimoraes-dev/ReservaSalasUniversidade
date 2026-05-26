<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nova Reserva</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <%@ include file="/jsp/layout/header.jsp" %>
    <div class="page page-md">
        <div class="header">
            <h1>Nova reserva</h1>
            <a href="${pageContext.request.contextPath}/listarReservas" class="back-link">← Voltar para lista</a>
        </div>
        <div class="card-form">
            <c:if test="${not empty erro}">
                <div class="msg-erro">${erro}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/NovaReservaServlet" method="post">
                <div class="form-group">
                    <label for="salaNome">Sala</label>
                    <input type="text" id="salaNome" name="salaNome" value="${param.salaNome}" placeholder="Ex: Sala 101" required>
                </div>
                <div class="form-group">
                    <label for="usuarioNome">Usuário</label>
                    <input type="text" id="usuarioNome" name="usuarioNome" value="${param.usuarioNome}" placeholder="Ex: João Silva" required>
                </div>
                <div class="form-group">
                    <label for="data">Data</label>
                    <input type="date" name="data" id="data" value="${param.data}" required>
                </div>
                <div class="form-row">
                    <div>
                        <label for="horaInicio">Hora início</label>
                        <input type="time" name="horaInicio" id="horaInicio" value="${param.horaInicio}" required>
                    </div>
                    <div>
                        <label for="horaFim">Hora fim</label>
                        <input type="time" name="horaFim" id="horaFim" value="${param.horaFim}" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="motivo">Motivo</label>
                    <textarea name="motivo" id="motivo" placeholder="Descreva o motivo da reserva..." required>${param.motivo}</textarea>
                </div>
                <div class="form-group">
                    <label>Equipamentos</label>
                    <div class="checkbox-group">
                        <label class="checkbox-label">
                            <input type="checkbox" name="usarComputadores" ${param.usarComputadores == 'on' ? 'checked' : ''}>
                            Computadores
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="usarProjetor" ${param.usarProjetor == 'on' ? 'checked' : ''}>
                            Projetor
                        </label>
                    </div>
                </div>
                <div class="form-actions">
                    <a href="${pageContext.request.contextPath}/listarReservas" class="btn-voltar">Cancelar</a>
                    <button type="submit" class="btn-salvar">Salvar reserva</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>