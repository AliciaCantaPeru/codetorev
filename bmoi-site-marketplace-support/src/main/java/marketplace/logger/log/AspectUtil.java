package marketplace.logger.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

public class AspectUtil {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final static String NULL = "null";
    private final static String SALTO_LINEA = "\n";
    private final static String PARAM_TYPE = "* Param Type:";
    private final static String PARAM_NAME = "* Param Name:";
    private final static String PARAM_VALUE = "* Param Value:";
    private final static String DOS_PUNTOS = ":";
    private final static String TAB = "    ";

    public static String getObjectAsString(Object obj) {
        return obj == null ? NULL : obj.toString();
    }

    public static String getParameterTypes(String uuid, ProceedingJoinPoint proceedingJoinPoint) {
        StringBuilder sb = new StringBuilder();
        final Signature signature = proceedingJoinPoint.getStaticPart().getSignature();
        if (signature instanceof MethodSignature) {
            final MethodSignature ms = (MethodSignature) signature;
            final Class<?>[] parameterTypes = ms.getParameterTypes();
            final String[] parameterNames = ms.getParameterNames();
            final Object[] parameterValues = proceedingJoinPoint.getArgs();

            if (parameterTypes != null && parameterNames != null && parameterValues != null) {
                int paramIndex = 1;
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> type = parameterTypes[i];
                    String name = parameterNames[i];
                    Object value = parameterValues[i];
                    sb.append(SALTO_LINEA).append(TAB).append(TAB).append(uuid).append(PARAM_TYPE).append(paramIndex).append(DOS_PUNTOS).append(type);
                    sb.append(SALTO_LINEA).append(TAB).append(TAB).append(uuid).append(PARAM_NAME).append(paramIndex).append(DOS_PUNTOS).append(name);
                    sb.append(SALTO_LINEA).append(TAB).append(TAB).append(uuid).append(PARAM_VALUE).append(paramIndex).append(DOS_PUNTOS).append(getObjectAsString(value));

                    paramIndex++;
                }
            }

        }

        return sb.toString();
    }

}
