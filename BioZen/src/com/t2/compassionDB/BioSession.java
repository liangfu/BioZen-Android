/*****************************************************************
BioZen

Copyright (C) 2011 The National Center for Telehealth and 
Technology

Eclipse Public License 1.0 (EPL-1.0)

This library is free software; you can redistribute it and/or
modify it under the terms of the Eclipse Public License as
published by the Free Software Foundation, version 1.0 of the 
License.

The Eclipse Public License is a reciprocal license, under 
Section 3. REQUIREMENTS iv) states that source code for the 
Program is available from such Contributor, and informs licensees 
how to obtain it in a reasonable manner on or through a medium 
customarily used for software exchange.

Post your updates and modifications to our GitHub or email to 
t2@tee2.org.

This library is distributed WITHOUT ANY WARRANTY; without 
the implied warranty of MERCHANTABILITY or FITNESS FOR A 
PARTICULAR PURPOSE.  See the Eclipse Public License 1.0 (EPL-1.0)
for more details.
 
You should have received a copy of the Eclipse Public License
along with this library; if not, 
visit http://www.opensource.org/licenses/EPL-1.0

*****************************************************************/
package com.t2.compassionDB;

import java.util.ArrayList;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.t2.compassionMeditation.BioZenConstants;


@DatabaseTable
public class BioSession {

	public static final String USER_ID_FIELD_NAME = "user_id";	
	
	
	// id is generated by the database and set on the object automagically
	@DatabaseField(generatedId = true)
	int id;	
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = USER_ID_FIELD_NAME)
	private BioUser bioUser;
	
	
	@DatabaseField
	public long time;
	@DatabaseField
	private double valueSum = 0.00;
	@DatabaseField
	private int count = 0;

	
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public int[] minFilteredValue = new int[BioZenConstants.MAX_KEY_ITEMS];
	
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public int[] maxFilteredValue = new int[BioZenConstants.MAX_KEY_ITEMS];
	
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public int[] avgFilteredValue = new int[BioZenConstants.MAX_KEY_ITEMS];
	
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public String[] keyItemNames = new String[BioZenConstants.MAX_KEY_ITEMS];
	
//	@DatabaseField
	public String mindsetBandOfInterest = "";

	@DatabaseField
	public int mindsetBandOfInterestIndex = 0;

//	@DatabaseField
	public String bioHarnessParameterOfInterest = "";

	@DatabaseField
	public int bioHarnessParameterOfInterestIndex = 0;

	
	
	@DatabaseField
	public String comments = "";

	@DatabaseField
	public String category = "";

	@DatabaseField
	public int secondsCompleted = 0;
	
	@DatabaseField
	public int precentComplete = 0;
	
	@DatabaseField
	public String logFileName = "";
	

	@DatabaseField(dataType=DataType.SERIALIZABLE)
	private ArrayList<Double> values = new ArrayList<Double>();
	
	public BioSession() {
		// needed by ormlite
	}
	
	public BioSession(BioUser bioUser, long time) {
		this.bioUser = bioUser;
		this.time = time;
	}
	
//	public void addValue(double val) {
//		values.add(val);
//		valueSum += val;
//		++count;
//		
//		if(val > maxValue || count == 1) {
//			maxValue = val;
//		}
//		
//		if(val < minValue || count == 1) {
//			minValue = val;
//		}
//	}
	
//	public double getAverageValue() {
//		if(valueSum == 0 && count == 0) {
//			return defaultValue;
//		}
//		return valueSum / count;
//	}
	
	public double[] getValues() {
		double[] out = new double[values.size()];
		for(int i = 0; i < values.size(); ++i) {
			out[i] = values.get(i);
		}
		return out;
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i < BioZenConstants.MAX_KEY_ITEMS; i++) {
			result += minFilteredValue[i] + ", ";
		}
		for (int i = 0; i < BioZenConstants.MAX_KEY_ITEMS; i++) {
			result += maxFilteredValue[i] + ", ";
		}
		for (int i = 0; i < BioZenConstants.MAX_KEY_ITEMS; i++) {
			result += avgFilteredValue[i] + ", ";
		}
		
		result += mindsetBandOfInterestIndex + ", ";	
		result += bioHarnessParameterOfInterest + ", ";	
		result += comments + ", ";	
		result += category + ", ";	
		result += secondsCompleted + ", ";	
		result += precentComplete + ", ";	
		return result;
		
	}
}
