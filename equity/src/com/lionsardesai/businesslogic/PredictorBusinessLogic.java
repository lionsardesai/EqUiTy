/**
 * 
 */
package com.lionsardesai.businesslogic;

import java.util.List;
import java.util.Map;

import com.lionsardesai.beans.PredictorListBean;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.common.exceptions.UnClassifiedException;

/**
 * @author shardul
 * 
 */
public abstract class PredictorBusinessLogic {

	public abstract void init() throws UnClassifiedException;

	public abstract Map<String, List<RAWDataBean>> getRecords(
			PredictorListBean model) throws UnClassifiedException;

	public abstract void evaluate(Map<String, List<RAWDataBean>> tmpMap,
			PredictorListBean predictorListBean);

}
