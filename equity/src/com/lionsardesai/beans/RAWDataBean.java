/**
 * 
 */
package com.lionsardesai.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shardul
 * 
 */
public class RAWDataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2688207938129276321L;

	public Date date;
	public String id;
	public String open;
	public String close;
	public String high;
	public String low;
	public String volume;
	public String adjClose;
	public String divident;

	/**
	 * @param date
	 * @param id
	 * @param open
	 * @param close
	 * @param high
	 * @param low
	 * @param volume
	 */
	public RAWDataBean(Date date, String id, String open, String close,
			String high, String low, String volume, String adjClose,
			String divident) {
		this.date = date;
		this.id = id;
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.volume = volume;
		this.adjClose = adjClose;
		this.divident = divident;
	}

	public static RAWDataBean MakeRAWDataBean(String data, String id)
			throws ParseException {
		String[] fields = data.split(",");
		// TODO read date format from property file

		DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(d.parse(fields[0]));
		if (fields.length < 5) {
			return new RAWDataBean(d.parse(fields[0]), id, null, null, null,
					null, null, null, fields[1]);
		}
		return new RAWDataBean(d.parse(fields[0]), id, fields[1], fields[2],
				fields[3], fields[4], fields[5], fields[6], null);
	}

	public RAWDataBean() {

	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	 * @return the open
	 */
	public String getOpen() {
		return open;
	}

	/**
	 * @param open
	 *            the open to set
	 */
	public void setOpen(String open) {
		this.open = open;
	}

	/**
	 * @return the close
	 */
	public String getClose() {
		return close;
	}

	/**
	 * @param close
	 *            the close to set
	 */
	public void setClose(String close) {
		this.close = close;
	}

	/**
	 * @return the high
	 */
	public String getHigh() {
		return high;
	}

	/**
	 * @param high
	 *            the high to set
	 */
	public void setHigh(String high) {
		this.high = high;
	}

	/**
	 * @return the low
	 */
	public String getLow() {
		return low;
	}

	/**
	 * @param low
	 *            the low to set
	 */
	public void setLow(String low) {
		this.low = low;
	}

	/**
	 * @return the volume
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}

	/**
	 * @return the adjClose
	 */
	public String getAdjClose() {
		return adjClose;
	}

	/**
	 * @param adjClose
	 *            the adjClose to set
	 */
	public void setAdjClose(String adjClose) {
		this.adjClose = adjClose;
	}

	/**
	 * @return the divident
	 */
	public String getDivident() {
		return divident;
	}

	/**
	 * @param divident
	 *            the divident to set
	 */
	public void setDivident(String divident) {
		this.divident = divident;
	}

}
