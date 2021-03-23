package br.com.caelum.comidinhas.tipoComida;

import org.junit.jupiter.api.*;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class EditarTipoCozinhaValidatorTest {

    private TipoCozinhaRepository tipoCozinhaRepository;
    private Errors errors;
    private TipoCozinhaInputEditar tipoCozinhaInputEditar;
    private EditarTipoCozinhaValidator editarTipoCozinhaValidator;

    @BeforeEach
    public void setup(){
        this.tipoCozinhaRepository = mock(TipoCozinhaRepository.class);
        this.errors = mock(Errors.class);
        this.tipoCozinhaInputEditar = mock(TipoCozinhaInputEditar.class);
        this.editarTipoCozinhaValidator = new EditarTipoCozinhaValidator(tipoCozinhaRepository);
    }


    @Test
    void deve_retornar_verdadeiro_quando_o_nome_existe_e_mostrar_msg_de_erro(){
        when(tipoCozinhaInputEditar.getNome()).thenReturn("Brasileira");
        when(tipoCozinhaRepository.existsByNome("Brasileira")).thenReturn(true);
        editarTipoCozinhaValidator.validate(tipoCozinhaInputEditar, errors);
        verify(errors, times(1)).rejectValue("nome", "tipo.comida.editar.exists");
    }

    @Test
    void deve_retornar_falso_quando_o_nome_nao_existe_e_nao_mostrar_msg_de_erro(){
        when(tipoCozinhaInputEditar.getNome()).thenReturn("Italiana");
        when(tipoCozinhaRepository.existsByNome("Italiana")).thenReturn(false);
        editarTipoCozinhaValidator.validate(tipoCozinhaInputEditar, errors);
        verify(errors, never()).rejectValue("nome", "tipo.comida.editar.exists");
    }

}
