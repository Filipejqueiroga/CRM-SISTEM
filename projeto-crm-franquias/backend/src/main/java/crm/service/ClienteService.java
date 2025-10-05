package crm.service;

import crm.model.Cliente;
import crm.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    //substitui adicionar_clientes e atualizar_clientes em DAO
    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    //substitui listar_clientes
    public List<Cliente> buscarTodos(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Integer id){
        return clienteRepository.findById(id);
    }

    //substitui excluir_clientes
    public void deletar(Integer id) {
        clienteRepository.deleteById(id);
    }

    //substitui listar_clientes_franquia
    public List<Cliente> buscarPorFranquia(int idFranquia) {
        return clienteRepository.findByIdFranquia(idFranquia);
    }
}
