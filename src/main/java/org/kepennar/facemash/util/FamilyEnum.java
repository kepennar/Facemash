package org.kepennar.facemash.util;

import java.security.InvalidParameterException;

import com.google.common.base.Preconditions;

/**
 * Enumeration representing the families
 * <b>Values : </b> 
 * <ul>
 * 	<li>BOATS("boats")</li>
 * 	<li>GIRLS("girls")</li>
 * </ul>
 * 
 * @author KEPENNAR
 *
 */
public enum FamilyEnum {

	BOATS("boats"),
	GIRLS("girls");
	
	private final String key;
	private FamilyEnum(String pKey) {
		this.key = pKey;
	}
	public String getKey() {
		return key;
	}
	
	public static FamilyEnum getFamilyEnumFromKey(String pKey) {
		Preconditions.checkNotNull(pKey);
		
		FamilyEnum result = null;
		for (FamilyEnum family : values()) {
			if (family.getKey().equals(pKey)) {
				result = family;
			}
 		}
		if (result == null) {
			throw new InvalidParameterException("The parameter pKey (=" + pKey + "does not exist in " + FamilyEnum.class);
		}
		return result;
	}
	
	
}
