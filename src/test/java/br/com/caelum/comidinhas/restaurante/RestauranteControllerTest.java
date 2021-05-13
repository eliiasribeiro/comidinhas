package br.com.caelum.comidinhas.restaurante;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RestauranteController.class)
class RestauranteControllerTest {
    @MockBean
    private RestauranteRepository restauranteRepository;

    @Autowired
    private MockMvc mockMvc;
}
