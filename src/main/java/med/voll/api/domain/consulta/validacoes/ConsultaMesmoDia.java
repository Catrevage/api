package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaMesmoDia implements Validacoes {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var horainicial = dados.data().withHour(7);
        var horaFinal = dados.data().withHour(18);


        var consulta = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), horainicial,horaFinal);

        if(consulta){
            throw new ValidacaoException("Já existe uma consulta para este paciente nesta data!");
        }

    }
}
