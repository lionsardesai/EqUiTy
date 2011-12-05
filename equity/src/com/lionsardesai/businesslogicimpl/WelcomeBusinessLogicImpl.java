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

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.businesslogic.WelcomeBusinessLogic;
import com.lionsardesai.common.Constants;
import com.lionsardesai.common.exceptions.UnClassifiedException;
import com.lionsardesai.connection.ConnectionFactory;
import com.lionsardesai.dbi.YahooRecords;

/**
 * @author shardul
 * 
 */
public class WelcomeBusinessLogicImpl extends WelcomeBusinessLogic {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lionsardesai.businesslogic.WelcomeBusinessLogic#init()
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
				if (dateInDB.getTime() > new Date().getTime() - 216000000L) {
					return;
				}
				logger.info("updating records");
				oldDate = new SimpleDateFormat("yyyy-MM-dd").format(dateInDB);
				logger.info("records present till date : " + oldDate);
			} else {
				oldDate = "2011-10-01";
				logger.info("no data in DB");
			}
			String[] oldDateSplit = oldDate.split("-");
			String oldDay = oldDateSplit[2];
			int tempmonth = Integer.parseInt(oldDateSplit[1]);
			tempmonth--;
			String oldMonth = String.format("%02d", tempmonth);
			String oldYear = oldDateSplit[0];
			String[] newDateSplit = (new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date())).split("-");
			String newDay = newDateSplit[2];
			tempmonth = Integer.parseInt(newDateSplit[1]);
			tempmonth--;
			String newMonth = String.format("%02d", tempmonth);
			String newYear = newDateSplit[0];

			// logging dates to query
			logger.debug("old date in yyyymmdd format : " + oldYear + oldMonth
					+ oldDay);
			logger.debug("new date in yyyymmdd format : " + newYear + newMonth
					+ newDay);

			for (String id : Constants.listAll) {
				// logger.info("yahoo keeps changing formats but mostly maintains the number of arguments and their structure");
				// logger.info("check yahoo site manually if IOException is being caught");
				URL u1 = new URL("http://ichart.finance.yahoo.com/table.csv?s="
						+ id // id of the stock to get
						+ "&a=" + oldMonth + // old date
						"&b=" + oldDay + // old month
						"&c=" + oldYear + // old year
						"&d=" + newMonth + // new date
						"&e=" + newDay + // new month
						"&f=" + newYear + // new year
						"&g=d&ignore=.csv");
				logger.debug("query url : " + u1.getQuery());
				input = (InputStream) u1.getContent();
				in = new InputStreamReader(input);
				updateRecords(id, dbi, in, con);
				in.close();
				input.close();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error("MalformedURLException occured");
			throw new UnClassifiedException();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error("IOException occured");
			throw new UnClassifiedException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQLException occured");
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
				logger.error("IOException occured");
				throw new UnClassifiedException();
			}
		}
	}

	@Override
	public List<RAWDataBean> updateRecords(String id, YahooRecords dbi,
			InputStreamReader in, SqlMapClient con)
			throws UnClassifiedException {

		try {
			logger.debug("id to be queried " + id);
			List<String> data = dbi.getRecords(in);
			List<RAWDataBean> entities = new ArrayList<RAWDataBean>();
			for (String line : data) {
				// System.out.println(line);
				if (line.contains("Date")) {
					continue;
				}
				entities.add(RAWDataBean.MakeRAWDataBean(line, id));
			}
			dbi.writeRecords(con, entities);
			return entities;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("ParseException occured");
			throw new UnClassifiedException();
		}
	}

}
