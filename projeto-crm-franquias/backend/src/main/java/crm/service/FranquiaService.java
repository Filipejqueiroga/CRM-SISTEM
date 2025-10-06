package crm.service;

import crm.model.Franquia;
import crm.repository.FranquiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FranquiaService {

    private final FranquiaRepository franquiaRepository;

    @Autowired
    public FranquiaService(FranquiaRepository franquiaRepository) {
        this.franquiaRepository = franquiaRepository;
    }

    @Transactional
    public Franquia salvar(Franquia franquia) {
        return franquiaRepository.save(franquia);
    }
    
    @Transactional(readOnly = true)
    public List<Franquia> buscarTodas() {
        return franquiaRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Franquia> buscarPorId(Integer id) {
        return franquiaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Franquia> buscarPorCidade(String cidade) {
        return franquiaRepository.findByCidade(cidade);
    }

    @Transactional(readOnly = true)
    public List<Franquia> buscarPorStatus(String status) {
        return franquiaRepository.findByStatus(status);
    }

    @Transactional
    public void deletar(Integer id) {
        franquiaRepository.deleteById(id);
    }
}
