package br.com.caelum.comidinhas.tipoCozinha;

import org.junit.jupiter.api.*;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class NovoTipoCozinhaValidatorTest {

    private TipoCozinhaRepository tipoCozinhaRepository;
    private Errors errors;
    private TipoCozinhaInputNovo tipoCozinhaInputNovo;
    private NovoTipoCozinhaValidator novoTipoCozinhaValidator;

    @BeforeEach
    public void setup(){
        this.tipoCozinhaRepository = mock(TipoCozinhaRepository.class);
        this.errors = mock(Errors.class);
        this.tipoCozinhaInputNovo = mock(TipoCozinhaInputNovo.class);
        this.novoTipoCozinhaValidator = new NovoTipoCozinhaValidator(tipoCozinhaRepository);
    }


    @Test
    void deve_retornar_verdadeiro_quando_o_nome_existe_e_mostrar_msg_de_erro(){
        when(tipoCozinhaInputNovo.getNome()).thenReturn("Brasileira");
        when(tipoCozinhaRepository.existsByNome("Brasileira")).thenReturn(true);
        novoTipoCozinhaValidator.validate(tipoCozinhaInputNovo, errors);
        verify(errors).rejectValue("nome", "tipo.cozinha.novo.exists");
    }

    @Test
    void deve_retornar_falso_quando_o_nome_nao_existe_e_nao_mostrar_msg_de_erro(){
        when(tipoCozinhaInputNovo.getNome()).thenReturn("Italiana");
        when(tipoCozinhaRepository.existsByNome("Italiana")).thenReturn(false);
        novoTipoCozinhaValidator.validate(tipoCozinhaInputNovo, errors);
        verify(errors, never()).rejectValue("nome", "tipo.cozinha.novo.exists");
    }
}
