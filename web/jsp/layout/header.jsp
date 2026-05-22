<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="topbar">
    <a href="${pageContext.request.contextPath}/home" class="topbar-home">
        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/>
            <polyline points="9 22 9 12 15 12 15 22"/>
        </svg>
        Home
    </a>
    <div class="topbar-links">
        <a href="${pageContext.request.contextPath}/listarReservas">Reservas</a>
        <a href="${pageContext.request.contextPath}/listarSalas">Salas</a>
        <a href="${pageContext.request.contextPath}/listarUsuarios">Usuários</a>
        <a href="${pageContext.request.contextPath}/jsp/equipe.jsp">Equipe</a>
    </div>
</div>