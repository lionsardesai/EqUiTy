/**
 * 
 */
package com.lionsardesai.businesslogicimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.lionsardesai.beans.RAWDataBean;
import com.lionsardesai.beans.WelcomeListBean;
import com.lionsardesai.businesslogic.WelcomeBusinessLogic;
import com.lionsardesai.common.exceptions.UnClassifiedException;
import com.lionsardesai.connection.ConnectionFactory;
import com.lionsardesai.dbi.FakeSelectRAW;

/**
 * @author shardul
 * 
 */
public class FakeWelcomeBusinessLogicImpl extends WelcomeBusinessLogic {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lionsardesai.businesslogic.WelcomeBusinessLogic#init()
	 */
	@Override
	public void init(WelcomeListBean model) throws UnClassifiedException {
		// TODO Auto-generated method stub

		ConnectionFactory temp = new ConnectionFactory();
		try {
			FakeSelectRAW dbi = new FakeSelectRAW();
			dbi.selectDemo(temp.getConnection());

		} catch (IOException e) {
			// TODO Auto-generated catch blocks
			throw new UnClassifiedException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new UnClassifiedException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lionsardesai.businesslogic.WelcomeBusinessLogic#updateRecords()
	 */
	@Override
	public List<RAWDataBean> updateRecords() throws UnClassifiedException {
		return null;
		// TODO Auto-generated method stub

	}

}
