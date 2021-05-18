package br.com.caelum.comidinhas.restaurante;


import br.com.caelum.comidinhas.tipoCozinha.TipoCozinha;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestauranteController.class)
class RestauranteControllerTest {

    @MockBean
    private RestauranteRepository restauranteRepository;

    @MockBean
    private DistanciaService distanciaService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void deve_retornar_uma_lista_com_restaurantes() throws Exception {
        TipoCozinha arabe = new TipoCozinha(1L, "arabe");

        Restaurante barDoElias = new Restaurante(1L,"Bar do elias","35.509.407/0001-23",
                "Rua paulista","03221000","piriripopopo", arabe);

        Restaurante kebabDoAquiles = new Restaurante(2L,"Kebab do Aquiles","11.418.316/0001-14",
                "Rua vergueiro","71234000","mange du kebab", arabe);

        when(distanciaService.calculaDistancia(barDoElias, "08255-000")).thenReturn(10);
        when(distanciaService.calculaDistancia(kebabDoAquiles, "08255-000")).thenReturn(6);

        List<Restaurante> restaurantes = Arrays.asList(barDoElias, kebabDoAquiles);
        doReturn(restaurantes).when(restauranteRepository).findAll();

        mockMvc.perform(get("/restaurantes-proximos/08255-000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[*].nome", containsInAnyOrder("Bar do elias", "Kebab do Aquiles")))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].descricao", containsInAnyOrder("piriripopopo", "mange du kebab")))
                .andExpect(jsonPath("$[*].distancia", containsInAnyOrder(10, 6)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void deve_retornar_uma_lista_vazia_quando_nao_tem_restaurantes() throws Exception {
        doReturn(Collections.EMPTY_LIST).when(restauranteRepository).findAll();
        mockMvc.perform(get("/restaurantes-proximos/08255-000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(Collections.EMPTY_LIST)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // formato URL inválido => 404
    // distancia service retornando null

}
