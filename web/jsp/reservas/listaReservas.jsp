<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Reservas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css?v=2">
</head>
<body>
    <%@ include file="/jsp/layout/header.jsp" %>
    <div class="page page-xl">
        <div class="header">
            <h1>Lista de reservas</h1>
            <a href="${pageContext.request.contextPath}/NovaReservaServlet" class="btn-novo">+ Nova Reserva</a>
        </div>
        <c:if test="${not empty param.sucesso}">
            <div class="msg sucesso">${param.sucesso}</div>
        </c:if>
        <c:if test="${not empty param.erro}">
            <div class="msg erro">${param.erro}</div>
        </c:if>
        <c:if test="${not empty reservas}">
            <div class="card card-table">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th><th>Sala</th><th>Usuário</th><th>Data</th>
                            <th>Início</th><th>Fim</th><th>Motivo</th><th>Computadores</th><th>Projetor</th><th>Status</th><th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="r" items="${reservas}">
                            <tr class="${r.data.toString() == hoje ? 'reserva-hoje' : ''}">
                                <td class="id-col">#${r.id}</td>
                                <td>${r.sala.nome}</td>
                                <td>${r.usuario.nome}</td>
                                <td>${r.data}</td>
                                <td>${r.horaInicio}</td>
                                <td>${r.horaFim}</td>
                                <td>${r.motivo}</td>
                                <td>${r.usarComputadores ? 'Sim' : 'Não'}</td>
                                <td>${r.usarProjetor ? 'Sim' : 'Não'}</td>
                                <td>
                                    <span class="badge badge-${r.status}">${r.status}</span>
                                    <c:if test="${r.data.toString() == hoje}">
                                        <span class="hoje-label">hoje</span>
                                    </c:if>
                                </td>
                                <td>
                                    <div class="acoes">
                                        <a href="${pageContext.request.contextPath}/EditarReservaServlet?id=${r.id}" class="btn-editar">Editar</a>
                                        <a href="${pageContext.request.contextPath}/CancelarReservaServlet?id=${r.id}" class="btn-cancelar" onclick="return confirm('Cancelar esta reserva?')">Cancelar</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${empty reservas}">
            <div class="card vazio">
                <p>Nenhuma reserva encontrada.</p>
                <p style="margin-top: 8px;"><a href="${pageContext.request.contextPath}/NovaReservaServlet">Criar uma nova reserva</a></p>
            </div>
        </c:if>
    </div>
</body>
</html>