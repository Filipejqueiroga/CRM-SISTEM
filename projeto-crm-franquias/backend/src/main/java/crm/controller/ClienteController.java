package crm.controller;

import crm.model.Cliente;
import crm.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
        // chama o service para salvar ou atualizar
        Cliente novoCliente = clienteService.salvar(cliente);
        // retorna o novo objeto cliente (agora com id)
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos(){
        List<Cliente> clientes = clienteService.buscarTodos();
        return ResponseEntity.ok(clientes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id){
        return clienteService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/franquia/{idFranquia}")
    public ResponseEntity<List<Cliente>> listarPorFranquia(@PathVariable int idFranquia) {
        List<Cliente> clientes = clienteService.buscarPorFranquia(idFranquia);
        return ResponseEntity.ok(clientes);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Integer id) {
        clienteService.deletar(id);  
        return ResponseEntity.noContent().build(); 
    }
}
