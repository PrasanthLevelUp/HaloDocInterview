package haloDocRest.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import haloDocRest.Base.BaseTest;

public class UtilsFiles extends BaseTest {

	public LinkedHashMap findtheValues(LinkedHashMap maps) {

		LinkedHashMap<String, Double> newData = new LinkedHashMap();

		int givencondtion = Integer.parseInt(prop.getProperty("conditionnumber"));

		Set set = maps.keySet();
		Iterator it = set.iterator();

		while (it.hasNext()) {
			String key = it.next().toString();
			String name = maps.get(key).toString();
			double val = Double.valueOf(name);
			if (val >= givencondtion) {
				double rounded = (double) Math.round(val * 100) / 1000;
				newData.put(key, rounded);
			}

		}

		return newData;

	}

}
