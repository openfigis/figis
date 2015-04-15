package org.fao.dataloading.countryrfb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class FIGISDocDigger {

	public Object findObject(Object object, Class<?> type) {
		Object pulled = pull(object, type);
		Object found = null;
		if (pulled.getClass().equals(type)) {
			found = pulled;
		}
		if (pulled.getClass().equals(List.class)) {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) pulled;
			for (Object element : list) {
				FIGISDocDigger d = new FIGISDocDigger();
				found = d.findObject(element, type);
			}

		}
		return found;
	}

	private Object pull(Object object, Class<?> clazz) {
		Method[] methods = object.getClass().getDeclaredMethods();
		Object found = null;

		for (Method method : methods) {
			if (method.getName().startsWith("get")) {
				try {
					Object result = method.invoke(object);
					if (result.getClass().equals(clazz) || result.getClass().equals(List.class)) {
						found = result;
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return found;
	}
}
