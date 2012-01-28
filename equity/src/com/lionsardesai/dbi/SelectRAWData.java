/**
 * 
 */
package com.lionsardesai.dbi;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.common.Constants;

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

	/**
	 * @param con
	 * @return
	 */
	public Map<String, List<RAWDataBean>> getPredictorData(SqlMapClient con) {
		Map<String, List<RAWDataBean>> predictorMap = new HashMap<String, List<RAWDataBean>>();
		try {
			for (String id : Constants.listAll) {
				@SuppressWarnings("unchecked")
				List<RAWDataBean> list = con.queryForList("getPredictorData",
						id);
				predictorMap.put(id, list);
			}
			return predictorMap;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
