package br.com.caelum.comidinhas.restaurante;


import br.com.caelum.comidinhas.tipoCozinha.TipoCozinha;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestauranteController.class)
class RestauranteControllerTest {
    @MockBean
    private RestauranteRepository restauranteRepository;

    @Autowired
    private MockMvc mockMvc;


    //PEDIR AJUDAR PRA ENTENDER COMO FAZ ISSO DE MANEIRA CORRETA
    @Test
    void deve_retornar_uma_lista_com_restaurantes() throws Exception {
        Restaurante restaurante1 = new Restaurante(1L,"Bar do elias","35.509.407/0001-23","Rua paulista","03221000","piriripopopo",new TipoCozinha(1L,"arabe"));
        List<Restaurante> restaurantes = List.of(restaurante1);
        doReturn(restaurantes).when(restauranteRepository).findAll();
        mockMvc.perform(get("/restaurantes-proximos/08255000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is("Bar do elias")))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void deve_retornar_uma_lista_vazia_quando_nao_tem_restaurantes() throws Exception {
        doReturn(Collections.EMPTY_LIST).when(restauranteRepository).findAll();
        mockMvc.perform(get("/restaurantes-proximos/08255000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(Collections.EMPTY_LIST)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
