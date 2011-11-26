/**
 * 
 */
package com.lionsardesai.beans;

/**
 * @author shardul
 * 
 */
public class FakeBean {

	public String open;
	public String close;
	public String id;

	/**
	 * @param open
	 * @param close
	 * @param id
	 */
	public FakeBean() {
		this.open = "80";
		this.close = "100";
		this.id = "MCD";
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
	 * @return the high
	 */
	public String getClose() {
		return close;
	}

	/**
	 * @param close
	 *            the high to set
	 */
	public void setClose(String close) {
		this.close = close;
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

}
