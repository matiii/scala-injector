package core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Mateusz on 2015-10-22.
 */
public class FactoryInjector {
    public <T> T getInstance(Constructor<?> constructor, Object[] parameters) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) constructor.newInstance(parameters);
    }
}
