package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamento(
        @NotNull
        Long id,
        @NotNull
        Cancelamento motivo) {
    public DadosCancelamento(DadosCancelamento dados){
        this(dados.id, dados.motivo());
    }
}
