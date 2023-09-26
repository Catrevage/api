package med.voll.api.domain.consulta;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndDataBetween(Long aLong, LocalDateTime horainicial, LocalDateTime horaFinal);

    boolean existsByMedicoIdAndData(Long aLong, LocalDateTime data);
}