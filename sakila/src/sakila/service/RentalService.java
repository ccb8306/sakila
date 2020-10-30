package sakila.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.common.DBUtil;
import sakila.dao.RentalDao;
import sakila.query.RentalQuery;
import sakila.vo.*;

public class RentalService {
	// 대여중인 전체 리스트와 최대 페이지 값을 구헤옴
	public Map<String, Object> getRentalList(int storeId, int currentPage, int rowPage){
		Map<String, Object> map = null;
		int endPage = 1; //최대 페이지
		List<RentalAndFilm> list = null; // 대여 리스트
		Connection conn = null;
		RentalDao rentalDao = null;
		
		try {
			map = new HashMap<String, Object>();
			list = new ArrayList<RentalAndFilm>();
			rentalDao = new RentalDao();
			conn = DBUtil.getConnection();
			
			// 대여 리스트 불러 오기
			list = rentalDao.selectRentalList(conn, storeId, currentPage, rowPage);
			// 최대 페이지 구하기
			endPage = rentalDao.selectRentalListEndPage(conn, storeId, rowPage);
			
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
	
	// 한 고객의 대여 리스트 불러오기
	public List<RentalAndFilm> getCustomerRentalList(int customerId){
		List<RentalAndFilm> list = null;
		Connection conn = null;
		RentalDao rentalDao = null;
		
		try {
			list = new ArrayList<RentalAndFilm>();
			rentalDao = new RentalDao();
			conn = DBUtil.getConnection();
			System.out.println(customerId + "<--getCustomerRentalList customerId");
			// 대여 리스트 불러 오기
			list = rentalDao.selectCustomerRentalList(conn, customerId);
			
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
		
		return list;
	}
	// 한 고객의 연체 리스트 불러오기
	public List<RentalAndFilm> getCustomerOverDueList(int customerId){
		List<RentalAndFilm> list = null;
		Connection conn = null;
		RentalDao rentalDao = null;
		
		try {
			list = new ArrayList<RentalAndFilm>();
			rentalDao = new RentalDao();
			conn = DBUtil.getConnection();
			System.out.println(customerId + "<--getCustomerOverDueList customerId");
			// 연체 리스트 불러 오기
			list = rentalDao.selectCustomerOverDueList(conn, customerId);
			
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
		
		return list;
	}
	
}
