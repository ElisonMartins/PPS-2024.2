package br.com.cookingbook.demo.services;


import org.springframework.beans.factory.annotation.Autowired;

import br.com.cookingbook.demo.entities.Receita;
import br.com.cookingbook.demo.repositories.ReceitaRepository;

public class ReceitaService {
     @Autowired
    private ReceitaRepository receitaRepository;

}
