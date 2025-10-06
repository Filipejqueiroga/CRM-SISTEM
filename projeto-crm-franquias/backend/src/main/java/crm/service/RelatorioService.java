package crm.service;

import crm.model.Checkin;
import crm.model.Cliente;
import crm.model.Franquia;
import crm.model.Lead;
import crm.model.Venda;
import crm.repository.CheckinRepository;
import crm.repository.ClienteRepository;
import crm.repository.FranquiaRepository;
import crm.repository.LeadRepository;
import crm.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RelatorioService {

    private final ClienteRepository clienteRepository;
    private final VendaRepository vendaRepository;
    private final LeadRepository leadRepository;
    private final FranquiaRepository franquiaRepository;
    private final CheckinRepository checkinRepository;

    @Autowired
    public RelatorioService(
            ClienteRepository clienteRepository,
            VendaRepository vendaRepository,
            LeadRepository leadRepository,
            FranquiaRepository franquiaRepository,
            CheckinRepository checkinRepository) {
        this.clienteRepository = clienteRepository;
        this.vendaRepository = vendaRepository;
        this.leadRepository = leadRepository;
        this.franquiaRepository = franquiaRepository;
        this.checkinRepository = checkinRepository;
    }

    public Map<String, Object> relatorioGeral(String dataVenda) {
        List<Lead> leads = leadRepository.findAll();
        long leadsTotal = leadRepository.count();
        
        List<Franquia> franquias = franquiaRepository.findAll();
        long franquiasTotal = franquiaRepository.count();

        double faturamentoTotal = 0;
        long clientesTotal = 0;

        Map<String, Object> resultado = new HashMap<>();
        Map<String, Object> detalhesFranquias = new HashMap<>();

        for (Franquia f : franquias) {
            Integer idFranquia = f.getId();
            
            List<Cliente> clientesFranquia = clienteRepository.findByIdFranquia(idFranquia);
            long clientesTotalFranquia = clientesFranquia.size();

            List<Venda> vendasFranquia = vendaRepository.buscarVendasPorFranquiaEData(idFranquia, dataVenda); 
            
            double faturamentoFranquia = vendasFranquia.stream()
                .mapToDouble(Venda::getValor)
                .sum();

            faturamentoTotal += faturamentoFranquia;
            clientesTotal += clientesTotalFranquia;
            
            // armazena dados de cada franquia
            Map<String, Object> dadosFranquia = new HashMap<>();
            dadosFranquia.put("clientes", clientesTotalFranquia);
            dadosFranquia.put("faturamento", faturamentoFranquia);
            detalhesFranquias.put(f.getNome() + " (" + f.getCidade() + ")", dadosFranquia);
        }

        // resumo Geral
        resultado.put("detalhes_franquias", detalhesFranquias);
        resultado.put("total_franquias", franquiasTotal);
        resultado.put("total_clientes", clientesTotal);
        resultado.put("total_leads", leadsTotal);
        resultado.put("faturamento_total", faturamentoTotal);
        resultado.put("data_referencia", dataVenda);

        return resultado;
    }

    public Map<String, Object> relatorioFranquia(Integer idFranquia, String dataVenda) {
        
        Optional<Franquia> franquiaOpt = franquiaRepository.findById(idFranquia);
        if (franquiaOpt.isEmpty()) {
            return null;
        }
        Franquia franquia = franquiaOpt.get();
        
        List<Cliente> clientesFranquia = clienteRepository.findByIdFranquia(idFranquia);
        List<Checkin> checkinsFranquia = checkinRepository.findByIdFranquia(idFranquia);
        
        List<Venda> vendasFranquia = vendaRepository.buscarVendasPorFranquiaEData(idFranquia, dataVenda);

        double faturamentoFranquia = vendasFranquia.stream()
            .mapToDouble(Venda::getValor)
            .sum();

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("nome_franquia", franquia.getNome());
        resultado.put("cidade", franquia.getCidade());
        resultado.put("total_clientes", clientesFranquia.size());
        resultado.put("total_checkins", checkinsFranquia.size());
        resultado.put("faturamento", faturamentoFranquia);
        resultado.put("data_referencia", dataVenda);

        return resultado;
    }
}
