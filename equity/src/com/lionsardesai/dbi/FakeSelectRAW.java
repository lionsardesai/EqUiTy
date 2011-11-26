/**
 * 
 */
package com.lionsardesai.dbi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lionsardesai.beans.FakeBean;

/**
 * @author shardul
 * 
 */
public class FakeSelectRAW {

	public void selectDemo(SqlMapClient sqlMap) throws IOException,
			SQLException {
		// Output all contacts
		System.out.println("*------Test data collector--------*");
		// sqlMap.insert("storetest", new FakeBean());
		// FakeBean data = sqlMap.query ForObject("getopenclose");
		@SuppressWarnings("unchecked")
		List<FakeBean> list = sqlMap.queryForList("getopenclose");
		for (FakeBean data : list) {
			System.out.println("|Id  = " + data.getId());
			System.out.println("|open = " + data.getOpen());
			System.out.println("|close = " + data.getClose());
			System.out.println("==========================================");
		}
	}
}
