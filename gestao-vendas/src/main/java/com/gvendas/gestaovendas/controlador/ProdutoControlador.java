package com.gvendas.gestaovendas.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gvendas.gestaovendas.dto.produto.ProdutoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gvendas.gestaovendas.entidades.Produto;
import com.gvendas.gestaovendas.servico.ProdutoServico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria{codigoCategoria}/produto")
public class ProdutoControlador {

	@Autowired
	private ProdutoServico produtoServico;

	@ApiOperation(value = "Listar", nickname = "listarTodos")
	@GetMapping
	public List<ProdutoResponseDTO> listarTodas(@PathVariable Long codigoCategoria) {
		return produtoServico.listarTodos(codigoCategoria).stream().map(produto -> ProdutoResponseDTO.converterParaProdutoDTO(produto)).collect(Collectors.toList());
	}

	@ApiOperation(value = "Listar por código", nickname = "buscarPorCodigo")
	@GetMapping("/{codigo}")
	public ResponseEntity<ProdutoResponseDTO> buscarPorCodigo(@PathVariable Long codigoCategoria,
															 @PathVariable Long codigo) {
		Optional<Produto> produto = produtoServico.buscarPorCodigo(codigo, codigoCategoria);
		return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produto.get())) : ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salvar", nickname = "salvarProduto")
	@PostMapping
	public ResponseEntity<Produto> salvar(@PathVariable Long codigoCategoria, @Valid @RequestBody ProdutoResponseDTO produto) {
		Produto produtoSalvo = produtoServico.salvar(codigoCategoria, produto.converterParaEntidade(codigoCategoria));
		return ResponseEntity.status(HttpStatus.CREATED).(ProdutoResponseDTO.converterParaProdutoDTO(produtoSalvo));
	}

	@ApiOperation(value = "Atualizar", nickname = "atualizarProduto")
	@PutMapping("/{codigoProduto}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto,
											 @Valid @RequestBody Produto produto) {
		return ResponseEntity.ok(produtoServico.atualizar(codigoCategoria, codigoProduto, produto));
	}
		@ApiOperation(value = "Deletar", nickname = "deletarProduto")
		@DeleteMapping("/{codigoProduto}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deletar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
			produtoServico.deletar(codigoCategoria, codigoProduto);

	}

}
