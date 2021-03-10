package br.com.caelum.comidinha.tipoComida;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TipoComidaControllerTest {

    @MockBean
    private TipoComidaRepository tipoComidaRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /admin/tipo-de-comidas")
    void should_return_a_list_with_4_comidas() throws Exception {
        TipoComida tipoComida1 = new TipoComida(1L,"Brasileira");
        TipoComida tipoComida2 = new TipoComida(2L,"Portuguesa");
        TipoComida tipoComida3 = new TipoComida(3L,"Indiana");
        TipoComida tipoComida4 = new TipoComida(4L,"Italiana");
        List<TipoComida> tiposDeComida = List.of(tipoComida1,tipoComida2,tipoComida3,tipoComida4);
        doReturn(tiposDeComida).when(tipoComidaRepository).findAllByOrderByNome();

        mockMvc.perform(get("/admin/tipo-de-comidas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]nome", is("Brasileira")))
                .andExpect(jsonPath("$.[0]id", is(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /admin/tipo-de-comidas")
    void should_return_a_list_empty() throws Exception {
        doReturn(Collections.EMPTY_LIST).when(tipoComidaRepository).findAllByOrderByNome();

        mockMvc.perform(get("/admin/tipo-de-comidas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(Collections.EMPTY_LIST)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}