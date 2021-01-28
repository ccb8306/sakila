package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.common.DBUtil;
import sakila.dao.SalesDao;
import sakila.dao.StaffDao;
import sakila.vo.Staff;

public class SalesService {

	// 매장 통계 가져오기 
	public Map<String, List<Map<String, Object>>> getSales(String manager) {
		Connection conn =  null;
		Map<String, List<Map<String, Object>>> map = null;
		List<Map<String, Object>> list = null;
		SalesDao salesDao = null;
		
		try {
			conn = DBUtil.getConnection(); // connection
			map = new HashMap<String, List<Map<String,Object>>>();
			list = new ArrayList<Map<String,Object>>();
			salesDao = new SalesDao();
			
			list = salesDao.selectSalesByStore(conn, manager);
			map.put("salesStore", list);
			list = salesDao.selectSalesByCategory(conn);
			map.put("salesCategory", list);
			
			conn.commit(); // commit
		} catch (Exception e) {
			try {
				conn.rollback(); // rollback
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close(); // close
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 인증 결과 반환
		return map;
	}
	
}
