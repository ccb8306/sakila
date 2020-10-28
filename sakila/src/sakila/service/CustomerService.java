package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import sakila.common.DBUtil;
import sakila.dao.CustomerDao;
import sakila.vo.*;

public class CustomerService {
	// 고객 전체 리스트 불러오기
	public Map<String, Object> getCustomerList(Staff staff, int currentPage, int rowPage){
		Map<String, Object> map = null;
		List<CustomerList> list = null;
		Connection conn = null;
		CustomerDao customerDao = null;
		
		try {
			// 객체 생성
			customerDao = new CustomerDao();
			conn = DBUtil.getConnection();
			list = new ArrayList<CustomerList>();
			map = new HashMap<String, Object>();
			
			// 고객 리스트와 마지막 페이지 가져오기
			list = customerDao.selectCustomerList(conn, staff, currentPage, rowPage);
			int endPage = customerDao.selectCustomerEndPage(conn, staff, rowPage);
			
			// 맵에 추가
			map.put("list", list);
			map.put("endPage", endPage);
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
} 
