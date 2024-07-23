/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;
import java.lang.reflect.Type;

/**
 *
 * @author mpilar
 */
public class JsonConversor {

    public static final Gson CONVER_TO_JSON = new Gson();

    public static <T> T fromObjectToObject(Object dato, Class<T> tipoSaliente) throws JsonSyntaxException {
        String datoCastear = CONVER_TO_JSON.toJson(dato);
        Object datoHrtVer = CONVER_TO_JSON.fromJson(datoCastear, (Type) tipoSaliente);
        return Primitives.wrap(tipoSaliente).cast(datoHrtVer);
    }

    public static <T> T fromArrayToArray(Object dato, Type tipoSaliente) throws JsonSyntaxException {
        String datoCastear = CONVER_TO_JSON.toJson(dato);
        T object = CONVER_TO_JSON.fromJson(datoCastear, tipoSaliente);
        return object;
    }

    public static <T> T fromStringToObject(String dato, Class<T> tipoSaliente) throws JsonSyntaxException {
        Object datoHrtVer = CONVER_TO_JSON.fromJson(dato, (Type) tipoSaliente);
        return Primitives.wrap(tipoSaliente).cast(datoHrtVer);
    }

    public static <T> T fromStringToArray(String dato, Type tipoSaliente) throws JsonSyntaxException {
        T object = CONVER_TO_JSON.fromJson(dato, tipoSaliente);
        return object;
    }

}
