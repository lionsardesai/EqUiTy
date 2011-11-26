/**
 * 
 */
package com.lionsardesai.connection;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * @author shardul
 * 
 */
public class ConnectionFactory {

	public SqlMapClient getConnection() throws IOException, SQLException {
		Reader reader = Resources.getResourceAsReader("sqlMap-config-RAW.xml");
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return sqlMap;
	}
}
