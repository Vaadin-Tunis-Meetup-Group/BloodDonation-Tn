package org.vaadin.tunis.blooddonation.security;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtil {

	public static String hashPassword(String value) {
		return DigestUtils.sha1Hex(value);
	}

}
