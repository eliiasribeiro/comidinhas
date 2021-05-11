package br.com.caelum.comidinhas.restaurante;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class RestauranteController {

    private final RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping("/restaurantes-proximos/{cep}")
    public ResponseEntity buscaRestauranteProximos(@PathVariable("cep") String cep){
        List<Restaurante> all = restauranteRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
