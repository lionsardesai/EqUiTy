/**
 * 
 */
package com.lionsardesai.businesslogicimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lionsardesai.beans.ChartListBean;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.businesslogic.ChartBusinessLogic;
import com.lionsardesai.common.exceptions.UnClassifiedException;
import com.lionsardesai.connection.ConnectionFactory;
import com.lionsardesai.dbi.SelectRAWData;

/**
 * @author shardul
 * 
 */
public class ChartBusinessLogicImpl extends ChartBusinessLogic {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lionsardesai.businesslogic.ChartBusinessLogic#init()
	 */
	@Override
	public void init() throws UnClassifiedException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lionsardesai.businesslogic.ChartBusinessLogic#getRecords(com.
	 * lionsardesai.beans.WelcomeListBean)
	 */
	@Override
	public List<RAWDataBean> getRecords(ChartListBean model)
			throws UnClassifiedException {
		try {
			ConnectionFactory temp = new ConnectionFactory();
			SqlMapClient con = temp.getConnection();
			SelectRAWData dbi = new SelectRAWData();
			// TODO default at 30 days, change inteval later
			return dbi.getRecords(con, model.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("IOException occured");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQLException occured");
			e.printStackTrace();
		}

		return null;
	}
}
