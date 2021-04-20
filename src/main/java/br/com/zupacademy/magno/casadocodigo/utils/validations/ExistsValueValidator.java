package br.com.zupacademy.magno.casadocodigo.utils.validations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

// CI total: 1 pt
public class ExistsValueValidator implements ConstraintValidator<ExistsValue, Object> {

    private String targetAttribute;
    private Class<?> klass;
    private boolean shoudExist;

    @PersistenceContext
    private EntityManager manager;

    /**
     * Esse método é chamado na instanciacao da classe que vai ser validada.
     * O metodo recebe a anotacao customizada e seta os valores definidos nela
     * @param params o objeto que do tipo da anotacao que contem os campos especificados na annotation
     */
    @Override
    public void initialize(ExistsValue params) { // alteracao do nome do argumento para algo que faz mais sentido
        targetAttribute = params.fieldName();
        klass = params.targetClass();
    }

    /**
     *
     * @param value o objeto que representa o valor do atributo a ser buscado
     * @param constraintValidatorContext
     * @return True se a regra de valicacao der ok, False em caso contrario
     */

    @Override // CI: 1
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 1
        Query query = manager.createQuery("select 1 from "+klass.getName()+ " where "+ targetAttribute+"=:pValue");
        query.setParameter("pValue", value);
        List<?> list = query.getResultList();

        return !list.isEmpty();


    }
}
