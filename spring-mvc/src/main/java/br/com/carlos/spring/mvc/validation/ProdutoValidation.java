package br.com.carlos.spring.mvc.validation;

import br.com.carlos.spring.mvc.models.Produto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Classe de validação de produtos cadastrados. Implementa Validator, classe
 * destinada a validações, vinda do Spring, responsável pela validação
 * automática sempre que se faz necessária.
 *
 * @author Carlos H
 */
public class ProdutoValidation implements Validator {

    /**
     * Método destinado ao reconhecimento e suporte de validações de classes
     * vindas como parâmetro.
     * 
     * Ex.: clazz é passível de validação?
     *      é a mesma classe que produto?
     *      é parent de produto?
     *
     * @param clazz: classe a ser validada.
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        // Verifica se possível validar classe Produto.
        return Produto.class.isAssignableFrom(clazz);
    }

    /**
     * Método para validação de produtos cadastrados
     *
     * @param target : objeto a ser cadastrado
     * @param errors : Erros de prencimento de formulário (Gerenciado pelo
     * Spring)
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");

        Produto produto = (Produto) target;

        if (produto.getPaginas() <= 0) {
            errors.rejectValue("paginas", "field.required");
        }

    }

}
