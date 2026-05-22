// Função para confirmar exclusão/cancelamento
function confirmarCancelamento(event) {
    if (!confirm('Tem certeza que deseja cancelar esta reserva?')) {
        event.preventDefault();
    }
}

// Função para validar formulário de nova reserva
function validarFormularioReserva() {
    const data = document.getElementById('data').value;
    const horaInicio = document.getElementById('horaInicio').value;
    const horaFim = document.getElementById('horaFim').value;
    
    if (!data || !horaInicio || !horaFim) {
        alert('Preencha todos os campos obrigatórios.');
        return false;
    }
    
    if (horaInicio >= horaFim) {
        alert('A hora de início deve ser anterior à hora de fim.');
        return false;
    }
    
    return true;
}

// Associar eventos após o carregamento da página
document.addEventListener('DOMContentLoaded', function() {
    // Adicionar confirmação aos links de cancelamento
    const linksCancelar = document.querySelectorAll('.cancelar-reserva');
    linksCancelar.forEach(link => {
        link.addEventListener('click', confirmarCancelamento);
    });
    
    // Adicionar validação ao formulário de reserva, se existir
    const formReserva = document.getElementById('formNovaReserva');
    if (formReserva) {
        formReserva.addEventListener('submit', function(event) {
            if (!validarFormularioReserva()) {
                event.preventDefault();
            }
        });
    }
});