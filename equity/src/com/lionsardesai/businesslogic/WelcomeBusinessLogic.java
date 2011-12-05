/**
 * 
 */
package com.lionsardesai.businesslogic;

import java.io.InputStreamReader;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.common.exceptions.UnClassifiedException;
import com.lionsardesai.dbi.YahooRecords;

/**
 * @author shardul
 * 
 */
public abstract class WelcomeBusinessLogic {

	public abstract void init() throws UnClassifiedException;

	/**
	 * @param id
	 * @param dbi
	 * @param in
	 * @param con
	 * @return
	 * @throws UnClassifiedException
	 */
	public abstract List<RAWDataBean> updateRecords(String id,
			YahooRecords dbi, InputStreamReader in, SqlMapClient con)
			throws UnClassifiedException;

}
