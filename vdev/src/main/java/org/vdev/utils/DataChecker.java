package org.vdev.utils;

import org.vdev.constants.Constants;

public class DataChecker {
	
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Object obj) {
		
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			if (Constants.EMPTY.equals(obj)) {
				return true;
			}
		}
		return false;
	}
}
