package br.com.cookingbook.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cookingbook.demo.entities.Receita;
import br.com.cookingbook.demo.repositories.ReceitaRepository;
import br.com.cookingbook.demo.strategies.ReceitaListarStrategy;

@Service
public class ReceitaService {
     @Autowired
    private ReceitaRepository receitaRepository;

    private ReceitaListarStrategy strategy;

    public void setListarStrategy(ReceitaListarStrategy strategy) {
        this.strategy = strategy;
    }

    // Criar uma nova receita
    public Receita criarReceita(Receita receita) {
        return receitaRepository.save(receita);
    }

    // Listar todas as receitas
    public List<Receita> listarReceitas() {
        return receitaRepository.findAll();
    }

    //Listar receitas por métodos específicos com strategy
    public List<Receita> listarEmOrdem() {
        List<Receita> receitas = listarReceitas();
        return strategy != null ? strategy.Listar(receitas) : receitas;
    }

    // Listar receita por ID
    public Optional<Receita> listarReceitaPorId(Long id) {
        return receitaRepository.findById(id);
    }

    // Atualizar uma receita existente
    public Receita atualizarReceita(Long id, Receita receitaAtualizada) {
        Optional<Receita> receitaExistente = receitaRepository.findById(id);
        
        if (receitaExistente.isPresent()) {
            Receita receita = receitaExistente.get();
            receita.setTitulo(receitaAtualizada.getTitulo());
            receita.setDescricao(receitaAtualizada.getDescricao());
            receita.setIngredientes(receitaAtualizada.getIngredientes());
            receita.setPreparo(receitaAtualizada.getPreparo());
            receita.setTempo(receitaAtualizada.getTempo());
            receita.setPorcoes(receitaAtualizada.getPorcoes());
            return receitaRepository.save(receita);
        } else {
            throw new RuntimeException("Receita não encontrada");
        }
    }

    // Deletar uma receita por ID
    public void deletarReceita(Long id) {
        receitaRepository.deleteById(id);
    }
}
