package med.voll.api.domain.consulta;

public enum Cancelamento {
    PACIENTE_DESISTIU {
        String getName() {
            return "O Paciente Desistiu";
        }
    },
    MEDICO_CANCELOU {
        String getName() {
            return "O Médico Cancelou";
        }
    },
    OUTROS {
        String getName() {
            return "Outros";
        }
    };
}
