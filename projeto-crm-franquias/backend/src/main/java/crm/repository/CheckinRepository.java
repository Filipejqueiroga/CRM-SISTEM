package crm.repository;

import crm.model.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Integer> {
   List<Checkin> findByClienteId(Integer clienteId);
   List<Checkin> findByIdFranquia(Integer idFranquia);
}

