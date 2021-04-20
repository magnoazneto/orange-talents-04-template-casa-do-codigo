package br.com.zupacademy.magno.casadocodigo.utils.validations;

import org.springframework.util.Assert;

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
     * Esse método é chamado na instanciacao da classe que vai ser validada.
     * O metodo recebe a anotacao customizada e seta os valores definidos nela
     * @param params o objeto que do tipo da anotacao que contem os campos especificaso na annotation
     */
    @Override
    public void initialize(Unique params) { // alteracao do nome do argumento para algo que faz mais sentido
        targetAttribute = params.fieldName();
        klass = params.targetClass();
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
        // programacao defensiva
        Assert.state(list.size() <=1, "Foi encontrado mais de um "+klass+" com o atributo "+targetAttribute+" = "+value);

        return list.isEmpty();
    }
}
