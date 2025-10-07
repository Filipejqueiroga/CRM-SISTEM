package crm.controller;

import crm.model.Checkin;
import crm.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkins")
public class CheckinController {

    private final CheckinService checkinService;

    @Autowired
    public CheckinController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }

    @PostMapping
    public ResponseEntity<Checkin> criarCheckin(@RequestBody Checkin checkin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(checkinService.salvar(checkin));
    }

    @GetMapping
    public ResponseEntity<List<Checkin>> obterTodosCheckins() {
        return ResponseEntity.ok(checkinService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checkin> obterCheckinPorId(@PathVariable Integer id) {
        return checkinService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Checkin>> obterCheckinsPorCliente(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(checkinService.buscarPorCliente(clienteId));
    }

    @GetMapping("/franquia/{idFranquia}")
    public ResponseEntity<List<Checkin>> obterCheckinsPorFranquia(@PathVariable Integer idFranquia) {
        return ResponseEntity.ok(checkinService.buscarPorFranquia(idFranquia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Checkin> atualizarCheckin(@PathVariable Integer id, @RequestBody Checkin checkin) {
        checkin.setId(id);
        Checkin atualizado = checkinService.salvar(checkin);

        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCheckin(@PathVariable Integer id) {
        checkinService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
