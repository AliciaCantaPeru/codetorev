/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.pdf;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionStyleExpression;
import java.util.Map;

/**
 *
 * @author Martin Pilar <mpilarcastillejo@gmail.com>
 */
public class BackgroundConditionImpar extends ConditionStyleExpression implements CustomExpression {

    private String fieldName;
    private Integer numeroFila = 1;
    private String datoAnterior;

    public BackgroundConditionImpar(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object evaluate(Map fields, Map variables, Map parameters) {
        boolean condition = false;
        Object currentValue = fields.get(fieldName);
        if (currentValue instanceof String) {
            String datoActual = (String) currentValue;
            if (datoAnterior != null && !datoActual.equals(datoAnterior)) {
                numeroFila++;
            }
            datoAnterior = datoActual;
            condition = numeroFila % 2 == 1;
        }
        return condition;
    }

    public String getClassName() {
        return Boolean.class.getName();
    }
}
