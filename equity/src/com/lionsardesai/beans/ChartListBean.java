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

	/** line type of graph @see */
	private static final int CHART_TYPE_LINE = 0;
	/** candle type of graph */
	private static final int CHART_TYPE_CANDLE = 1;
	/** bar type of graph */
	private static final int CHART_TYPE_BAR = 2;

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public String id = Constants.listAll[4];
	public List<RAWDataBean> newData = new ArrayList<RAWDataBean>();
	public String[] listAll = Constants.listAll;

	public int size = 0;

	List<String> closeList = new ArrayList<String>();
	List<String> highList = new ArrayList<String>();
	List<String> lowList = new ArrayList<String>();
	List<String> openList = new ArrayList<String>();

	// TODO remove unused max/min variables
	// TODO replace by range of close/open/high/low
	// variables for chart display starting from here
	public float closeMax;
	public float closeMin;
	public float highMax;
	public float highMin;
	public float lowMax;
	public float lowMin;
	public float openMax;
	public float openMin;

	/*
	 * is it possible to shift common variables for pages to someplace else?
	 */
	/** line, candle or other chart types to display */
	public int chartType = CHART_TYPE_LINE;
	public int tabNumber;

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
		openMax = Float.parseFloat(newData.get(0).open);
		openMin = Float.parseFloat(newData.get(0).open);
		highMax = Float.parseFloat(newData.get(0).high);
		highMin = Float.parseFloat(newData.get(0).high);
		lowMax = Float.parseFloat(newData.get(0).low);
		lowMin = Float.parseFloat(newData.get(0).low);

		logger.debug("close max value before start " + closeMax);
		logger.debug("close min value before start " + closeMin);

		for (int i = 0; i < newData.size(); i++) {
			this.closeList.add(newData.get(i).close);
			this.openList.add(newData.get(i).open);
			this.highList.add(newData.get(i).high);
			logger.debug(this.highList.get(i));
			this.lowList.add(newData.get(i).low);

			// assign closeMax
			if (closeMax < Float.parseFloat(newData.get(i).close)) {
				closeMax = Float.parseFloat(newData.get(i).close);
			}
			// assign closeMin
			if (closeMin > Float.parseFloat(newData.get(i).close)) {
				closeMin = Float.parseFloat(newData.get(i).close);
			}
			// assign openMax
			if (openMax < Float.parseFloat(newData.get(i).open)) {
				openMax = Float.parseFloat(newData.get(i).open);
			}
			// assign openMin
			if (openMin > Float.parseFloat(newData.get(i).open)) {
				openMin = Float.parseFloat(newData.get(i).open);
			}
			// assign highMax
			if (highMax < Float.parseFloat(newData.get(i).high)) {
				highMax = Float.parseFloat(newData.get(i).high);
			}
			// assign highMin
			if (highMin > Float.parseFloat(newData.get(i).high)) {
				highMin = Float.parseFloat(newData.get(i).high);
			}
			// assign lowMax
			if (lowMax < Float.parseFloat(newData.get(i).low)) {
				lowMax = Float.parseFloat(newData.get(i).low);
			}
			// assign lowMin
			if (lowMin > Float.parseFloat(newData.get(i).low)) {
				lowMin = Float.parseFloat(newData.get(i).low);
			}

		}

		// scaling
		for (int i = 0; i < newData.size(); i++) {
			float tempclose;
			// close
			tempclose = (Float.parseFloat(closeList.get(i)) - closeMin)
					* (float) 250.0 / (closeMax - closeMin) + 25;
			closeList.set(i, Float.toString(tempclose));

			// open
			tempclose = (Float.parseFloat(openList.get(i)) - openMin)
					* (float) 250.0 / (openMax - openMin) + 25;
			openList.set(i, Float.toString(tempclose));

			// high
			tempclose = (Float.parseFloat(highList.get(i)) - highMin)
					* (float) 250.0 / (highMax - highMin) + 25;
			highList.set(i, Float.toString(tempclose));

			// low
			tempclose = (Float.parseFloat(lowList.get(i)) - lowMin)
					* (float) 250.0 / (lowMax - lowMin) + 25;
			lowList.set(i, Float.toString(tempclose));

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

	/**
	 * @return the chartType
	 */
	public int getChartType() {
		return chartType;
	}

	/**
	 * @param chartType
	 *            the chartType to set
	 */
	public void setChartType(int chartType) {
		this.chartType = chartType;
	}

	/**
	 * @return the tabNumber
	 */
	public int getTabNumber() {
		return tabNumber;
	}

	/**
	 * @param tabNumber
	 *            the tabNumber to set
	 */
	public void setTabNumber(int tabNumber) {
		this.tabNumber = tabNumber;
	}

	/**
	 * @return the highList
	 */
	public List<String> getHighList() {
		return highList;
	}

	/**
	 * @return the lowList
	 */
	public List<String> getLowList() {
		return lowList;
	}

	/**
	 * @return the openList
	 */
	public List<String> getOpenList() {
		return openList;
	}
}
