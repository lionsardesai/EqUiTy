/**
 * 
 */
package com.lionsardesai.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lionsardesai.beans.PredictorListBean;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.businesslogic.PredictorBusinessLogic;
import com.lionsardesai.businesslogicimpl.PredictorBusinessLogicImpl;
import com.lionsardesai.common.exceptions.UnClassifiedException;

/**
 * @author shardul
 * 
 */
public class PredictorAction extends BaseAction<PredictorListBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2554309534014029008L;

	private static final Logger logger = Logger.getLogger(PredictorAction.class
			.getSimpleName());

	@Override
	public PredictorListBean getModel() {
		if (model == null) {
			this.setModel(new PredictorListBean());
		}
		return model;
	}

	@Override
	public String execute() {

		PredictorBusinessLogic predictorLogic = new PredictorBusinessLogicImpl();

		try {
			predictorLogic.init();
			Map<String, List<RAWDataBean>> tmpMap = predictorLogic
					.getRecords(getModel());
			predictorLogic.evaluate(tmpMap, getModel());
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
