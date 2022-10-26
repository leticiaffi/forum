package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.repositorio.CategoriaRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServico {

    @Autowired
    private static CategoriaRepositorio categoriaRepositorio;

    public List<Categoria> listarTodas() {
        return categoriaRepositorio.findAll();
    }
    public static Optional<Categoria> buscarPorCodigo(Long codigo) {
        return categoriaRepositorio.findById(codigo);
    }
    public Categoria salvar (Categoria categoria){
        return categoriaRepositorio.save(categoria);
    }
    public static Categoria atualizar(Long codigo, Categoria categoria) {
        Categoria categoriaSalvar = validarCategoriaExiste(codigo);
        BeanUtils.copyProperties(categoria, categoriaSalvar, "codigo");
        return categoriaRepositorio.save(categoriaSalvar);
    }


    private static Categoria validarCategoriaExiste(Long codigo) {
    Optional<Categoria> categoria = buscarPorCodigo(codigo);
    if(categoria.isEmpty()){
        throw new EmptyResultDataAccessException(1);
    }
        return categoria.get();
    }
}
