package org.fao.dataloading.countryrfb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javassist.Modifier;

/**
 * Utility to dig into the FigisDoc
 * 
 * 
 * 
 * @author Erik van Ingen
 *
 */
public class FIGISDocDigger {

	/**
	 * Find the first object, corresponding to the type
	 * 
	 * 
	 * @param object
	 * @param type
	 * @return
	 */
	public Object findObject(Object object, Class<?> type) {
		Object found = null;
		if (object.getClass().equals(type)) {
			found = object;
		} else {
			Method[] methods = object.getClass().getDeclaredMethods();
			for (Method method : methods) {
				if (method.getModifiers() == Modifier.PUBLIC && method.getName().startsWith("get")
						&& method.getParameterCount() == 0 && found == null) {
					try {
						Object result = method.invoke(object);
						if (result != null && found == null) {
							if (result.getClass().equals(type)) {
								found = result;
							} else {
								if (result instanceof List && found == null) {
									@SuppressWarnings("unchecked")
									List<Object> list = (List<Object>) result;
									for (Object element : list) {
										if (found == null) {
											FIGISDocDigger d = new FIGISDocDigger();
											found = d.findObject(element, type);
										}
									}
								} else {
									FIGISDocDigger d = new FIGISDocDigger();
									found = d.findObject(result, type);
								}
							}
						}

					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		return found;
	}
}
