/**
 * 
 */
package com.lionsardesai.action;

import org.apache.log4j.Logger;

import com.lionsardesai.beans.ChartListBean;
import com.lionsardesai.businesslogic.ChartBusinessLogic;
import com.lionsardesai.businesslogicimpl.ChartBusinessLogicImpl;
import com.lionsardesai.common.exceptions.UnClassifiedException;

/**
 * @author shardul
 * 
 */
public class ChartAction extends BaseAction<ChartListBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5071072182260473466L;

	private static final Logger logger = Logger.getLogger(ChartAction.class
			.getSimpleName());

	@Override
	public ChartListBean getModel() {
		if (this.model == null) {
			this.setModel(new ChartListBean());
		}
		return this.model;
	}

	@Override
	public String execute() throws Exception {
		ChartBusinessLogic chartlogic = new ChartBusinessLogicImpl();

		try {
			chartlogic.init();
			getModel().setNewData(chartlogic.getRecords(getModel()));
			chartlogic.evaluate(getModel());
			getModel().setTickerString(chartlogic.getTicker());
		} catch (NullPointerException e) {
			// TODO Redirect to error page using error action
			logger.error("NULL pointer caught");
		} catch (UnClassifiedException e) {
			// TODO Redirect to error page using error action
			logger.error("UnClassified Exception Caught");
		}
		return "SUCCESS";
	}

}
