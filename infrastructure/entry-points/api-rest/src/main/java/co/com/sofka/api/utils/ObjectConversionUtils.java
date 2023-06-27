package co.com.sofka.api.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ObjectConversionUtils {
    public static <T> T convertir(Object source, Class<T> targetClass) {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir la clase", e);
        }
    }
}
