package crm.service;

import crm.model.Venda;
import crm.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Transactional
    public Venda salvar(Venda venda) {
        return vendaRepository.save(venda);
    }

    public List<Venda> buscarTodos() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarPorId(Integer id) {
        return vendaRepository.findById(id);
    }

    @Transactional
    public void deletar(Integer id) {
        vendaRepository.deleteById(id);
    }

    public List<Venda> buscarPorCliente(Integer idCliente) {
        return vendaRepository.findByIdCliente(idCliente);
    }
}

