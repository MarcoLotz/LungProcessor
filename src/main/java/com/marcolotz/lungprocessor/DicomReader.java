/*******************************************************************************
 * Copyright (c) 2002-2016 "Marco Aurelio Barbosa Fagnani Gomes Lotz"
 * [http://www.marcolotz.com]
 *
 * This file is part of Marco Lotz Hadoop Lung solution.
 *
 * Hadoop Lung is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.marcolotz.lungprocessor;

import ij.plugin.DICOM;

import com.marcolotz.lungprocessor.MRComponents.KeyStructure;
import com.marcolotz.lung.mapreduce.MapperComponents.ImageStructure;

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
