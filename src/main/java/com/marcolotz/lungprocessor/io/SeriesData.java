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

import com.marcolotz.lungprocessor.MRComponents.KeyStructure;
import com.marcolotz.lungprocessor.ReducerComponents.ReducedValue;

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
