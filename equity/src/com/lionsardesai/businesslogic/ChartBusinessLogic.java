/**
 * 
 */
package com.lionsardesai.businesslogic;

import java.util.List;

import com.lionsardesai.beans.ChartListBean;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.common.exceptions.UnClassifiedException;

/**
 * @author shardul
 * 
 */
public abstract class ChartBusinessLogic {

	public abstract void init() throws UnClassifiedException;

	public abstract List<RAWDataBean> getRecords(ChartListBean model)
			throws UnClassifiedException;

}
