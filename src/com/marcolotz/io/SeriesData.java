/*******************************************************************************
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 * 
 * Copyright (c) 2014 Marco Aurelio Barbosa Fagnani Gomes Lotz (marcolotz.com)
 * 
 * The source code in this document is licensed under Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License. You must 
 * credit the author of the source code in the way specified by the author or
 * licenser (but not in a way to suggest that the author or licenser has given 
 * you allowance to you or to your use of the source code). If you modify,
 * transform or create using this source code as basis, you can only distribute
 * the new source code under the same license or a similar license to this one.
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * To see a copy of the license, access:
 * creativecommons.org/licenses/by-nc-sa/4.0/legalcode
 ******************************************************************************/
package com.marcolotz.io;

import com.marcolotz.MRComponents.KeyStructure;
import com.marcolotz.ReducerComponents.ReducedValue;

/**
 * Contains all the meta information of a processed DICOM series. By series, one can
 * understand a group of images that were obtained in the same exam procedure.
 * 
 * @author Marco Aurelio Lotz
 * 
 */
public class SeriesData {
	KeyStructure keyStructure;
	ReducedValue reducedValue;

	public SeriesData(KeyStructure ks, ReducedValue rValue) {
		this.keyStructure = ks;
		this.reducedValue = rValue;
	}

	/**
	 * @return the keyStructure
	 */
	public KeyStructure getKeyStructure() {
		return keyStructure;
	}

	/**
	 * @return the reducedValue
	 */
	public ReducedValue getReducedValue() {
		return reducedValue;
	}

}
