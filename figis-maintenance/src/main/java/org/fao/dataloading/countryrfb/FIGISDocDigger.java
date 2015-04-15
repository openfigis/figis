package org.fao.dataloading.countryrfb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.fao.fi.figis.devcon.Org;
import org.fao.fi.figis.devcon.OrgsInvolved;

public class FIGISDocDigger {

	public Object findObject(Object object, Class<?> type) {
		Object found = null;
		if (object.getClass().equals(type)) {
			found = object;
		} else {
			Method[] methods = object.getClass().getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().startsWith("get") && method.getParameterCount() == 0 && found == null) {
					try {
						if (method.getName().equals("getMissionsAndGeoCoveragesAndTopicCoverages")) {
							System.out.println(method.getName());
						}

						System.out.println(method.getName());
						Object result = method.invoke(object);
						if (result != null && found == null) {
							FIGISDocDigger d = new FIGISDocDigger();
							if (result.getClass().equals(type)) {
								found = result;
							} else {
								if (result instanceof List && found == null) {
									@SuppressWarnings("unchecked")
									List<Object> list = (List<Object>) result;
									for (Object element : list) {
										System.out.println(element.getClass().getSimpleName());
										if (element instanceof Org || element instanceof OrgsInvolved) {
											System.out.println(element.getClass().getSimpleName());
										}
										if (found == null) {
											found = d.findObject(element, type);
										}
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
		}
		return found;
	}
}
