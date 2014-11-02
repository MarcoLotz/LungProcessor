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
package com.marcolotz.ReducerComponents;

import java.util.ArrayList;

import com.marcolotz.MapperComponents.ImageMetadata;

/**
 * In this application one generates a list of MappedValueKeys that will be
 * later serialized into JSON format. The Key Value will be the same for thes
 * mapper and for the reducer, since its data should not change if the images
 * are in the same series.
 * 
 * @author Marco Aurelio Lotz
 * 
 */
public class ReducedValue {

	private ArrayList<ImageMetadata> reducedList;

	public ReducedValue(ImageMetadata mvs) {
		reducedList = new ArrayList<ImageMetadata>();
		this.addToReducedList(mvs);
	}

	public ArrayList<ImageMetadata> getReducedList() {
		return this.reducedList;
	}

	public void addToReducedList(ImageMetadata mvs) {
		reducedList.add(mvs);
	}
}
