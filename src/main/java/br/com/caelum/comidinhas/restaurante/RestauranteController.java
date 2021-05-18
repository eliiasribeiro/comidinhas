package br.com.caelum.comidinhas.restaurante;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.shuffle;
import static org.springframework.http.ResponseEntity.*;

@RestController
class RestauranteController {

    private final RestauranteRepository restauranteRepository;

    RestauranteController(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping("/restaurantes-proximos/{cep:\\d{5}-\\d{3}}")
    ResponseEntity<List<RestauranteOutput>> buscaRestauranteProximos(@PathVariable("cep") String cep){
        List<Restaurante> todosOsRestaurantes = restauranteRepository.findAll();
        //Manda isso pra entidade ao inves de mandar pro controller?
        shuffle(todosOsRestaurantes);
        List<RestauranteOutput> restauranteOutputs = todosOsRestaurantes.stream().map(RestauranteOutput::new).limit(5).collect(Collectors.toList());
        return ok(restauranteOutputs);
    }
}
