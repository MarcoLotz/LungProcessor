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

package com.marcolotz.lungprocessor.ReducerComponents;

import java.util.ArrayList;

import com.marcolotz.lung.mapreduce.MapperComponents.ImageMetadata;

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