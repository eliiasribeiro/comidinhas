package br.com.caelum.comidinha.tipoComida;

import org.springframework.validation.*;

class EditarTipoCozinhaValidator implements Validator {

    TipoComidaRepository tipoComidaRepository;

    EditarTipoCozinhaValidator(TipoComidaRepository tipoComidaRepository) {
        this.tipoComidaRepository = tipoComidaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TipoComidaFormEditar.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //ACHO QUE TEM QUE PEGAR O ID TBM PODE O ID PODE SER DE OUTRO TIPO DE COZINHA
        TipoComidaFormEditar tipoComidaFormEditar = (TipoComidaFormEditar) o;
        if (tipoComidaRepository.existsByNome(tipoComidaFormEditar.getNome())){
            errors.rejectValue("nome","tipo.comida.editar.exists");
        }
    }
}
