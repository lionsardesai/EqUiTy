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
	public static final String CHART_TYPE_LINE = "line";
	/** candle type of graph */
	public static final String CHART_TYPE_CANDLE = "candle";
	/** bar type of graph */
	public static final String CHART_TYPE_BAR = "bar";

	private static final Logger logger = Logger.getLogger(ChartListBean.class
			.getSimpleName());

	public String tickerString = null;

	public String id = Constants.listAll[4];
	public List<RAWDataBean> newData = new ArrayList<RAWDataBean>();
	public String[] listAll = Constants.listAll;

	public int size = 0;

	List<String> closeList = new ArrayList<String>();
	List<String> highList = new ArrayList<String>();
	List<String> lowList = new ArrayList<String>();
	List<String> openList = new ArrayList<String>();
	List<String> volumeList = new ArrayList<String>();

	/** technical analysis chart select */
	String techChart = null;
	List<String> techData = new ArrayList<String>();

	// TODO use max and min in logic and only keep one max min for final data
	// variables for chart display starting from here
	public float closeMax;
	public float closeMin;
	public float highMax;
	public float highMin;
	public float lowMax;
	public float lowMin;
	public float openMax;
	public float openMin;
	public float volumeMax;
	public float volumeMin;

	/*
	 * is it possible to shift common variables for pages to someplace else?
	 */
	/** line, candle or other chart types to display */
	public String chartType = CHART_TYPE_LINE;
	public int tabNumber = 0;

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
		size = newData.size();
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
	public String getChartType() {
		return chartType;
	}

	/**
	 * @param chartType
	 *            the chartType to set
	 */
	public void setChartType(String chartType) {
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

	/**
	 * @param closeList
	 *            the closeList to set
	 */
	public void setCloseList(List<String> closeList) {
		this.closeList = closeList;
	}

	/**
	 * @param highList
	 *            the highList to set
	 */
	public void setHighList(List<String> highList) {
		this.highList = highList;
	}

	/**
	 * @param lowList
	 *            the lowList to set
	 */
	public void setLowList(List<String> lowList) {
		this.lowList = lowList;
	}

	/**
	 * @param openList
	 *            the openList to set
	 */
	public void setOpenList(List<String> openList) {
		this.openList = openList;
	}

	/**
	 * @return the techChart
	 */
	public String getTechChart() {
		return techChart;
	}

	/**
	 * @param techChart
	 *            the techChart to set
	 */
	public void setTechChart(String techChart) {
		this.techChart = techChart;
	}

	/**
	 * @return the closeMax
	 */
	public float getCloseMax() {
		return closeMax;
	}

	/**
	 * @param closeMax
	 *            the closeMax to set
	 */
	public void setCloseMax(float closeMax) {
		this.closeMax = closeMax;
	}

	/**
	 * @return the closeMin
	 */
	public float getCloseMin() {
		return closeMin;
	}

	/**
	 * @param closeMin
	 *            the closeMin to set
	 */
	public void setCloseMin(float closeMin) {
		this.closeMin = closeMin;
	}

	/**
	 * @return the highMax
	 */
	public float getHighMax() {
		return highMax;
	}

	/**
	 * @param highMax
	 *            the highMax to set
	 */
	public void setHighMax(float highMax) {
		this.highMax = highMax;
	}

	/**
	 * @return the highMin
	 */
	public float getHighMin() {
		return highMin;
	}

	/**
	 * @param highMin
	 *            the highMin to set
	 */
	public void setHighMin(float highMin) {
		this.highMin = highMin;
	}

	/**
	 * @return the lowMax
	 */
	public float getLowMax() {
		return lowMax;
	}

	/**
	 * @param lowMax
	 *            the lowMax to set
	 */
	public void setLowMax(float lowMax) {
		this.lowMax = lowMax;
	}

	/**
	 * @return the lowMin
	 */
	public float getLowMin() {
		return lowMin;
	}

	/**
	 * @param lowMin
	 *            the lowMin to set
	 */
	public void setLowMin(float lowMin) {
		this.lowMin = lowMin;
	}

	/**
	 * @return the openMax
	 */
	public float getOpenMax() {
		return openMax;
	}

	/**
	 * @param openMax
	 *            the openMax to set
	 */
	public void setOpenMax(float openMax) {
		this.openMax = openMax;
	}

	/**
	 * @return the openMin
	 */
	public float getOpenMin() {
		return openMin;
	}

	/**
	 * @param openMin
	 *            the openMin to set
	 */
	public void setOpenMin(float openMin) {
		this.openMin = openMin;
	}

	/**
	 * @return the techData
	 */
	public List<String> getTechData() {
		return techData;
	}

	/**
	 * @param techData
	 *            the techData to set
	 */
	public void setTechData(List<String> techData) {
		this.techData = techData;
	}

	/**
	 * @return the volumeList
	 */
	public List<String> getVolumeList() {
		return volumeList;
	}

	/**
	 * @param volumeList
	 *            the volumeList to set
	 */
	public void setVolumeList(List<String> volumeList) {
		this.volumeList = volumeList;
	}

	/**
	 * @return the volumeMax
	 */
	public float getVolumeMax() {
		return volumeMax;
	}

	/**
	 * @param volumeMax
	 *            the volumeMax to set
	 */
	public void setVolumeMax(float volumeMax) {
		this.volumeMax = volumeMax;
	}

	/**
	 * @return the volumeMin
	 */
	public float getVolumeMin() {
		return volumeMin;
	}

	/**
	 * @param volumeMin
	 *            the volumeMin to set
	 */
	public void setVolumeMin(float volumeMin) {
		this.volumeMin = volumeMin;
	}

	/**
	 * @return the tickerString
	 */
	public String getTickerString() {
		return tickerString;
	}

	/**
	 * @param tickerString
	 *            the tickerString to set
	 */
	public void setTickerString(String tickerString) {
		this.tickerString = tickerString;
	}

}
