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

import org.apache.hadoop.conf.Configuration;

import com.marcolotz.lung.mapreduce.MapperComponents.ImageMetadata;
import com.marcolotz.lung.mapreduce.MapperComponents.ImageStructure;
import com.marcolotz.imageprocess.GrayNoduleCandidates;
import com.marcolotz.imageprocess.ImageProcessor;
import com.marcolotz.imageprocess.NullPreProcessor;
import com.marcolotz.imageprocess.TresholdLung;
import com.marcolotz.lungprocessor.MRComponents.KeyStructure;
import com.marcolotz.lungprocessor.ReducerComponents.ReducedValue;
import com.marcolotz.lungprocessor.io.LungWriter;
import com.marcolotz.lungprocessor.io.SeriesData;

/**
 * Main class, contains the image reader, the processor and a simple viewer.
 * 
 * @author Marco Aurelio Lotz
 * 
 */
public class LungProcessor {
	static {
		/* User defined configuration file */
		Configuration.addDefaultResource("./lungConfiguration.xml");
	}

	/*
	 * Object to retrieve application default configurations This allows the
	 * ImageProcessingClass classes to receive configurations without using
	 * parameters. It's useful for when using MapReduce applications, since the
	 * Mapper and Reducer only receives configuration file as parameters.
	 */
	static Configuration conf = new Configuration();

	static DicomReader reader;
	static ImageProcessor processor;
	static ImageViewer viewer;
	static String ImageAddress = "/home/prometheus/Dropbox/TCC/code/matlab/TestImages/I1400001";

	static KeyStructure keyStrucuture;
	static ImageStructure imageStructure;
	static ImageMetadata imageMetadata;
	static ReducedValue reducedValueStructure;

	static LungWriter lungWriter;
	static String outputJSONAddress = "/home/prometheus/Dropbox/TCC/output/framework/Output.json";

	public static void main(String[] args) {
		readImage(ImageAddress);

		viewer = new ImageViewer();
		viewer.setImage(reader.getImage());

		processImage(reader);

		generateMetadata();

		serializeData();

		printEndMsg();
		// System.exit(0);
	}

	public static void readImage(String ImageAddress) {
		System.out.println("Lauching reader application...");
		reader = new DicomReader(ImageAddress);
		System.out.println(reader.toString());
		System.out.println("\n Reader: [OK]");
		System.out.println("==============================");
	}

	public static void processImage(DicomReader reader) {
		System.out.println("Lauching Image Processor Application...");

		// Insert user defined classes here.
		processor = new ImageProcessor(NullPreProcessor.class,
				TresholdLung.class, GrayNoduleCandidates.class, conf);

		processor.setInput(reader.getImage());
		processor.run();

		System.out.println(processor.toString());
		System.out.println("\n Image Processor: [OK]");
		System.out.println("==============================");
	}

	public static void generateMetadata() {
		System.out.print("Generating Map Key Structure... ");
		keyStrucuture = reader.getKeyStructure();
		imageStructure = reader.getMappedValueStructure();
		System.out.println("[OK]");

		System.out.print("Generating image Structure... ");
		imageStructure.setNodeCandidatesList(processor.getNodeCandidates());
		System.out.println("[OK]");

		System.out.print("Generating image metadata mapped structure... ");
		imageMetadata = new ImageMetadata(imageStructure);
		System.out.println("[OK]");

		System.out.print("Generating Reduce Value Structure... ");
		reducedValueStructure = new ReducedValue(imageMetadata);
		System.out.println("[OK]");

		System.out.println("\n generate Metadata: [OK]");
		System.out.println("==============================");
	}

	private static void serializeData() {
		System.out.println("Writing output on JSON Format... ");

		SeriesData jformatter = new SeriesData(keyStrucuture,reducedValueStructure);
		lungWriter = new LungWriter(outputJSONAddress, jformatter);

		lungWriter.Write();

		System.out.println("\n LungWriter : [OK]");
		System.out.println("==============================");
	}

	private static void printEndMsg() {
		System.out.println("******************************");
		System.out.println("\n LungProcessor framework status: [SUCCESS]");
		System.out.println("******************************");
	}
}
