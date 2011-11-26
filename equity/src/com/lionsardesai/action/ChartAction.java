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

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public ChartListBean getModel() {
		if (this.model == null) {
			this.setModel(new ChartListBean());
		}
		return this.model;
	}

	@Override
	public String execute() throws Exception {
		// ChartBusinessLogic welcome = new FakeWelcomeBusinessLogicImpl();
		ChartBusinessLogic chartlogic = new ChartBusinessLogicImpl();

		try {
			// welcome.init();
			// welcomelogic.init();
			logger.info("setting data");
			getModel().setNewData(chartlogic.getRecords(getModel()));
			logger.info("data set");
			// this.getModel().setNewData(
			// welcomelogic.updateRecords(this.getModel()));

		} catch (NullPointerException e) {
			// TODO Redirect to error page using error action
			logger.error("Null po - prolly model");
		} catch (UnClassifiedException e) {
			// TODO Redirect to error page using error action
		}
		return "SUCCESS";
	}

}
