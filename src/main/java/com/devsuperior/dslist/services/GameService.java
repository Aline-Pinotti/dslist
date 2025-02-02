package com.devsuperior.dslist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll() {
        List<GameMinDTO> result = gameRepository.findAll().stream().map(x -> new GameMinDTO(x)).toList();
        return result;
    }

    // Boa prática colocar em todo método do service para que fique transacional.
    // Para que garanta que
    // a operação com o banco de dados vai acontecer obecenco aos princípios das
    // transações (ACID - Atomicidade, Consistência, Isolamento e Durabilidade)
    // Consistente, Isolado e Durável)
    // read0nly = true: assegura que não vou fazer nenhuma operação de escrita - não
    // bloqueia o banco de dados pra escrita
    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        // Repository já tem um método chamado FindBy ID. Esse método retorna um
        // Optional, então é necessário chamar o get() para pegar o objeto dentro
        Game result = gameRepository.findById(id).get(); // TODO: tratamento para validar se o ID existe
        return new GameDTO(result);
    }

}
