/**
 * 
 */
package com.lionsardesai.dbi;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lionsardesai.beans.RAWDataBean;

/**
 * @author shardul
 * 
 */
public class SelectRAWData implements BaseDBI {

	/**
	 * @param con
	 * @return
	 */
	public List<RAWDataBean> getRecords(SqlMapClient con, String id) {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("unchecked")
			List<RAWDataBean> list = con.queryForList("getRecords", id);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
