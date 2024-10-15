package br.com.cookingbook.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cookingbook.demo.entities.Preparo;
import br.com.cookingbook.demo.repositories.PreparoRepository;

@Service
public class PreparoService {

    @Autowired
    private PreparoRepository preparoRepository;

    // Criar novo preparo
    public Preparo criarPreparo(Preparo preparo) {
        return preparoRepository.save(preparo);
    }

    // Listar todos os preparos
    public List<Preparo> listarPreparos() {
        return preparoRepository.findAll();
    }

    // Listar preparo por ID
    public Optional<Preparo> listarPreparoPorId(Long id) {
        return preparoRepository.findById(id);
    }

    // Atualizar preparo
    public Preparo atualizarPreparo(Long id, Preparo preparoAtualizado) {
        Optional<Preparo> preparoExistente = preparoRepository.findById(id);

        if (preparoExistente.isPresent()) {
            Preparo preparo = preparoExistente.get();
            preparo.setTipo(preparoAtualizado.getTipo());
            preparo.setDescricao(preparoAtualizado.getDescricao());
            return preparoRepository.save(preparo);
        } else {
            throw new RuntimeException("Preparo não encontrado");
        }
    }

    // Deletar preparo
    public void deletarPreparo(Long id) {
        preparoRepository.deleteById(id);
    }
}
