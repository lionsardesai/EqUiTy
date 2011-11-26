/**
 * 
 */
package com.lionsardesai.businesslogicimpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lionsardesai.beans.ChartListBean;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.businesslogic.ChartBusinessLogic;
import com.lionsardesai.common.Constants;
import com.lionsardesai.common.exceptions.UnClassifiedException;
import com.lionsardesai.connection.ConnectionFactory;
import com.lionsardesai.dbi.SelectRAWData;
import com.lionsardesai.dbi.YahooRecords;

/**
 * @author shardul
 * 
 */
public class ChartBusinessLogicImpl extends ChartBusinessLogic {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lionsardesai.businesslogic.ChartBusinessLogic#init()
	 */
	@Override
	public void init() throws UnClassifiedException {

		InputStream input = null;
		InputStreamReader in = null;
		try {
			ConnectionFactory temp = new ConnectionFactory();
			SqlMapClient con = temp.getConnection();
			YahooRecords dbi = new YahooRecords();
			Date dateInDB = dbi.getDate(con);
			String oldDate;
			if (dateInDB != null) {
				if (dateInDB.getTime() > new Date().getTime() - 900000000L) {
					return;
				}
				System.out.println("updating records");
				oldDate = new SimpleDateFormat("yyyy-MM-dd").format(dateInDB);
				System.out.println("records present till date : " + oldDate);
			} else {
				oldDate = "2011-10-01";
				System.out.println("no data in DB");
			}
			String[] oldDateSplit = oldDate.split("-");
			String oldDay = oldDateSplit[2];
			int tempmonth = Integer.parseInt(oldDateSplit[1]);
			tempmonth--;
			String oldMonth = Integer.toString(tempmonth);
			String oldYear = oldDateSplit[0];
			String[] newDateSplit = (new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date())).split("-");
			String newDay = newDateSplit[2];
			tempmonth = Integer.parseInt(newDateSplit[1]);
			tempmonth--;
			String newMonth = Integer.toString(tempmonth);
			String newYear = newDateSplit[0];

			for (String id : Constants.listAll) {
				URL u1 = new URL("http://ichart.finance.yahoo.com/table.csv?s="
						+ id // id of the stock to get
						+ "&a=" + oldDay + // old date
						"&b=" + oldMonth + // old month
						"&c=" + oldYear + // old year
						"&d=" + newDay + // new date
						"&e=" + newMonth + // new month
						"&f=" + newYear + // new year
						"&g=d&ignore=.csv");
				input = (InputStream) u1.getContent();
				in = new InputStreamReader(input);
				updateRecords(id, dbi, in, con);
				in.close();
				input.close();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			throw new UnClassifiedException();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			throw new UnClassifiedException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new UnClassifiedException();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				// TODO log close error
				throw new UnClassifiedException();
			}
		}
	}

	@Override
	public List<RAWDataBean> updateRecords(String id, YahooRecords dbi,
			InputStreamReader in, SqlMapClient con)
			throws UnClassifiedException {

		try {
			System.out.println("id to be queried " + id);
			List<String> data = dbi.getRecords(in);
			List<RAWDataBean> entities = new ArrayList<RAWDataBean>();
			for (String line : data) {
				// System.out.println(line);
				if (line.contains("Date")) {
					continue;
				}
				System.out.print("| ");
				entities.add(RAWDataBean.MakeRAWDataBean(line, id));
			}
			dbi.writeRecords(con, entities);
			return entities;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new UnClassifiedException();
		}
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
		// TODO Auto-generated method stub
		try {
			ConnectionFactory temp = new ConnectionFactory();
			SqlMapClient con = temp.getConnection();
			SelectRAWData dbi = new SelectRAWData();
			return dbi.getRecords(con, model.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
