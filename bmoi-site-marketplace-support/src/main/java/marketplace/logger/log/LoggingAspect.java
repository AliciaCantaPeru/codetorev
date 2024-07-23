package marketplace.logger.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Log4j2
@Aspect
@Component("loggingAspect")
public class LoggingAspect {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final static String CONTROLLER = "Controller";
    private final static String SEPARACION = " ================================= METODO ====================================";
    private final static String SEPARACION2 = "------------------------------------------------------------------------";

    private final static String SEPARACION_CONTROLLER = " **************************************** METODO ********************************************";
    private final static String PARENTISIS_INICIO = "(";
    private final static String PARENTISIS_FIN = ")";
    private final static String SALTO_LINEA = "\n";
    private final static String INICIO_METODO = " **INICIO PARA:";
    private final static String FIN_METODO = " **FIN PARA:";
    private final static String METODO = "METODO";
    private final static String DURACION = " DURACION: ";

    private final static Map<String, Duracion> map = new TreeMap();

    private static void setInicio(String uuid, long inicio) {
        Duracion duracion = map.get(uuid);
        if (duracion == null) {
            duracion = new Duracion();
            duracion.setUuid(uuid);
            duracion.setInicio(inicio);
            map.put(uuid, duracion);
        } else {
            duracion.setInicio(inicio);
        }
    }

    private static void setFin(String uuid, long fin) {
        Duracion duracion = map.get(uuid);
        if (duracion == null) {
            duracion = new Duracion();
            duracion.setUuid(uuid);
            duracion.setFin(fin);
            map.put(uuid, duracion);
        } else {
            duracion.setFin(fin);
        }
    }

    private static long obtenerDuracion(String uuid) {
        Duracion duracion = map.get(uuid);
        if (duracion == null) {
            return 0;
        }
        return duracion.getDuracion();
    }

    @Pointcut("bean(*Controller)")
    private void controllerBean() {
    }

    @Pointcut("bean(*ServiceImpl)")
    private void serviceBean() {
    }

    @Pointcut("bean(*RepositoryImpl)")
    private void repositoryBean() {
    }

    @Pointcut("execution(public void *())")
    private void publicMethodActionListener() {
    }

    @Pointcut("execution(public * *(..))")
    private void publicMethod() {
    }

    @Pointcut("execution(private * *(..))")
    private void privateMethod() {
    }

    @Around("(controllerBean() || serviceBean() || repositoryBean())&& (publicMethodActionListener()|| publicMethod() || privateMethod())")
    public Object logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String uuid = "";

//        System.out.println("proceedingJoinPoint.getKind():" + proceedingJoinPoint.getKind());
//        System.out.println("proceedingJoinPoint.getTarget():" + proceedingJoinPoint.getTarget());
        String method = proceedingJoinPoint.getSignature().toShortString();
        StopWatch stopWatch = new StopWatch(method);

        if (method.contains(CONTROLLER)) {
            StringBuilder sb = new StringBuilder();
            uuid = UUID.randomUUID().toString();
            setInicio(uuid, System.currentTimeMillis());
            log.info(uuid + SEPARACION_CONTROLLER.replace(METODO, method));

            sb.append(uuid).append(INICIO_METODO).append(method).append(PARENTISIS_INICIO).append(AspectUtil.getParameterTypes(uuid, proceedingJoinPoint)).append(SALTO_LINEA).append(PARENTISIS_FIN);
            log.info(sb.toString());

        }
        try {
            stopWatch.start(method);
            Object obj = proceedingJoinPoint.proceed();

            if (method.contains(CONTROLLER)) {
                StringBuilder sb = new StringBuilder();
                sb.append(uuid).append(FIN_METODO).append(method);
                log.info(sb.toString());

                setFin(uuid, System.currentTimeMillis());
                log.info(uuid + SEPARACION_CONTROLLER.replace(METODO, method));
                log.info(uuid + DURACION + method + obtenerDuracion(uuid));
            }
            return obj;
        } catch (Throwable throwable) {
//            log.error(getStackTrace(throwable));
            throw throwable;
        } finally {
            stopWatch.stop();
            log.debug(stopWatch.shortSummary());
        }
    }

    @AfterThrowing(pointcut = "(controllerBean())", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error(getStackTrace(e));
    }

    private String getStackTrace(Throwable e) {
        String NL = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append("\n ")
                .append("       [Exception] ")
                .append(e.getMessage())
                .append("\n ");
        builder.append("       [CAUSE]     ")
                .append(e.getCause())
                .append(NL);
        StackTraceElement[] elements = e.getStackTrace();
        for (StackTraceElement s : elements) {
            builder.append("\tat ")
                    .append(s.getClassName())
                    .append(".")
                    .append(s.getMethodName())
                    .append("(")
                    .append(s.getFileName())
                    .append(":")
                    .append(s.getLineNumber())
                    .append(")")
                    .append(NL);
        }
        return builder.toString();
    }

    @Data
    private static class Duracion implements Comparable<Duracion> {

        private String uuid;
        private long inicio;
        private long fin;

        public long getDuracion() {
            return this.fin - this.inicio;
        }

        @Override
        public int compareTo(Duracion o) {
            return uuid.compareTo(o.uuid);
        }

    }

}
