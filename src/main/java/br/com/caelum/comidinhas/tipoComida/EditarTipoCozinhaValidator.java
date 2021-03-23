package br.com.caelum.comidinhas.tipoComida;

import org.springframework.validation.*;

class EditarTipoCozinhaValidator implements Validator {

    TipoCozinhaRepository tipoCozinhaRepository;

    EditarTipoCozinhaValidator(TipoCozinhaRepository tipoCozinhaRepository) {
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TipoCozinhaInputEditar.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //ACHO QUE TEM QUE PEGAR O ID TBM PODE O ID PODE SER DE OUTRO TIPO DE COZINHA
        TipoCozinhaInputEditar tipoCozinhaInputEditar = (TipoCozinhaInputEditar) o;
        if (tipoCozinhaRepository.existsByNome(tipoCozinhaInputEditar.getNome())){
            errors.rejectValue("nome","tipo.comida.editar.exists");
        }
    }
}
