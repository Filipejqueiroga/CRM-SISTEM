package crm.controller;

import crm.model.Franquia;
import crm.service.FranquiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franquias")
public class FranquiaController {

    private final FranquiaService franquiaService;

    @Autowired
    public FranquiaController(FranquiaService franquiaService) {
        this.franquiaService = franquiaService;
    }

    @PostMapping
    public ResponseEntity<Franquia> criarFranquia(@RequestBody Franquia franquia) {
        Franquia nova = franquiaService.salvar(franquia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @GetMapping
    public ResponseEntity<List<Franquia>> listarFranquias() {
        return ResponseEntity.ok(franquiaService.buscarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Franquia> buscarPorId(@PathVariable Integer id) {
        return franquiaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<Franquia>> buscarPorCidade(@PathVariable String cidade) {
        return ResponseEntity.ok(franquiaService.buscarPorCidade(cidade));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Franquia>> buscarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(franquiaService.buscarPorStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Franquia> atualizarFranquia(@PathVariable Integer id, @RequestBody Franquia franquia) {
        franquia.setId(id);
        Franquia atualizada = franquiaService.salvar(franquia); 
        
        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFranquia(@PathVariable Integer id) {
        franquiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
