package org.fao.dataloading.countryrfb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.fao.fi.figis.devcon.OrgRef;

public class FIGISDocDigger {

	public Object findObject(Object object, Class<?> type) {
		Method[] methods = object.getClass().getDeclaredMethods();
		Object found = null;
		for (Method method : methods) {
			if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
				try {
					Object result = method.invoke(object);
					if (result != null) {

						if (result instanceof OrgRef) {
							System.out.println(result.getClass().getSimpleName());
						}
						FIGISDocDigger d = new FIGISDocDigger();
						if (result.getClass().equals(type)) {
							found = result;
						} else {
							if (result instanceof List) {
								@SuppressWarnings("unchecked")
								List<Object> list = (List<Object>) result;
								for (Object element : list) {
									found = d.findObject(element, type);
								}
							} else {
								found = d.findObject(result, type);
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
