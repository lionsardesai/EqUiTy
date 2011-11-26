/**
 * 
 */
package com.lionsardesai.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

	public String id = Constants.listAll[4];
	public List<RAWDataBean> newData = new ArrayList<RAWDataBean>();
	public String[] listAll = Constants.listAll;

	public int size = 0;

	List<String> closeList = new ArrayList<String>();

	// TODO remove unused max/min variables
	// TODO replace by range of close/open/high/low
	public String closeMax;
	public String closeMin;
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
		// closeMax = newData.get(0).close;
		for (int i = 0; i < newData.size(); i++) {
			this.closeList.add(newData.get(i).close);
			// if (Float.parseFloat(closeMax) < Float
			// .parseFloat(newData.get(i).close)) {
			// closeMax = newData.get(i).close;
			// }
			// if (Float.parseFloat(closeMin) > Float
			// .parseFloat(newData.get(i).close)) {
			// closeMin = newData.get(i).close;
			// }
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
