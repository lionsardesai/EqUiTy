/**
 * 
 */
package com.lionsardesai.dbi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.common.exceptions.UnClassifiedException;

/**
 * @author shardul
 * 
 */
public class YahooRecords {

	/** byte mark for end line on yahoo */
	private static int END_OF_LINE = 10;

	public Date getDate(SqlMapClient map) {
		Date temp = null;

		try {
			temp = (Date) map.queryForObject("uptodate");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	public List<String> getRecords(InputStreamReader in)
			throws UnClassifiedException {

		int buffer = 0;
		StringBuffer buff = new StringBuffer("");
		List<String> ret = new ArrayList<String>();

		try {
			while ((buffer = in.read()) != -1) {
				buff.append((char) buffer);
				if (buffer == END_OF_LINE) {
					ret.add(buff.substring(0, buff.length() - 1).toString());
					buff.setLength(0);
				}
			}
			return ret;
		} catch (IOException e) {
			throw new UnClassifiedException();
		}

	}

	public int writeRecords(SqlMapClient map, List<RAWDataBean> data)
			throws UnClassifiedException {

		try {
			for (RAWDataBean entity : data) {
				map.insert("writeRecords", entity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
