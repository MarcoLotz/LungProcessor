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

import org.apache.hadoop.conf.Configuration;

import com.marcolotz.MRComponents.KeyStructure;
import com.marcolotz.MapperComponents.ImageMetadata;
import com.marcolotz.MapperComponents.ImageStructure;
import com.marcolotz.ReducerComponents.ReducedValue;
import com.marcolotz.imageprocess.GrayNoduleCandidates;
import com.marcolotz.imageprocess.ImageProcessor;
import com.marcolotz.imageprocess.NullPreProcessor;
import com.marcolotz.imageprocess.TresholdLung;
import com.marcolotz.io.SeriesData;
import com.marcolotz.io.LungWriter;

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

		SeriesData jformatter = new SeriesData(keyStrucuture,
				reducedValueStructure);
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
