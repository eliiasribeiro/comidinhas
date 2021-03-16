package br.com.caelum.comidinha.tipoComida;

import org.junit.jupiter.api.*;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class EditarTipoCozinhaValidatorTest {

    private TipoComidaRepository tipoComidaRepository;
    private Errors errors;
    private TipoComidaFormEditar tipoComidaFormEditar;
    private EditarTipoCozinhaValidator editarTipoCozinhaValidator;

    @BeforeEach
    public void setup(){
        this.tipoComidaRepository = mock(TipoComidaRepository.class);
        this.errors = mock(Errors.class);
        this.tipoComidaFormEditar = mock(TipoComidaFormEditar.class);
        this.editarTipoCozinhaValidator = new EditarTipoCozinhaValidator(tipoComidaRepository);
    }


    @Test
    void bla(){
        when(tipoComidaFormEditar.getNome()).thenReturn("Brasileira");
        when(tipoComidaRepository.existsByNome("Brasileira")).thenReturn(true);
        editarTipoCozinhaValidator.validate(tipoComidaFormEditar, errors);
        verify(errors, times(1)).rejectValue("nome", "tipo.comida.editar.exists");
    }

    @Test
    void bla2(){
        when(tipoComidaFormEditar.getNome()).thenReturn("Italiana");
        when(tipoComidaRepository.existsByNome("Italiana")).thenReturn(false);
        editarTipoCozinhaValidator.validate(tipoComidaFormEditar, errors);
        verify(errors, never()).rejectValue("nome", "tipo.comida.editar.exists");
    }

}
