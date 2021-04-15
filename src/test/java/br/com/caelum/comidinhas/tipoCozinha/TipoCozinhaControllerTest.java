package br.com.caelum.comidinhas.tipoCozinha;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static br.com.caelum.comidinhas.utils.SerializadorJson.paraJson;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TipoCozinhaController.class)
class TipoCozinhaControllerTest {

    @MockBean
    private TipoCozinhaRepository tipoCozinhaRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deve_retornar_uma_lista_com_tipos_de_cozinha()throws Exception {
        TipoCozinha tipoCozinha1 = new TipoCozinha(1L,"Brasileira");
        TipoCozinha tipoCozinha2 = new TipoCozinha(2L,"Portuguesa");
        TipoCozinha tipoCozinha3 = new TipoCozinha(3L,"Indiana");
        TipoCozinha tipoCozinha4 = new TipoCozinha(4L,"Italiana");
        List<TipoCozinha> tiposCozinha = List.of(tipoCozinha1, tipoCozinha2, tipoCozinha3, tipoCozinha4);
        doReturn(tiposCozinha).when(tipoCozinhaRepository).findAllByOrderByNome();

        mockMvc.perform(get("/admin/tipos-de-cozinha"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is("Brasileira")))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void deve_retornar_uma_lista_vazia_quando_nao_tem_tipos_de_cozinha() throws Exception {
        doReturn(Collections.EMPTY_LIST).when(tipoCozinhaRepository).findAllByOrderByNome();

        mockMvc.perform(get("/admin/tipos-de-cozinha"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(Collections.EMPTY_LIST)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void deve_salvar_um_tipos_de_cozinha_novo()throws Exception {
        TipoCozinhaInputNovo portuguesa = new TipoCozinhaInputNovo("Portuguesa");
        String json = paraJson(portuguesa);
        when(tipoCozinhaRepository.save(any(TipoCozinha.class))).thenReturn(new TipoCozinha(1L,"Portuguesa"));
        mockMvc.perform(post("/admin/tipos-de-cozinha")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location","/admin/tipos-de-cozinha/1"));
        verify(tipoCozinhaRepository).save(any(TipoCozinha.class));
    }

    @Test
    void deve_retornar_uma_bad_request_quando_ja_existe_o_nome() throws Exception {
        when(tipoCozinhaRepository.existsByNome("Italiana")).thenReturn(true);
        TipoCozinhaInputNovo tipoCozinhaInputNovo = new TipoCozinhaInputNovo("Italiana");
        String json = paraJson(tipoCozinhaInputNovo);
        mockMvc.perform(post("/admin/tipos-de-cozinha")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
        verify(tipoCozinhaRepository,never()).save(any(TipoCozinha.class));
    }

    @Test
    void deve_editar_o_nome_do_tipo_de_cozinha() throws Exception{
        TipoCozinha chinesa = new TipoCozinha(1L,"Chinesa");
        when(tipoCozinhaRepository.findById(1L)).thenReturn(Optional.of(chinesa));
        TipoCozinhaInputEditar tipoCozinhaInputEditar = new TipoCozinhaInputEditar(1L,"Chinesa");
        String json = paraJson(tipoCozinhaInputEditar);
        mockMvc.perform(put("/admin/tipos-de-cozinha/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        verify(tipoCozinhaRepository).save(any(TipoCozinha.class));
    }


    @Test
    void deve_editar_retornar_um_erro_quando_o_nome_ja_existe() throws Exception{
        TipoCozinha italiana = new TipoCozinha(1L,"Italiana");
        when(tipoCozinhaRepository.save(any(TipoCozinha.class))).thenReturn(italiana);
        TipoCozinhaInputEditar tipoCozinhaInputEditar = new TipoCozinhaInputEditar(1L,"Chinesa");
        String json = paraJson(tipoCozinhaInputEditar);
        mockMvc.perform(put("/admin/tipos-de-cozinha/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.globalErrorMessages", Matchers.hasItem("O tipo de cozinha Chinesa informado nao existe")));
        verify(tipoCozinhaRepository,never()).save(any(TipoCozinha.class));
    }

    @Test
    void deve_remover_um_tipo_de_cozinha() throws Exception{
        TipoCozinha italiana = new TipoCozinha(1L, "Italiana");
        when(tipoCozinhaRepository.findById(italiana.getId())).thenReturn(Optional.of(italiana));
        mockMvc.perform(delete("/admin/tipos-de-cozinha/{id}","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(tipoCozinhaRepository).deleteById(1L);
    }

}
