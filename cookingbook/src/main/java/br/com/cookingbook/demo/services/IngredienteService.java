package br.com.cookingbook.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cookingbook.demo.entities.Ingrediente;
import br.com.cookingbook.demo.factories.IngredienteFactory;
import br.com.cookingbook.demo.repositories.IngredienteRepository;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private IngredienteFactory ingredienteFactory;

    // Criar novo ingrediente usando a fábrica
    public Ingrediente criarIngrediente(Ingrediente ingrediente) {
        // Utiliza a fábrica para criar o ingrediente
        Ingrediente novoIngrediente = ingredienteFactory.criarIngrediente(
                ingrediente.getNome(),
                ingrediente.getQuantidade(),
                ingrediente.getReceita().getReceita_id()
        );
        return ingredienteRepository.save(novoIngrediente);
    }

    // Listar todos os ingredientes
    public List<Ingrediente> listarIngredientes() {
        return ingredienteRepository.findAll();
    }

    // Listar ingrediente por ID
    public Optional<Ingrediente> listarIngredientePorId(Long id) {
        return ingredienteRepository.findById(id);
    }

    // Atualizar ingrediente
    public Ingrediente atualizarIngrediente(Long id, Ingrediente ingredienteAtualizado) {
        Optional<Ingrediente> ingredienteExistente = ingredienteRepository.findById(id);

        if (ingredienteExistente.isPresent()) {
            Ingrediente ingrediente = ingredienteExistente.get();
            ingrediente.setNome(ingredienteAtualizado.getNome());
            ingrediente.setQuantidade(ingredienteAtualizado.getQuantidade());
            // Se necessário, atualizar a receita associada
            if (ingredienteAtualizado.getReceita() != null) {
                ingrediente.setReceita(ingredienteAtualizado.getReceita());
            }
            return ingredienteRepository.save(ingrediente);
        } else {
            throw new RuntimeException("Ingrediente não encontrado");
        }
    }

    // Deletar ingrediente
    public void deletarIngrediente(Long id) {
        ingredienteRepository.deleteById(id);
    }
}