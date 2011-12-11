/**
 * 
 */
package com.lionsardesai.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author shardul
 * @param <T>
 * 
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -215875534295446579L;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	protected T model;

	public void setModel(T model) {
		this.model = model;
		if (this.model == null) {
			System.out.println("model is null, not setting");
		} else {
			System.out.println("setting model " + this.model.toString());
		}
	}

	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return this.model;
	}
}
