package crm.repository;

import crm.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
   List<Venda> findByIdCliente(Integer idCliente);

   @Query("SELECT v FROM Venda v WHERE v.idFranquia = :idFranquia AND v.data = :dataVenda")
   List<Venda> buscarVendasPorFranquiaEData(@Param("idFranquia") Integer idFranquia, @Param("dataVenda") String dataVenda);
}

