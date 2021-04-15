package br.com.caelum.comidinhas.tipoCozinha;

import org.springframework.validation.*;

class NovoTipoCozinhaValidator implements Validator {

    private final TipoCozinhaRepository tipoCozinhaRepository;

    NovoTipoCozinhaValidator(TipoCozinhaRepository tipoCozinhaRepository) {
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TipoCozinhaInputNovo.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TipoCozinhaInputNovo tipoCozinhaInputNovo = (TipoCozinhaInputNovo) o;
        if (tipoCozinhaRepository.existsByNome(tipoCozinhaInputNovo.getNome())){
            errors.rejectValue("nome","tipo.cozinha.novo.exists");
        }
    }
}
