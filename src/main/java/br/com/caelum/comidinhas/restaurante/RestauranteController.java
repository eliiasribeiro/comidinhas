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
    private final ItemDoCardapioRepository itemDoCardapioRepository;

    RestauranteController(RestauranteRepository restauranteRepository, DistanciaService distanciaService, ItemDoCardapioRepository itemDoCardapioRepository) {
        this.restauranteRepository = restauranteRepository;
        this.distanciaService = distanciaService;
        this.itemDoCardapioRepository = itemDoCardapioRepository;
    }

    @GetMapping("/restaurantes-proximos/{cep:\\d{5}-\\d{3}}")
    ResponseEntity<List<RestauranteMenuOutput>> buscaRestauranteProximos(@PathVariable("cep") String cep){
        List<Restaurante> todosOsRestaurantes = restauranteRepository.findAll();
        shuffle(todosOsRestaurantes);
        List<RestauranteMenuOutput> restauranteMenuOutputs = todosOsRestaurantes.stream().map(restauranteOutput()).limit(5)
                .collect(Collectors.toList());
        return ok(restauranteMenuOutputs);
    }

    @GetMapping("/restaurante/{slug}")
    ResponseEntity<RestauranteComCardapioDTO> umRestaurante(@PathVariable("slug")String slug){
        Optional<RestauranteCardapioOutput> possivelRestauranteCardapioOutput = restauranteRepository.findBySlug(slug);
        List<ItemDoCardapioMenu> itensDoCardapio = itemDoCardapioRepository.findByCardapio_Restaurante_Slug(slug);
        if(possivelRestauranteCardapioOutput.isPresent()){
            RestauranteComCardapioDTO restauranteComCardapioDTO = new RestauranteComCardapioDTO(possivelRestauranteCardapioOutput.get(), itensDoCardapio);
            return ResponseEntity.ok(restauranteComCardapioDTO);
        }
        return ResponseEntity.notFound().build();
    }


    private Function<Restaurante, RestauranteMenuOutput> restauranteOutput() {
        return restaurante -> new RestauranteMenuOutput(restaurante, distanciaService.calculaDistancia());
    }

}
