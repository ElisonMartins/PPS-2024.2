package br.com.cookingbook.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cookingbook.demo.entities.Receita;
import br.com.cookingbook.demo.services.ReceitaService;
import br.com.cookingbook.demo.strategies.imp.ListarPorPorcoes;
import br.com.cookingbook.demo.strategies.imp.ListarPorTempo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    // CRUD: criar receita
    @PostMapping("path")
    public ResponseEntity<Receita> criarReceita(@RequestBody Receita receita) {
        // Utilizando o padrão Builder para criar uma nova receita
        Receita novaReceita = new Receita.Builder()
                .titulo(receita.getTitulo())
                .descricao(receita.getDescricao())
                .ingredientes(receita.getIngredientes())
                .preparo(receita.getPreparo())
                .tempo(receita.getTempo())
                .porcoes(receita.getPorcoes())
                .build();

        Receita newReceita = receitaService.criarReceita(novaReceita);
        return ResponseEntity.ok(newReceita);
    }

    // CRUD: listar receitas(todas)
    @GetMapping
    public ResponseEntity<List<Receita>> listarReceitas() {
        List<Receita> receitas = receitaService.listarReceitas();
        return ResponseEntity.ok(receitas);
    }

    // listar específicas: tempo
    @GetMapping("/tempo")
    public ResponseEntity<List<Receita>> listarPorTempo() {
        receitaService.setListarStrategy(new ListarPorTempo());
        return ResponseEntity.ok(receitaService.listarEmOrdem());
    }

    // listar específicas: porção
    @GetMapping("/porcao")
    public ResponseEntity<List<Receita>> listarPorPorcoes() {
        receitaService.setListarStrategy(new ListarPorPorcoes());
        return ResponseEntity.ok(receitaService.listarEmOrdem());
    }

    // listar receita por ID
    @GetMapping("/{receita_id}")
    public ResponseEntity<Receita> listarReceitaPorId(@PathVariable Long receita_id) {
        Optional<Receita> receita = receitaService.listarReceitaPorId(receita_id);
        if (receita.isPresent()) {
            return ResponseEntity.ok(receita.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // CRUD: atualizar receita
    @PutMapping("/{receita_id}")
    public ResponseEntity<Receita> atualizarReceita(@PathVariable Long receita_id, @RequestBody Receita receitaAtt) {
        try {
            Receita receita = receitaService.atualizarReceita(receita_id, receitaAtt);
            return ResponseEntity.ok(receita);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // CRUD: deletar receita
    @DeleteMapping("/{receita_id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable Long receita_id) {
        receitaService.deletarReceita(receita_id);
        return ResponseEntity.noContent().build();
    }
}
