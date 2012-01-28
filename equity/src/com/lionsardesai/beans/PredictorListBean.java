/**
 * 
 */
package com.lionsardesai.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shardul
 * 
 */
public class PredictorListBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6446435910451215014L;

	List<String> displayData = new ArrayList<String>();
	String max;
	String min;

	/**
	 * @return the displayData
	 */
	public List<String> getDisplayData() {
		return displayData;
	}

	/**
	 * @param displayData
	 *            the displayData to set
	 */
	public void setDisplayData(List<String> displayData) {
		this.displayData = displayData;
	}

	/**
	 * @return the max
	 */
	public String getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * @return the min
	 */
	public String getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(String min) {
		this.min = min;
	}

}
