package med.voll.api.domain.clientes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {

	Page<Clientes> findAllByAtivoTrue(Pageable paginacao);

}
