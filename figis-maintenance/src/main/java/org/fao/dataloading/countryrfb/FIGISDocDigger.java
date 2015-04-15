package org.fao.dataloading.countryrfb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class FIGISDocDigger {

	public Object findObject(Object object, Class<?> type) {
		Method[] methods = object.getClass().getDeclaredMethods();
		Object found = null;
		for (Method method : methods) {
			if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
				try {
					Object result = method.invoke(object);
					if (result != null) {
						System.out.println(result.getClass().getSimpleName());
						if (result.getClass().equals(type)) {
							found = result;
						}
						if (result instanceof List) {
							@SuppressWarnings("unchecked")
							List<Object> list = (List<Object>) result;
							for (Object element : list) {
								FIGISDocDigger d = new FIGISDocDigger();
								found = d.findObject(element, type);
							}
						}
					}

				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return found;
	}

}
