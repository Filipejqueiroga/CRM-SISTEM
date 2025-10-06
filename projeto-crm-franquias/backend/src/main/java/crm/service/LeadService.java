package crm.service;

import crm.model.Lead;
import crm.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <-- Importação adicionada

import java.util.List;
import java.util.Optional;

@Service
public class LeadService {

    private final LeadRepository leadRepository;

    @Autowired
    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @Transactional
    public Lead salvar(Lead lead) {
        return leadRepository.save(lead);
    }

    public List<Lead> buscarTodos() {
        return leadRepository.findAll();
    }

    public Optional<Lead> buscarPorId(Integer id) {
        return leadRepository.findById(id);
    }

    @Transactional
    public void deletar(Integer id) {
        leadRepository.deleteById(id);
    }

    public List<Lead> buscarPorStatus(String status) {
        return leadRepository.findByStatus(status);
    }
}

