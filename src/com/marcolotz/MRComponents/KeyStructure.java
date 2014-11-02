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
package com.marcolotz.MRComponents;

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
