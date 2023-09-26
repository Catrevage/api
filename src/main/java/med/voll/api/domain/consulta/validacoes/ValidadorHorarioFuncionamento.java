package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamento implements Validacoes {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
      
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var sabado = dataConsulta.getDayOfWeek().equals(DayOfWeek.SATURDAY);

        var antesHora = dataConsulta.getHour() < 7;
        var depoisHora = dataConsulta.getHour() >  18;

        if (domingo || antesHora || depoisHora || sabado) {
            throw  new ValidacaoException("Agendamento fora do horário de funcionamento.");
        }

    }
}
