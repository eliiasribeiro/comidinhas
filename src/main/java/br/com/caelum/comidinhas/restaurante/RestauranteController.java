package br.com.caelum.comidinhas.restaurante;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.shuffle;

@RestController
class RestauranteController {

    private final RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    //PERGUNTAR SE EU DEVERIA VALIDAR O CEP QUE VEIO PELA URL
    @GetMapping("/restaurantes-proximos/{cep}")
    public ResponseEntity buscaRestauranteProximos(@PathVariable("cep") String cep){
        List<Restaurante> todosOsRestaurantes = restauranteRepository.findAll();
        shuffle(todosOsRestaurantes);
        List<RestauranteOutput> restauranteOutputs = todosOsRestaurantes.stream().map(RestauranteOutput::new).limit(5).collect(Collectors.toList());
        return ResponseEntity.ok(restauranteOutputs);
    }
}
