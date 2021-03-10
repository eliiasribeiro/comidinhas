package br.com.caelum.comidinha.tipoComida;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
class TipoComidaController {

    final TipoComidaRepository tipoComidaRepository;

    TipoComidaController(TipoComidaRepository tipoComidaRepository) {
        this.tipoComidaRepository = tipoComidaRepository;
    }

    @GetMapping("/admin/tipo-de-comidas")
    ResponseEntity<List<TipoComida>> todosOsTiposDeComida(){
        //TODO VERIFICAR SE O CARA E ADMIN
        List<TipoComida> tipoComidas = tipoComidaRepository.findAllByOrderByNome();
        return ok(tipoComidas);
    }

    @PostMapping("/admin/tipo-de-comida/novo")
    ResponseEntity<?> criarUmNovoTipoDeComida(@RequestBody @Valid TipoComidaForm tipoComidaForm){
        //TODO VERIFICAR SE O CARA E ADMIN
        if(tipoComidaRepository.findByNome(tipoComidaForm.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tipo de comida ja existe");
        }

        tipoComidaRepository.save(tipoComidaForm.toModel());
        URI location = URI.create("/admin/tipo-de-comidas");
        return created(location).build();
    }

    @PutMapping("/admin/tipo-de-comida/editar/{id}")
    ResponseEntity<?> criarUmNovoTipoDeComida(@PathVariable("id") Long id, @Valid @RequestBody TipoComidaForm tipoComidaForm){
        //TODO VERIFICAR SE O CARA E ADMIN
        TipoComida tipoComida = tipoComidaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        tipoComidaRepository.save(tipoComida.convertFormToEntity(id, tipoComidaForm));
        URI location = URI.create("/admin/tipo-de-comidas");
        return created(location).build();
    }

    @DeleteMapping("/admin/tipo-de-comida/remover/{id}")
    ResponseEntity<?> deletaUmTipoDeCozinha(@PathVariable("id") Long id){
        //TODO VERIFICAR SE O CARA E ADMIN
        //VERIFICA SE ALGUEM TA USANDO PRA EXCLUIR
        TipoComida tipoComida = tipoComidaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        tipoComidaRepository.deleteById(tipoComida.getId());
        URI location = URI.create("/admin/tipo-de-comidas");
        return ok(location);
    }
}
