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

import static br.com.caelum.comidinha.tipoComida.TipoComidaBuilder.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void deve_retornar_uma_lista_com_4_comidas()throws Exception {
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
    void deve_retornar_uma_lista_vazia() throws Exception {
        doReturn(Collections.EMPTY_LIST).when(tipoComidaRepository).findAllByOrderByNome();

        mockMvc.perform(get("/admin/tipo-de-comidas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(Collections.EMPTY_LIST)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /admin/tipo-de-comida/novo")
    void deve_salvar_um_tipo_de_comida_novo()throws Exception {
        String formJson = umTipoComida().comNome("Portuguesa").comoJson();
        mockMvc.perform(post("/admin/tipo-de-comida/novo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /admin/tipo-de-comida/novo")
    void deve_retornar_a_mensagem_de_erro_quando_ja_existe_o_nome() throws Exception {
        tipoComidaRepository.save(umTipoComida().comNome("Italiana").cria());
        String formJson = umTipoComida().comNome("Italiana").comoJson();
        mockMvc.perform(post("/admin/tipo-de-comida/novo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /admin/tipo-de-comida/editar")
    void deve_editar_o_nome_do_tipo_de_comida() throws Exception{
        tipoComidaRepository.save(umTipoComida().comId(1L).comNome("Italiana").cria());
        String formJson = umTipoComida().comId(1L).comNome("Italiana").comoJson();
        mockMvc.perform(put("/admin/tipo-de-comida/editar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("PUT /admin/tipo-de-comida/editar")
    void deve_editar_retornar_um_erro_quando_o_nome_ja_existe() throws Exception{
        tipoComidaRepository.save(umTipoComida().comNome("Italiana").cria());
        String formJson = umTipoComida().comNome("Italiana").comoJson();
        mockMvc.perform(put("/admin/tipo-de-comida/editar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE /admin/tipo-de-comida/remover")
    void deve_remover_um_tipo_de_comida() throws Exception{
        tipoComidaRepository.save(umTipoComida().comId(1L).comNome("Italiana").cria());
        mockMvc.perform(delete("/admin/tipo-de-comida/remover/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
