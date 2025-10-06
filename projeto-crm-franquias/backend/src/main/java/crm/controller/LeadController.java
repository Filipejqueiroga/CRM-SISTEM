package crm.controller;


import crm.model.Lead;
import crm.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leads")
public class LeadController {

   private final LeadService leadService;

   @Autowired
   public LeadController(LeadService leadService) {
       this.leadService = leadService;
   }

   @PostMapping
   public ResponseEntity<Lead> salvarLead(@RequestBody Lead lead) {
       Lead novoLead = leadService.salvar(lead);
       return ResponseEntity.status(HttpStatus.CREATED).body(novoLead);
   }

   @GetMapping
   public ResponseEntity<List<Lead>> listarTodos() {
       return ResponseEntity.ok(leadService.buscarTodos());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Lead> buscarPorId(@PathVariable Integer id) {
       return leadService.buscarPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }

   @GetMapping("/status/{status}")
   public ResponseEntity<List<Lead>> buscarPorStatus(@PathVariable String status) {
       return ResponseEntity.ok(leadService.buscarPorStatus(status));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletarLead(@PathVariable Integer id) {
       leadService.deletar(id);
       return ResponseEntity.noContent().build();
   }
}

