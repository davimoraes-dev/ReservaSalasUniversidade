<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema de Reservas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body class="home-body">
    <div class="home-page">
        <nav class="home-nav">
            <a href="${pageContext.request.contextPath}/home" class="home-nav-brand">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/>
                    <polyline points="9 22 9 12 15 12 15 22"/>
                </svg>
                Home
            </a>
            <div class="home-nav-links">
                <a href="${pageContext.request.contextPath}/listarReservas">Reservas</a>
                <a href="${pageContext.request.contextPath}/listarSalas">Salas</a>
                <a href="${pageContext.request.contextPath}/listarUsuarios">Usuários</a>
                <a href="${pageContext.request.contextPath}/jsp/equipe.jsp">Equipe</a>
            </div>
        </nav>

        <div class="home-conteudo">
            <div class="home-hero">
                <div class="home-hero-left">
                    <div class="home-tag"><span class="home-tag-dot"></span> Sistema ativo</div>
                    <div class="home-title">Sistema de Reserva de Salas</div>
                    <div class="home-subtitle">Universidade — Gestão de salas e auditórios</div>
                </div>
                <div class="home-clock">
                    <div class="home-time" id="hora"></div>
                    <div class="home-date" id="data"></div>
                </div>
            </div>

            <div class="home-stats">
                <div class="home-stat">
                    <div class="home-stat-icon s-green">
                        <svg viewBox="0 0 24 24" fill="none" stroke="#3B6D11" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <rect x="3" y="4" width="18" height="18" rx="2"/>
                            <line x1="16" y1="2" x2="16" y2="6"/>
                            <line x1="8" y1="2" x2="8" y2="6"/>
                            <line x1="3" y1="10" x2="21" y2="10"/>
                        </svg>
                    </div>
                    <div>
                        <div class="home-stat-num">${reservasHoje}</div>
                        <div class="home-stat-label">Reservas hoje</div>
                    </div>
                </div>
                <div class="home-stat">
                    <div class="home-stat-icon s-blue">
                        <svg viewBox="0 0 24 24" fill="none" stroke="#185FA5" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/>
                            <rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/>
                        </svg>
                    </div>
                    <div>
                        <div class="home-stat-num">${totalSalas}</div>
                        <div class="home-stat-label">Salas cadastradas</div>
                    </div>
                </div>
                <div class="home-stat">
                    <div class="home-stat-icon s-orange">
                        <svg viewBox="0 0 24 24" fill="none" stroke="#854F0B" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                            <circle cx="12" cy="7" r="4"/>
                        </svg>
                    </div>
                    <div>
                        <div class="home-stat-num">${totalUsuarios}</div>
                        <div class="home-stat-label">Usuários ativos</div>
                    </div>
                </div>
            </div>

            <div class="home-bottom">
                <a href="${pageContext.request.contextPath}/NovaReservaServlet" class="home-nova-reserva">
                    <div>
                        <div class="home-nova-title">+ Nova reserva</div>
                        <div class="home-nova-desc">Criar uma reserva agora</div>
                    </div>
                    <div class="home-nova-arrow">→</div>
                </a>
                <a href="${pageContext.request.contextPath}/listarReservas" class="home-ver-reservas">
                    <div>
                        <div class="home-ver-title">Ver reservas</div>
                        <div class="home-ver-desc">Listar todas as reservas</div>
                    </div>
                    <div class="home-ver-arrow">→</div>
                </a>
                <a href="${pageContext.request.contextPath}/listarSalas" class="home-atalho">
                    <div class="home-atalho-top">
                        <div class="home-atalho-icon s-blue">
                            <svg viewBox="0 0 24 24" fill="none" stroke="#185FA5" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                <rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/>
                                <rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/>
                            </svg>
                        </div>
                        <span class="home-atalho-arrow">→</span>
                    </div>
                    <div>
                        <div class="home-atalho-titulo">Salas</div>
                        <div class="home-atalho-desc">Cadastrar e editar salas</div>
                    </div>
                </a>
                <a href="${pageContext.request.contextPath}/listarUsuarios" class="home-atalho">
                    <div class="home-atalho-top">
                        <div class="home-atalho-icon s-orange">
                            <svg viewBox="0 0 24 24" fill="none" stroke="#854F0B" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                                <circle cx="12" cy="7" r="4"/>
                            </svg>
                        </div>
                        <span class="home-atalho-arrow">→</span>
                    </div>
                    <div>
                        <div class="home-atalho-titulo">Usuários</div>
                        <div class="home-atalho-desc">Cadastrar e editar usuários</div>
                    </div>
                </a>
                <a href="${pageContext.request.contextPath}/jsp/equipe.jsp" class="home-atalho">
                    <div class="home-atalho-top">
                        <div class="home-atalho-icon s-green">
                            <svg viewBox="0 0 24 24" fill="none" stroke="#3B6D11" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
                                <circle cx="9" cy="7" r="4"/>
                                <path d="M23 21v-2a4 4 0 0 0-3-3.87"/>
                                <path d="M16 3.13a4 4 0 0 1 0 7.75"/>
                            </svg>
                        </div>
                        <span class="home-atalho-arrow">→</span>
                    </div>
                    <div>
                        <div class="home-atalho-titulo">Equipe</div>
                        <div class="home-atalho-desc">Conheça os desenvolvedores</div>
                    </div>
                </a>
            </div>
        </div>
    </div>

    <script>
        function atualizar() {
            document.getElementById('hora').textContent = new Date().toLocaleTimeString('pt-BR');
            document.getElementById('data').textContent = new Date().toLocaleDateString('pt-BR', {
                weekday: 'long', day: 'numeric', month: 'long'
            });
        }
        atualizar();
        setInterval(atualizar, 1000);
    </script>
</body>
</html>