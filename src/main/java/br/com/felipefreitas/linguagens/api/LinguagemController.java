package br.com.felipefreitas.linguagens.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/linguagens")
public class LinguagemController {

	@Autowired
	public LinguagemRepository repository;
	
	@GetMapping
	public List<Linguagem> obterLinguagens(){
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Void> cadastrarLinguagem(@RequestBody Linguagem linguagem) {
		Linguagem linguagemSalva = repository.save(linguagem);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(linguagemSalva.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	

	@PutMapping
	public ResponseEntity<Void> alterarLinguagem(@RequestBody Linguagem linguagem) {
		repository.save(linguagem);
		return ResponseEntity.noContent().build();
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirLinguagem(@PathVariable("id") String id) {
		repository.deleteById(id);
		return  ResponseEntity.noContent().build();
	}
	
}
