package org.fao.fi.figis.statquery.generator;

import java.util.HashMap;
import java.util.Map;

public class ParameterParser {

	void apply(String aString, UrlReplacement holder) {
		String[] params = aString.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String[] nv = param.split("=");
			String name = nv[0];
			String value = "";
			if (nv.length > 1) {
				value = nv[1];
			}
			map.put(name, value);
		}
		holder.setDs(map.get(ParameterName.ds.name()));

		holder.setK1(map.get(ParameterName.k1.name()));
		holder.setOuttype(map.get(ParameterName.outtype.name()));
		holder.setGr_props(map.get(ParameterName.gr_props.name()));
		holder.setK1v(map.get(ParameterName.k1v.name()));
		holder.setK1s(map.get(ParameterName.k1s.name()));

		// =
		// "ds=Production&k1=COUNTRY&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=";

	}
}
