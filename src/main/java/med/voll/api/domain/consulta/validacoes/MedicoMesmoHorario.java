package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoMesmoHorario implements Validacoes {
    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var consultaMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (consultaMesmoHorario){
            throw new ValidacaoException("Já existe uma consulta para este médico neste horário");
        }

    }
}
