package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Service
public class CategoriaServico {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public List<Categoria> listarTodas() {
        return categoriaRepositorio.findAll();
    }
    public Optional<Categoria> buscarPorCodigo(Long codigo) {
        return categoriaRepositorio.findById(codigo);
    }
}
