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
import com.lionsardesai.dbi.ScavengerForBloombergTicker;
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
	public void evaluate(ChartListBean model) throws NullPointerException {
		List<RAWDataBean> newData = model.getNewData();
		float closeMax = Float.parseFloat(newData.get(0).close);
		float closeMin = Float.parseFloat(newData.get(0).close);
		float openMax = Float.parseFloat(newData.get(0).open);
		float openMin = Float.parseFloat(newData.get(0).open);
		float highMax = Float.parseFloat(newData.get(0).high);
		float highMin = Float.parseFloat(newData.get(0).high);
		float lowMax = Float.parseFloat(newData.get(0).low);
		float lowMin = Float.parseFloat(newData.get(0).low);
		float volumeMax = Float.parseFloat(newData.get(0).volume);
		float volumeMin = Float.parseFloat(newData.get(0).volume);

		List<String> closeList = new ArrayList<String>();
		List<String> openList = new ArrayList<String>();
		List<String> highList = new ArrayList<String>();
		List<String> lowList = new ArrayList<String>();
		List<String> volumeList = new ArrayList<String>();

		logger.info("starting assignment of extremes newdata size is : "
				+ newData.size());
		for (int i = 0; i < newData.size(); i++) {
			closeList.add(newData.get(i).close);
			openList.add(newData.get(i).open);
			highList.add(newData.get(i).high);
			lowList.add(newData.get(i).low);
			volumeList.add(newData.get(i).volume);

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
			// assign volumeMax
			if (volumeMax < Float.parseFloat(newData.get(i).volume)) {
				volumeMax = Float.parseFloat(newData.get(i).volume);
			}
			// assign volumeMin
			if (volumeMin > Float.parseFloat(newData.get(i).volume)) {
				volumeMin = Float.parseFloat(newData.get(i).volume);
			}

		}

		// scaling
		logger.info("starting scaling");
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
			tempclose = (Float.parseFloat(volumeList.get(i)) - closeMin)
					* (float) 250.0 / (closeMax - closeMin) + 25;
			lowList.set(i, Float.toString(tempclose));

			// volume
			tempclose = Float.parseFloat(volumeList.get(i)) * (float) 100.0
					/ (volumeMax);
			volumeList.set(i, Float.toString(tempclose));

		}

		// set indicator data
		List<String> techData = null;
		logger.info("starting techdata calculations ");
		// roc 12 days
		if (model.getTechChart() != null
				&& model.getTechChart().equalsIgnoreCase(
						Constants.INDICATOR_ROC)) {
			logger.info("setting data for type " + Constants.INDICATOR_ROC);
			techData = new ArrayList<String>();
			techData.add("");
			techData.add("");
			techData.add("");
			for (int i = 12; i < closeList.size(); i++) {
				techData.add(Float.toString((Float.parseFloat(closeList.get(i)) - Float
						.parseFloat(closeList.get(i - 12)))
						/ Float.parseFloat(closeList.get(i - 12))));
			}
		}

		float prevEMA = 0;
		float sma = 0;
		float multiplier = 0;
		int noOfDays = 10;
		float EMA = 0;
		// ema 10 days
		if (model.getTechChart() != null
				&& model.getTechChart().equalsIgnoreCase(
						Constants.INDICATOR_EMA)) {
			logger.info("setting data for type " + Constants.INDICATOR_EMA);
			techData = new ArrayList<String>();
			techData.add("");
			techData.add("");
			techData.add("");
			for (int i = 0; i < closeList.size(); i++) {
				if (i < 10) {
					sma = sma + Float.parseFloat(closeList.get(i));
					techData.add("");
					if (i == 9) {
						prevEMA = sma / 10;
					}
				} else {
					multiplier = 2 / (noOfDays + 1);
					EMA = (Float.parseFloat(closeList.get(i)) - prevEMA)
							* multiplier + prevEMA;
					techData.add(Float.toString(EMA));
				}
			}
		}
		float typicalPrice = 0;
		float prevTypicalPrice = 0;
		float rawMoneyFlow = 0;
		float positiveMoneyFlow = 0;
		float negativeMoneyFlow = 0;
		float MFI = 0;
		float moneyFlowRatio = 0;
		// mfi 14 days
		if (model.getTechChart() != null
				&& model.getTechChart().equalsIgnoreCase(
						Constants.INDICATOR_MFI)) {
			logger.info("setting data for type " + Constants.INDICATOR_MFI);
			techData = new ArrayList<String>();
			// techData.add("");
			// techData.add("");
			// techData.add("");
			for (int i = 1; i < closeList.size(); i++) {
				if (i < 15) {
					techData.add("");
				} else {
					for (int j = i - 14; j <= i; j++) {
						typicalPrice = Float.parseFloat(highList.get(j))
								+ Float.parseFloat(lowList.get(j))
								+ Float.parseFloat(closeList.get(j));
						if (j != 1) {
							rawMoneyFlow = Float.parseFloat(volumeList.get(j))
									* typicalPrice;
							if (typicalPrice > prevTypicalPrice) {
								positiveMoneyFlow = positiveMoneyFlow
										+ rawMoneyFlow;
							} else {
								negativeMoneyFlow = negativeMoneyFlow
										+ rawMoneyFlow;
							}
						}
						prevTypicalPrice = typicalPrice;
					}
					moneyFlowRatio = positiveMoneyFlow / negativeMoneyFlow;
					MFI = 100 - 100 / (moneyFlowRatio + 1);
					techData.add(Float.toString(MFI));
				}
			}
		}

		// macd 22 days
		if (model.getTechChart() != null
				&& model.getTechChart().equalsIgnoreCase(
						Constants.INDICATOR_MACD)) {
			logger.info("setting data for type " + Constants.INDICATOR_MACD);
			techData = new ArrayList<String>();
			techData.add("");
			techData.add("");
			techData.add("");
			for (int i = 22; i < closeList.size(); i++) {
				techData.add(Float.toString((Float.parseFloat(closeList.get(i)) - Float
						.parseFloat(closeList.get(i - 22)))
						/ Float.parseFloat(closeList.get(i - 22))));
			}
		}

		float prevADL = 0;
		float ADL = 0;
		float moneyFlowMultiplier = 0;
		float moneyFlowVolume = 0;
		// ADL everyday
		if (model.getTechChart() != null
				&& model.getTechChart().equalsIgnoreCase(
						Constants.INDICATOR_ADL)) {
			logger.info("setting data for type " + Constants.INDICATOR_ADL);
			techData = new ArrayList<String>();
			for (int i = 0; i < closeList.size(); i++) {
				moneyFlowMultiplier = ((Float.parseFloat(closeList.get(i)) - Float
						.parseFloat(lowList.get(i))) - (Float
						.parseFloat(highList.get(i)) - Float
						.parseFloat(closeList.get(i))))
						/ (Float.parseFloat(highList.get(i)) - Float
								.parseFloat(lowList.get(i)));
				moneyFlowVolume = moneyFlowMultiplier
						* Float.parseFloat(volumeList.get(i));
				ADL = prevADL + moneyFlowVolume;
				prevADL = ADL;
				techData.add(Float.toString(ADL));
			}
		}

		float averageGain = 0;
		float averageLoss = 0;
		float RS = 0;
		float RSI = 0;
		float prevAverageGain = 0;
		float prevAverageLoss = 0;
		float gainOrLoss = 0;
		float gain = 0;
		float loss = 0;
		// RSI 14 days
		if (model.getTechChart() != null
				&& model.getTechChart().equalsIgnoreCase(
						Constants.INDICATOR_RSI)) {
			logger.info("setting data for type " + Constants.INDICATOR_RSI);
			techData = new ArrayList<String>();
			// techData.add("");
			// techData.add("");
			// techData.add("");
			for (int i = 1; i < closeList.size(); i++) {
				if (i < 15) {
					techData.add("");
				} else {
					if (i == 15) {
						for (int j = 2; j <= 15; j++) {
							gainOrLoss = Float.parseFloat(closeList.get(j))
									- Float.parseFloat(closeList.get(j - 1));
							if ((gainOrLoss) > 0) {
								averageGain = averageGain + gainOrLoss;
							} else {
								averageLoss = averageLoss - gainOrLoss;
							}
						}
						averageGain = averageGain / 14;
						averageLoss = averageLoss / 14;
						prevAverageGain = averageGain;
						prevAverageLoss = averageLoss;
						// closeList(i)
					} else {
						gainOrLoss = Float.parseFloat(closeList.get(i))
								- Float.parseFloat(closeList.get(i - 1));
						if ((gainOrLoss) > 0) {
							gain = gainOrLoss;
							loss = 0;
						} else {
							gain = 0;
							loss = gainOrLoss;
						}
						averageGain = (prevAverageGain * 13 + gain) / 14;
						averageLoss = (prevAverageLoss * 13 - loss) / 14;
						prevAverageGain = averageGain;
						prevAverageLoss = averageLoss;
					}
					RS = averageGain / averageLoss;
					RSI = 100 - 100 / (1 + RS);
					techData.add(Float.toString(RSI));
				}
			}
		}

		float stoc = 0;
		Float lowLow = null;
		Float highHigh = null;
		// STOC 14 days
		if (model.getTechChart() != null
				&& model.getTechChart().equalsIgnoreCase(
						Constants.INDICATOR_STOC)) {
			logger.info("setting data for type " + Constants.INDICATOR_STOC);
			techData = new ArrayList<String>();
			for (int i = 0; i < closeList.size(); i++) {
				if (i < 13) {
					techData.add("");
				} else {
					for (int j = i - 13; j < i; j++) {
						if (lowLow == null) {
							lowLow = Float.parseFloat(lowList.get(j));
						} else if (lowLow > Float.parseFloat(lowList.get(j))) {
							lowLow = Float.parseFloat(lowList.get(j));
						}
						if (highHigh == null) {
							highHigh = Float.parseFloat(highList.get(j));
						} else if (highHigh < Float.parseFloat(highList.get(j))) {
							highHigh = Float.parseFloat(highList.get(j));
						}
					}
					stoc = (Float.parseFloat(closeList.get(i)) - lowLow)
							/ (highHigh - lowLow) * 100;
					techData.add(Float.toString(stoc));
				}
			}
		}

		// set all calculated values
		model.setCloseMax(closeMax);
		model.setCloseMin(closeMin);
		model.setHighMax(highMax);
		model.setHighMin(highMin);
		model.setLowMax(lowMax);
		model.setLowMin(lowMin);
		model.setOpenMax(openMax);
		model.setOpenMin(openMin);
		model.setVolumeMax(volumeMax);
		model.setVolumeMin(volumeMin);
		model.setCloseList(closeList);
		model.setHighList(highList);
		model.setLowList(lowList);
		model.setOpenList(openList);
		model.setTechData(techData);
		model.setVolumeList(volumeList);
	}

	@Override
	public String getTicker() {
		String ret = ScavengerForBloombergTicker.scavenge();
		ret += " </div>";
		return ret;
	}
}
