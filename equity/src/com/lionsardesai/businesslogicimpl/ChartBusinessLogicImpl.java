/**
 * 
 */
package com.lionsardesai.businesslogicimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lionsardesai.beans.ChartListBean;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.businesslogic.ChartBusinessLogic;
import com.lionsardesai.common.Constants;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lionsardesai.businesslogic.ChartBusinessLogic#evaluate(com.lionsardesai
	 * .beans.ChartListBean)
	 */
	@Override
	public void evaluate(ChartListBean model) {
		List<RAWDataBean> newData = model.getNewData();
		float closeMax = Float.parseFloat(newData.get(0).close);
		float closeMin = Float.parseFloat(newData.get(0).close);
		float openMax = Float.parseFloat(newData.get(0).open);
		float openMin = Float.parseFloat(newData.get(0).open);
		float highMax = Float.parseFloat(newData.get(0).high);
		float highMin = Float.parseFloat(newData.get(0).high);
		float lowMax = Float.parseFloat(newData.get(0).low);
		float lowMin = Float.parseFloat(newData.get(0).low);

		logger.debug("close max value before start " + closeMax);
		logger.debug("close min value before start " + closeMin);

		List<String> closeList = new ArrayList<String>();
		List<String> openList = new ArrayList<String>();
		List<String> highList = new ArrayList<String>();
		List<String> lowList = new ArrayList<String>();

		for (int i = 0; i < newData.size(); i++) {
			closeList.add(newData.get(i).close);
			openList.add(newData.get(i).open);
			highList.add(newData.get(i).high);
			lowList.add(newData.get(i).low);

			// assign closeMax
			if (closeMax < Float.parseFloat(newData.get(i).close)) {
				closeMax = Float.parseFloat(newData.get(i).close);
			}
			// assign closeMin
			if (closeMin > Float.parseFloat(newData.get(i).close)) {
				closeMin = Float.parseFloat(newData.get(i).close);
			}
			// assign openMax
			if (openMax < Float.parseFloat(newData.get(i).open)) {
				openMax = Float.parseFloat(newData.get(i).open);
			}
			// assign openMin
			if (openMin > Float.parseFloat(newData.get(i).open)) {
				openMin = Float.parseFloat(newData.get(i).open);
			}
			// assign highMax
			if (highMax < Float.parseFloat(newData.get(i).high)) {
				highMax = Float.parseFloat(newData.get(i).high);
			}
			// assign highMin
			if (highMin > Float.parseFloat(newData.get(i).high)) {
				highMin = Float.parseFloat(newData.get(i).high);
			}
			// assign lowMax
			if (lowMax < Float.parseFloat(newData.get(i).low)) {
				lowMax = Float.parseFloat(newData.get(i).low);
			}
			// assign lowMin
			if (lowMin > Float.parseFloat(newData.get(i).low)) {
				lowMin = Float.parseFloat(newData.get(i).low);
			}

		}

		// scaling
		for (int i = 0; i < newData.size(); i++) {
			float tempclose;
			// close
			tempclose = (Float.parseFloat(closeList.get(i)) - closeMin)
					* (float) 250.0 / (closeMax - closeMin) + 25;
			closeList.set(i, Float.toString(tempclose));

			// open
			tempclose = (Float.parseFloat(openList.get(i)) - closeMin)
					* (float) 250.0 / (closeMax - closeMin) + 25;
			openList.set(i, Float.toString(tempclose));

			// high
			tempclose = (Float.parseFloat(highList.get(i)) - closeMin)
					* (float) 250.0 / (closeMax - closeMin) + 25;
			highList.set(i, Float.toString(tempclose));

			// low
			tempclose = (Float.parseFloat(lowList.get(i)) - closeMin)
					* (float) 250.0 / (closeMax - closeMin) + 25;
			lowList.set(i, Float.toString(tempclose));

		}

		// set indicator data
		List<String> techData = null;
		if (model.getTechChart().equalsIgnoreCase(Constants.INDICATOR_ROC)) {
			logger.info("setting data for type " + Constants.INDICATOR_ROC);
			techData = new ArrayList<String>();
			techData.add("");
			techData.add("");
			techData.add("");
			for (int i = 3; i < closeList.size(); i++) {
				techData.add(Float.toString((Float.parseFloat(closeList.get(i)) - Float
						.parseFloat(closeList.get(i - 3)))
						/ Float.parseFloat(closeList.get(i - 3))));
			}
		}

		model.setCloseMax(closeMax);
		model.setCloseMin(closeMin);
		model.setHighMax(highMax);
		model.setHighMin(highMin);
		model.setLowMax(lowMax);
		model.setLowMin(lowMin);
		model.setOpenMax(openMax);
		model.setOpenMin(openMin);
		model.setCloseList(closeList);
		model.setHighList(highList);
		model.setLowList(lowList);
		model.setOpenList(openList);
		model.setTechData(techData);
	}
}
