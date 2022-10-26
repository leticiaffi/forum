package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.servico.CategoriaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoriaControlador {

    @Autowired
    private CategoriaServico categoriaServico;

    @GetMapping
    public List<Categoria> listarTodas() {
        return categoriaServico.listarTodas();
    }
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria>> buscarPorId( @PathVariable Long codigo){
        Optional<Categoria> categoria = categoriaServico.buscarPorCodigo(codigo);
                return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = (categoriaServico.salvar(categoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }
    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @RequestBody Categoria categoria) {
        return ResponseEntity.ok(CategoriaServico.atualizar(codigo, categoria));
    }


}
