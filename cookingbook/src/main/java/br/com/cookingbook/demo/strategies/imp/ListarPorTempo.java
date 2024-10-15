package br.com.cookingbook.demo.strategies.imp;

import br.com.cookingbook.demo.entities.Receita;
import br.com.cookingbook.demo.strategies.ReceitaListarStrategy;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ListarPorTempo implements ReceitaListarStrategy{
    @Override
    public List<Receita> Listar(List<Receita> receitas) {
        return receitas.stream().sorted(Comparator.comparing(Receita::getTempo)).collect(Collectors.toList());
    }
    
}
