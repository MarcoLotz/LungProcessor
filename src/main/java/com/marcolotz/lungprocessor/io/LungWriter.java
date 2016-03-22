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
package com.marcolotz.lungprocessor.io;

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
