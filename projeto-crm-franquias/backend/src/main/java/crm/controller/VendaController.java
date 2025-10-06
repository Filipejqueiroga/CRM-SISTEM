package crm.controller;

import crm.model.Venda;
import crm.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    @Autowired
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    // Rota: POST http://localhost:8080/vendas
    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) {
        Venda novaVenda = vendaService.salvar(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
    }

    // Rota: GET http://localhost:8080/vendas
    @GetMapping
    public ResponseEntity<List<Venda>> listarTodas() {
        List<Venda> vendas = vendaService.buscarTodos();
        return ResponseEntity.ok(vendas);
    }

    // Rota: GET http://localhost:8080/vendas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Integer id) {
        return vendaService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Rota: GET http://localhost:8080/vendas/cliente/{idCliente}
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Venda>> listarPorCliente(@PathVariable Integer idCliente) {
        List<Venda> vendas = vendaService.buscarPorCliente(idCliente);
        
        if (vendas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vendas);
    }

    // Rota: DELETE http://localhost:8080/vendas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Integer id) {
        vendaService.deletar(id);
        return ResponseEntity.noContent().build(); 
    }
}
