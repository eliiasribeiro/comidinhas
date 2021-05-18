package br.com.caelum.comidinhas.restaurante;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.shuffle;
import static org.springframework.http.ResponseEntity.*;

@RestController
class RestauranteController {

    private final RestauranteRepository restauranteRepository;
    private final DistanciaService distanciaService;

    RestauranteController(RestauranteRepository restauranteRepository, DistanciaService distanciaService) {
        this.restauranteRepository = restauranteRepository;
        this.distanciaService = distanciaService;
    }

    @GetMapping("/restaurantes-proximos/{cep:\\d{5}-\\d{3}}")
    ResponseEntity<List<RestauranteOutput>> buscaRestauranteProximos(@PathVariable("cep") String cep){
        List<Restaurante> todosOsRestaurantes = restauranteRepository.findAll();
        shuffle(todosOsRestaurantes);
        List<RestauranteOutput> restauranteOutputs = todosOsRestaurantes.stream().map(restauranteOutput(cep)).limit(5)
                .collect(Collectors.toList());
        return ok(restauranteOutputs);
    }

    private Function<Restaurante, RestauranteOutput> restauranteOutput(String cep) {
        return restaurante -> new RestauranteOutput(restaurante, distanciaService.calculaDistancia(restaurante, cep));
    }
}
