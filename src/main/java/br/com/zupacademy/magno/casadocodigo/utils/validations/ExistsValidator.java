package br.com.zupacademy.magno.casadocodigo.utils.validations;

import com.sun.xml.bind.v2.schemagen.episode.Klass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

public class ExistsValidator implements ConstraintValidator<Exists, Object> {

    private String targetAttribute;
    private Class<?> klass;
    private boolean shoudExist;

    @PersistenceContext
    @Autowired
    private EntityManager manager;

    /**
     * Esse método é chamado na instanciacao da classe que vai ser validada.
     * O metodo recebe a anotacao customizada e seta os valores definidos nela
     * @param params o objeto que do tipo da anotacao que contem os campos especificados na annotation
     */
    @Override
    public void initialize(Exists params) { // alteracao do nome do argumento para algo que faz mais sentido
        targetAttribute = params.fieldName();
        klass = params.targetClass();
        shoudExist = params.shouldExist();
    }

    /**
     *
     * @param value o objeto que representa o valor do atributo a ser buscado
     * @param constraintValidatorContext
     * @return True se a regra de valicacao der ok, False em caso contrario
     */

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from "+klass.getName()+ " where "+ targetAttribute+"=:pValue");
        query.setParameter("pValue", value);
        List<?> list = query.getResultList();

        return shoudExist != list.isEmpty();
    }
}
