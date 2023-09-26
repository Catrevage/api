package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.clientes.Clientes;
import med.voll.api.domain.clientes.ClientesRepository;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Clientes paciente;

    private LocalDateTime data;

    private boolean ativo = true;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private Cancelamento motivoCancelamento;

    public Consulta(Object o, Medico medico, Clientes paciente, LocalDateTime data) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
    }


    public void inativar(){
        this.ativo = false;
    }


    public void cancelarConsulta(DadosCancelamento dados) {
        this.ativo = false;
        this.motivoCancelamento = dados.motivo();
    }
}

