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

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Writes the content of the JsonFormater into a JSON file
 * 
 * @author Marco Aurelio Lotz
 * 
 */
public class LungWriter {
	String outputPath;
	SeriesData jformater;
	Writer writer;

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/***
	 * Default class constructor
	 * 
	 * @param outputPath
	 * @param jformater
	 */
	public LungWriter(String outputPath, SeriesData jformater) {
		this.outputPath = outputPath;
		this.jformater = jformater;
	}

	/***
	 * Writes the content of the Json formatter into a JSON file
	 */
	public void Write() {
		createWriter();

		gson.toJson(jformater, writer);

		closeWriter();
	}

	private void createWriter() {
		if (this.writer == null) {
			try {
				this.writer = new FileWriter(outputPath);
			} catch (IOException e) {
				System.out.println("Error opening the JSON output");
				e.printStackTrace();
			}
		}
	}

	private void closeWriter() {
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Error closing the JSON output");
			e.printStackTrace();
		}
	}

}
