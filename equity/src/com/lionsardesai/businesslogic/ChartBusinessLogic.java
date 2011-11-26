/**
 * 
 */
package com.lionsardesai.businesslogic;

import java.io.InputStreamReader;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lionsardesai.beans.ChartListBean;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.common.exceptions.UnClassifiedException;
import com.lionsardesai.dbi.YahooRecords;

/**
 * @author shardul
 * 
 */
public abstract class ChartBusinessLogic {

	public abstract void init() throws UnClassifiedException;

	public abstract List<RAWDataBean> updateRecords(String id,
			YahooRecords dbi, InputStreamReader in, SqlMapClient con)
			throws UnClassifiedException;

	public abstract List<RAWDataBean> getRecords(ChartListBean model)
			throws UnClassifiedException;

}
