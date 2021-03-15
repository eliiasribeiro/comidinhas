package br.com.caelum.comidinha.tipoComida;

import org.junit.jupiter.api.*;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class NovoTipoCozinhaValidatorTest {

    private TipoComidaRepository tipoComidaRepository;
    private Errors errors;
    private TipoComidaFormNovo tipoComidaFormNovo;
    private NovoTipoCozinhaValidator novoTipoCozinhaValidator;

    @BeforeEach
    public void setup(){
        this.tipoComidaRepository = mock(TipoComidaRepository.class);
        this.errors = mock(Errors.class);
        this.tipoComidaFormNovo = mock(TipoComidaFormNovo.class);
        this.novoTipoCozinhaValidator = new NovoTipoCozinhaValidator(tipoComidaRepository);
    }


    @Test
    void bla(){
        when(tipoComidaFormNovo.getNome()).thenReturn("Brasileira");
        when(tipoComidaRepository.existsByNome("Brasileira")).thenReturn(true);
        novoTipoCozinhaValidator.validate(tipoComidaFormNovo, errors);
        verify(errors, times(1)).rejectValue("nome", "tipo.comida.novo.exists");
    }

    @Test
    void bla2(){
        when(tipoComidaFormNovo.getNome()).thenReturn("Italiana");
        when(tipoComidaRepository.existsByNome("Italiana")).thenReturn(false);
        novoTipoCozinhaValidator.validate(tipoComidaFormNovo, errors);
        verify(errors, never()).rejectValue("nome", "tipo.comida.novo.exists");
    }
}
