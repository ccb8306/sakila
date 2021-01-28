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
	// 대여중인 전체 리스트와 최대 페이지 값을 구헤옴 - 비디오 제목 검색
	public Map<String, Object> getRentalListByFilmTitle(int storeId, String filmTitle, int currentPage, int rowPage){
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
			list = rentalDao.selectRentalListByFilmTitle(conn, storeId, filmTitle, currentPage, rowPage);
			// 최대 페이지 구하기
			endPage = rentalDao.selectRentalListByFilmTitleEndPage(conn, storeId, filmTitle, rowPage);
			
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
	// 대여 비디오 반납
	public void returnRentalFilm(int rentalId) {
		Connection conn = null;
		RentalDao rentalDao = null;
		try {
			conn = DBUtil.getConnection();
			rentalDao = new RentalDao();
			rentalDao.updateRentalReturnDate(conn, rentalId);
			
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
	}
	

	// 비디오 대여하기
	public void addRental(Rental rental, Payment payment) {
		Connection conn = null;
		RentalDao rentalDao = null;
		try {
			conn = DBUtil.getConnection();
			rentalDao = new RentalDao();
			rentalDao.insertRental(conn, rental);
			int lastId = rentalDao.selectLastInsertId(conn);
			payment.setRental_id(lastId);
			payment.setAmount(rentalDao.selectRentalRateByRentalId(conn, lastId));
			rentalDao.insertPayment(conn, payment);
			
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
		
	}
	
}
