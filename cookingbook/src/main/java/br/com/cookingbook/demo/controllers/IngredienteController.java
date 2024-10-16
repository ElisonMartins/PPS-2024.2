package br.com.cookingbook.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.cookingbook.demo.entities.Ingrediente;
import br.com.cookingbook.demo.services.IngredienteService;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {


    @Autowired
    private IngredienteService ingredienteService;

    // Criar ingrediente
    @PostMapping
    public ResponseEntity<Ingrediente> criarIngrediente(@RequestBody Ingrediente ingrediente) {
        // Verifica se o objeto Receita dentro de Ingrediente tem o ID correto
        if (ingrediente.getReceita() == null || ingrediente.getReceita().getReceita_id() == null) {
            return ResponseEntity.badRequest().build();
        }
        Ingrediente newIngrediente = ingredienteService.criarIngrediente(ingrediente);
        return ResponseEntity.ok(newIngrediente);
    }

    // Listar todos os ingredientes
    @GetMapping
    public ResponseEntity<List<Ingrediente>> listarIngredientes() {
        List<Ingrediente> ingredientes = ingredienteService.listarIngredientes();
        return ResponseEntity.ok(ingredientes);
    }

    // Listar ingrediente por ID
    @GetMapping("/{ingrediente_id}")
    public ResponseEntity<Ingrediente> listarIngredientePorId(@PathVariable Long ingrediente_id) {
        Optional<Ingrediente> ingrediente = ingredienteService.listarIngredientePorId(ingrediente_id);
        return ingrediente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar ingrediente
    @PutMapping("/{ingrediente_id}")
    public ResponseEntity<Ingrediente> atualizarIngrediente(@PathVariable Long ingrediente_id, @RequestBody Ingrediente ingredienteAtt) {
        try {
            Ingrediente ingrediente = ingredienteService.atualizarIngrediente(ingrediente_id, ingredienteAtt);
            return ResponseEntity.ok(ingrediente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar ingrediente
    @DeleteMapping("/{ingrediente_id}")
    public ResponseEntity<Void> deletarIngrediente(@PathVariable Long ingrediente_id) {
        ingredienteService.deletarIngrediente(ingrediente_id);
        return ResponseEntity.noContent().build();
    }
}