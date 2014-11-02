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
package com.marcolotz;

import ij.plugin.DICOM;

import com.marcolotz.MRComponents.KeyStructure;
import com.marcolotz.MapperComponents.ImageStructure;

/**
 * DICOM specialized format reader. Reads the image data and also the metadata
 * inside the DICOM image.
 * 
 * @author Marco Aurelio Lotz
 * 
 */
public class DicomReader {

	private DICOM source = new DICOM();

	private String sourceAddress;

	// Time Variables
	private float startTime;
	private float totalLatency;

	// DICOM series info, used as the main Map and Reduce key.
	private KeyStructure keyStructure;

	// DICOM image info, used as the Map value.
	ImageStructure mappedValueStructure;

	public DicomReader(String sourceAddress) {
		startTime = System.currentTimeMillis();

		this.sourceAddress = sourceAddress;

		System.out.print("Openning source address... ");
		source.open(sourceAddress);
		System.out.println("[OK]");

		if (source != null) {
			System.out.println("Getting image info:");
			System.out.print("Generating key structure... ");
			keyStructure = new KeyStructure(source);
			System.out.println("[OK]");

			System.out.print("Generating mapped value structure... ");
			mappedValueStructure = new ImageStructure(source);
			System.out.println("[OK]");
		} else {
			// TODO: Make a throw exception here.
			System.out.println("Problem reading the DICOM image.");
		}

		totalLatency = System.currentTimeMillis() - startTime;

		System.out.println("Total latency (ms): " + getLatency());

	}

	@Override
	public String toString() {
		String buffer = new String("");
		buffer = "Source address: ";

		if (sourceAddress == "") {
			buffer = buffer + "no source image\n";
		} else
			buffer = buffer + this.sourceAddress + "\n";

		buffer = buffer + "Total latency Image Reading (ms): "
				+ this.totalLatency + "\n";
		return buffer;
	}

	public float getLatency() {
		return this.totalLatency;
	}

	public DICOM getImage() {
		return this.source;
	}

	public String getSourceAddress() {
		return this.sourceAddress;
	}

	public KeyStructure getKeyStructure() {
		return this.keyStructure;
	}

	public ImageStructure getMappedValueStructure() {
		return this.mappedValueStructure;
	}
}
