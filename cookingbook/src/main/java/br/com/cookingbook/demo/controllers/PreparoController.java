package br.com.cookingbook.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.cookingbook.demo.entities.Preparo;
import br.com.cookingbook.demo.services.PreparoService;

@RestController
@RequestMapping("/preparos")
public class PreparoController {

    @Autowired
    private PreparoService preparoService;

    // Criar preparo
    @PostMapping
    public ResponseEntity<Preparo> criarPreparo(@RequestBody Preparo preparo) {
        Preparo newPreparo = preparoService.criarPreparo(preparo);
        return ResponseEntity.ok(newPreparo);
    }

    // Listar todos os preparos
    @GetMapping
    public ResponseEntity<List<Preparo>> listarPreparos() {
        List<Preparo> preparos = preparoService.listarPreparos();
        return ResponseEntity.ok(preparos);
    }

    // Listar preparo por ID
    @GetMapping("/{preparo_id}")
    public ResponseEntity<Preparo> listarPreparoPorId(@PathVariable Long preparo_id) {
        Optional<Preparo> preparo = preparoService.listarPreparoPorId(preparo_id);
        return preparo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar preparo
    @PutMapping("/{preparo_id}")
    public ResponseEntity<Preparo> atualizarPreparo(@PathVariable Long preparo_id, @RequestBody Preparo preparoAtt) {
        try {
            Preparo preparo = preparoService.atualizarPreparo(preparo_id, preparoAtt);
            return ResponseEntity.ok(preparo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar preparo
    @DeleteMapping("/{preparo_id}")
    public ResponseEntity<Void> deletarPreparo(@PathVariable Long preparo_id) {
        preparoService.deletarPreparo(preparo_id);
        return ResponseEntity.noContent().build();
    }
}
