package br.com.caelum.comidinhas.tipoCozinha;

import org.springframework.http.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static java.lang.String.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
class TipoCozinhaController {

    final TipoCozinhaRepository tipoCozinhaRepository;

    TipoCozinhaController(TipoCozinhaRepository tipoCozinhaRepository) {
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    @InitBinder("tipoCozinhaInputNovo")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new NovoTipoCozinhaValidator(tipoCozinhaRepository));
    }

    @InitBinder("tipoCozinhaInputEditar")
    void initBinderEditaCozinha(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new EditarTipoCozinhaValidator(tipoCozinhaRepository));
    }

    @GetMapping("/admin/tipos-de-cozinha")
    ResponseEntity<List<TipoCozinha>> todosOsTiposDeCozinha(){
        //TODO VERIFICAR SE O CARA E ADMIN
        List<TipoCozinha> tipoCozinhas = tipoCozinhaRepository.findAllByOrderByNome();
        return ok(tipoCozinhas);
    }

    @PostMapping("/admin/tipos-de-cozinha")
    ResponseEntity<?> criarUmNovoTipoDeCozinha(@RequestBody @Valid TipoCozinhaInputNovo tipoCozinhaInputNovo){
        //TODO VERIFICAR SE O CARA E ADMIN
        TipoCozinha tipoCozinha = tipoCozinhaRepository.save(tipoCozinhaInputNovo.toModel());
        URI location = URI.create(format("/admin/tipos-de-cozinha/%s",tipoCozinha.getId()));
        return created(location).build();
    }

    @PutMapping("/admin/tipos-de-cozinha/{id}")
    ResponseEntity<?> editaUmNovoTipoDeCozinha(@PathVariable("id") Long id, @Valid @RequestBody TipoCozinhaInputEditar tipoCozinhaInputEditar){
        //TODO VERIFICAR SE O CARA E ADMIN
        if(!id.equals(tipoCozinhaInputEditar.getId())){
            return badRequest().body("ID da URI deve ser igual ao do payload");
        }
        tipoCozinhaRepository.save(tipoCozinhaInputEditar.toModel(tipoCozinhaRepository::findById));
        return ok().build();
    }

    @DeleteMapping("/admin/tipos-de-cozinha/{id}")
    ResponseEntity<?> deletaUmTipoDeCozinha(@PathVariable("id") Long id){
        //TODO VERIFICAR SE O CARA E ADMIN
        //VERIFICA SE ALGUEM TA USANDO PRA EXCLUIR
        TipoCozinha tipoCozinha = tipoCozinhaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        tipoCozinhaRepository.deleteById(tipoCozinha.getId());
        return ok().build();
    }
}
