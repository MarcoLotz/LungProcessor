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

package com.marcolotz.lungprocessor.MRComponents;

import com.marcolotz.lung.mapreduce.MRComponents.DICOMTags;

import ij.plugin.DICOM;
import ij.util.DicomTools;

/**
 * The key structure used by mapper and reducers. It is of paramount importance
 * that the Key element do not know an image, since it is desirable for it to be
 * as small as possible.
 * 
 * @author Marco Aurelio Lotz
 * 
 */
public class KeyStructure {

	// When the image was created.
	// TODO: Check if the content time is the same for all the DICOM file
	// or each frame is different. Really important, since it can generate
	// different keys.

	/* ======================== DICOM info ======================= */

	// Unique identifier for the series that the image belongs to.
	private String seriesInstanceUID;

	private String studyDate;
	private String seriesDate;

	private String studyTime;
	private String seriesTime;

	private String modality;

	private String manufacturer;
	private String institutionName;
	private String institutionAddress;

	private String stationName;
	private String studyDescription;

	private String patientsName;
	private String patientsID;

	private String bodyPartExamined;
	private String sliceThickness;
	private String kVP;

	/***
	 * gives the distance between two adjacent slices (perpendicular to the
	 * image plane). More detailed info at:
	 * http://stackoverflow.com/questions/14930222
	 * /how-to-calculate-space-between-dicom-slices-for-mpr
	 */
	private String spaceBetweenSlices;

	// The size of a pixel (in mm).
	private String pixelSpacing;

	/* =========================================================== */

	public KeyStructure(DICOM image) {
		generateMetadata(image);
	}

	private void generateMetadata(DICOM image) {
		seriesInstanceUID = DicomTools.getTag(image,
				DICOMTags.SeriesInstanceUID);

		studyDate = DicomTools.getTag(image, DICOMTags.StudyDate);
		seriesDate = DicomTools.getTag(image, DICOMTags.SeriesDate);
		
		studyTime = DicomTools.getTag(image, DICOMTags.StudyTime);
		seriesTime = DicomTools.getTag(image, DICOMTags.SeriesTime);

		modality = DicomTools.getTag(image, DICOMTags.Modality);

		manufacturer = DicomTools.getTag(image, DICOMTags.Manufacturer);

		institutionName = DicomTools.getTag(image, DICOMTags.InstitutionName);
		institutionAddress = DicomTools.getTag(image,
				DICOMTags.InstitutionAddress);

		stationName = DicomTools.getTag(image, DICOMTags.StationName);
		studyDescription = DicomTools.getTag(image, DICOMTags.StudyDescription);

		patientsName = DicomTools.getTag(image, DICOMTags.PatientsName);
		patientsID = DicomTools.getTag(image, DICOMTags.PatientsID);

		bodyPartExamined = DicomTools.getTag(image, DICOMTags.BodyPartExamined);
		sliceThickness = DicomTools.getTag(image, DICOMTags.SliceThickness);
		kVP = DicomTools.getTag(image, DICOMTags.KVP);
		spaceBetweenSlices = DicomTools.getTag(image,DICOMTags.SpaceBetweenSlices);

		pixelSpacing = DicomTools.getTag(image, DICOMTags.PixelSpacing);
	}

	public String getSeriesInstanceUID() {
		return seriesInstanceUID;
	}

	public String getStudyDate() {
		return studyDate;
	}

	public String getSeriesDate() {
		return seriesDate;
	}

	public String getStudyTime() {
		return studyTime;
	}

	public String getSeriesTime() {
		return seriesTime;
	}

	public String getModality() {
		return modality;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public String getInstitutionAddress() {
		return institutionAddress;
	}

	public String getStationName() {
		return stationName;
	}

	public String getStudyDescription() {
		return studyDescription;
	}

	public String getPatientsName() {
		return patientsName;
	}

	public String getPatientsID() {
		return patientsID;
	}

	public String getBodyPartExamined() {
		return bodyPartExamined;
	}

	public String getSliceThickness() {
		return sliceThickness;
	}

	public String getkVP() {
		return kVP;
	}

	public String getPixelSpacing() {
		return pixelSpacing;
	}

	/**
	 * @return the spaceBetweenSlices
	 */
	public String getSpaceBetweenSlices() {
		return spaceBetweenSlices;
	}
}