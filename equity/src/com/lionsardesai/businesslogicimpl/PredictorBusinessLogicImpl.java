/**
 * 
 */
package com.lionsardesai.businesslogicimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lionsardesai.beans.PredictorListBean;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.businesslogic.PredictorBusinessLogic;
import com.lionsardesai.common.exceptions.UnClassifiedException;
import com.lionsardesai.connection.ConnectionFactory;
import com.lionsardesai.dbi.SelectRAWData;

/**
 * @author shardul
 * 
 */
public class PredictorBusinessLogicImpl extends PredictorBusinessLogic {

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
	public Map<String, List<RAWDataBean>> getRecords(PredictorListBean model)
			throws UnClassifiedException {
		try {
			ConnectionFactory temp = new ConnectionFactory();
			SqlMapClient con = temp.getConnection();
			SelectRAWData dbi = new SelectRAWData();
			return dbi.getPredictorData(con);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.lionsardesai.businesslogic.PredictorBusinessLogic#evaluate(com.
	 * lionsardesai.beans.PredictorListBean)
	 */
	@Override
	public void evaluate(Map<String, List<RAWDataBean>> tmpMap,
			PredictorListBean predictorListBean) {
		List<String> displayData = new ArrayList<String>();
		// TODO add try catch
		// TODO make up some logic

		// some logic to predict future

		// set the results of the logic and exit
		predictorListBean.setDisplayData(displayData);

	}
}
