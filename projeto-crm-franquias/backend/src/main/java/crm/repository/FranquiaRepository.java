package crm.repository;

import crm.model.Franquia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FranquiaRepository extends JpaRepository<Franquia, Integer> {
   List<Franquia> findByCidade(String cidade);
   List<Franquia> findByStatus(String status);
}

