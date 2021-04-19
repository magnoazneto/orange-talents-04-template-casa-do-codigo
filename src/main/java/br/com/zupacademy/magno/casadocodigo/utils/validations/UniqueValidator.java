package br.com.zupacademy.magno.casadocodigo.utils.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private String targetAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    /**
     * Na inicializacao o metodo recebe a anotacao customizada e seta
     * os valores definidos nela
     * @param unique o objeto que do tipo da anotacao que vai conter o field e a classe alvo
     */
    @Override
    public void initialize(Unique unique) {
        targetAttribute = unique.fieldName();
        klass = unique.targetClass();
    }

    /**
     *
     * @param value o objeto que representa o valor do atributo a ser buscado
     * @param constraintValidatorContext
     * @return True se a regra de valicacao der ok, False em caso contrario
     */

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select k from "+klass.getName()+ " k where "+ targetAttribute+"=:pValue");
        query.setParameter("pValue", value);

        List<?> list = query.getResultList();
        return list.size() == 0;
    }
}
