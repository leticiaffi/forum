package com.gvendas.gestaovendas.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gvendas.gestaovendas.entidades.Produto;
import com.gvendas.gestaovendas.servico.ProdutoServico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria{codigoCategoria}/produto")
public class ProdutoControlador {

	@Autowired
	private ProdutoServico produtoServico;

	@ApiOperation(value = "Listar", nickname = "listarTodos")
	@GetMapping
	public List<Produto> listarTodas(@PathVariable Long codigoCategoria) {
		return produtoServico.listarTodos(codigoCategoria);
	}

	@ApiOperation(value = "Listar por código", nickname = "buscarPorCodigo")
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Produto>> buscarPorCodigo(@PathVariable Long codigoCategoria,
			@PathVariable Long codigo) {
		Optional<Produto> produto = produtoServico.buscarPorCodigo(codigo, codigoCategoria);
		return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
	}
	@ApiOperation(value = "Salvar", nickname = "salvarProduto")
	@PostMapping
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
		Produto produtoSalvo = produtoServico.salvar(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}

}
