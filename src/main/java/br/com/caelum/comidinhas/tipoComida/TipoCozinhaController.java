package br.com.caelum.comidinhas.tipoComida;

import org.springframework.http.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
class TipoCozinhaController {

    final TipoCozinhaRepository tipoCozinhaRepository;

    TipoCozinhaController(TipoCozinhaRepository tipoCozinhaRepository) {
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    @InitBinder("tipoComidaFormNovo")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new NovoTipoCozinhaValidator(tipoCozinhaRepository));
    }

    @InitBinder("tipoComidaInputEditar")
    void initBinderEditaCozinha(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new EditarTipoCozinhaValidator(tipoCozinhaRepository));
    }

    @GetMapping("/admin/tipo-de-comidas")
    ResponseEntity<List<TipoCozinha>> todosOsTiposDeComida(){
        //TODO VERIFICAR SE O CARA E ADMIN
        List<TipoCozinha> tipoCozinhas = tipoCozinhaRepository.findAllByOrderByNome();
        return ok(tipoCozinhas);
    }

    @PostMapping("/admin/tipo-de-comida/novo")
    ResponseEntity<?> criarUmNovoTipoDeComida(@RequestBody @Valid TipoCozinhaInputNovo tipoCozinhaInputNovo){
        //TODO VERIFICAR SE O CARA E ADMIN
        tipoCozinhaRepository.save(tipoCozinhaInputNovo.toModel());
        URI location = URI.create("/admin/tipo-de-comidas");
        return created(location).build();
    }

    @PutMapping("/admin/tipo-de-comida/editar")
    ResponseEntity<?> editaUmNovoTipoDeComida(@Valid @RequestBody TipoCozinhaInputEditar tipoCozinhaInputEditar){
        //TODO VERIFICAR SE O CARA E ADMIN
        tipoCozinhaRepository.save(tipoCozinhaInputEditar.toModel(tipoCozinhaRepository::findById));
        URI location = URI.create("/admin/tipo-de-comidas");
        return ok(location);
    }

    @DeleteMapping("/admin/tipo-de-comida/remover/{id}")
    ResponseEntity<?> deletaUmTipoDeCozinha(@PathVariable("id") Long id){
        //TODO VERIFICAR SE O CARA E ADMIN
        //VERIFICA SE ALGUEM TA USANDO PRA EXCLUIR
        TipoCozinha tipoCozinha = tipoCozinhaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        tipoCozinhaRepository.deleteById(tipoCozinha.getId());
        URI location = URI.create("/admin/tipo-de-comidas");
        return ok(location);
    }
}
