/**
 * 
 */
package com.lionsardesai.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.lionsardesai.common.Constants;

/**
 * @author shardul
 * 
 */
public class ChartListBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8353816144236299966L;

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public String id = Constants.listAll[4];
	public List<RAWDataBean> newData = new ArrayList<RAWDataBean>();
	public String[] listAll = Constants.listAll;

	public int size = 0;

	List<String> closeList = new ArrayList<String>();

	// TODO remove unused max/min variables
	// TODO replace by range of close/open/high/low
	// variables for chart display starting from here
	public float closeMax;
	public float closeMin;
	public String highMax;
	public String highMin;
	public String lowMax;
	public String lowMin;
	public String openMax;
	public String openMix;

	/**
	 * 
	 */
	public ChartListBean() {
	}

	/**
	 * @return the newData
	 */
	public List<RAWDataBean> getNewData() {
		return newData;
	}

	/**
	 * @param newData
	 *            the newData to set
	 */
	public void setNewData(List<RAWDataBean> newData) {
		this.newData = newData;
		this.size = newData.size();
		closeMax = Float.parseFloat(newData.get(0).close);
		closeMin = Float.parseFloat(newData.get(0).close);
		logger.debug("close max value before start " + closeMax);
		logger.debug("close min value before start " + closeMin);

		for (int i = 0; i < newData.size(); i++) {
			this.closeList.add(newData.get(i).close);
			if (closeMax < Float.parseFloat(newData.get(i).close)) {
				closeMax = Float.parseFloat(newData.get(i).close);
			}
			if (closeMin > Float.parseFloat(newData.get(i).close)) {
				closeMin = Float.parseFloat(newData.get(i).close);
			}
		}
		logger.debug("closemin value after all scan " + closeMin);
		for (int i = 0; i < newData.size(); i++) {
			// logger.debug("close " + i + " value before adjust "
			// + closeList.get(i));
			float tempclose;
			tempclose = (Float.parseFloat(closeList.get(i)) - closeMin)
					* (float) 250.0 / (closeMax - closeMin) + 25;
			closeList.set(i, Float.toString(tempclose));
			// logger.debug("close " + i + " value after adjust "
			// + closeList.get(i));

		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		System.out.println(id);
		this.id = id;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the closeList
	 */
	public List<String> getCloseList() {
		return closeList;
	}
}
