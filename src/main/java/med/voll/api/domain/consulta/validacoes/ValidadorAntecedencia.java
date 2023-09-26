package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAntecedencia implements Validacoes {

    public void validar(DadosAgendamentoConsulta dados){
        var diferanca = Duration.between(LocalDateTime.now(), dados.data()).toMinutes();
        if( diferanca < 30){
            throw  new ValidacaoException("As consultas so podem ser agendadas com mais de 30min de antecedência");
        }

    }
}
