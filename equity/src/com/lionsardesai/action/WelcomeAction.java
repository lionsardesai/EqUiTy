package com.lionsardesai.action;

import org.apache.log4j.Logger;

import com.lionsardesai.beans.WelcomeListBean;
import com.lionsardesai.businesslogic.WelcomeBusinessLogic;
import com.lionsardesai.businesslogicimpl.WelcomeBusinessLogicImpl;
import com.lionsardesai.common.exceptions.UnClassifiedException;

public class WelcomeAction extends BaseAction<WelcomeListBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1902554403752293956L;

	private static final Logger logger = Logger.getLogger(WelcomeAction.class
			.getSimpleName());

	@Override
	public String execute() {
		WelcomeBusinessLogic welcomeLogic = new WelcomeBusinessLogicImpl();

		try {
			welcomeLogic.init();
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
