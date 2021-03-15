package br.com.caelum.comidinha.tipoComida;

import org.springframework.validation.*;

public class NovoTipoCozinhaValidator implements Validator {

    private TipoComidaRepository tipoComidaRepository;

    public NovoTipoCozinhaValidator(TipoComidaRepository tipoComidaRepository) {
        this.tipoComidaRepository = tipoComidaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TipoComidaFormNovo.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TipoComidaFormNovo tipoComidaFormNovo = (TipoComidaFormNovo) o;
        if (tipoComidaRepository.existsByNome(tipoComidaFormNovo.getNome())){
            errors.rejectValue("nome","bla","bla");
        }
    }
}
