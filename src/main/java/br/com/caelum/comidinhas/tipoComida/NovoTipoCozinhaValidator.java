package br.com.caelum.comidinhas.tipoComida;

import org.springframework.validation.*;

class NovoTipoCozinhaValidator implements Validator {

    TipoCozinhaRepository tipoCozinhaRepository;

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
            errors.rejectValue("nome","tipo.comida.novo.exists");
        }
    }
}
