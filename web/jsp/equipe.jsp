<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nossa Equipe - ReservaSalas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
    <style>
        .equipe-grid {
            display: flex;
            justify-content: center;
            gap: 24px;
            flex-wrap: wrap;
            margin-top: 2rem;
        }

        .equipe-card {
            background: #fff;
            border: 1px solid #e5e7eb;
            border-radius: 16px;
            padding: 2rem 1.5rem;
            width: 220px;
            text-align: center;
            transition: box-shadow 0.2s;
        }

        .equipe-card:hover {
            box-shadow: 0 4px 20px rgba(0,0,0,0.08);
        }

        .equipe-avatar {
            width: 64px;
            height: 64px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 1rem;
        }

        .equipe-avatar svg {
            width: 32px;
            height: 32px;
        }

        .avatar-blue  { background: #E6F1FB; }
        .avatar-green { background: #EAF3DE; }

        .equipe-nome {
            font-size: 15px;
            font-weight: 600;
            color: #111;
            margin-bottom: 4px;
        }

        .equipe-cargo {
            font-size: 13px;
            color: #888;
        }
    </style>
</head>
<body>

    <%@ include file="/jsp/layout/header.jsp" %>

    <div class="page">

        <div class="header">
            <h1>Nossa equipe</h1>
            <a href="${pageContext.request.contextPath}/home" class="back-link">← Voltar</a>
        </div>

        <div class="card" style="padding: 2.5rem;">
            <div class="equipe-grid">

                <div class="equipe-card">
                    <div class="equipe-avatar avatar-blue">
                        <svg viewBox="0 0 24 24" fill="none" stroke="#185FA5" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                            <circle cx="12" cy="7" r="4"/>
                        </svg>
                    </div>
                    <div class="equipe-nome">Davi Moraes</div>
                    <div class="equipe-cargo">DEV</div>
                </div>

                <div class="equipe-card">
                    <div class="equipe-avatar avatar-green">
                        <svg viewBox="0 0 24 24" fill="none" stroke="#3B6D11" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                            <circle cx="12" cy="7" r="4"/>
                        </svg>
                    </div>
                    <div class="equipe-nome">Luiz Cavalcanti</div>
                    <div class="equipe-cargo">DEV</div>
                </div>

            </div>
        </div>

    </div>

    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>