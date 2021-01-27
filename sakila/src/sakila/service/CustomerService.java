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
	// 고객 리스트 불러오기 - 이름 검색
	public Map<String, Object> getCustomerListByName(Staff staff, String name, int currentPage, int rowPage){
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
			list = customerDao.selectCustomerListByName(conn, staff, name, currentPage, rowPage);
			int endPage = customerDao.selectCustomerEndPageByName(conn, staff, name, rowPage);
			
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
	// 고객 상세보기(고객 정보)
	public Map<String, Object> getCustomerOne(int customerId){
		Map<String, Object> map = null;
		Connection conn = null;
		CustomerDao customerDao = null;
		CustomerAndCustomerList cacl = null;
		
		try {
			map = new HashMap<String, Object>();
			customerDao = new CustomerDao();
			conn = DBUtil.getConnection();
			cacl = new CustomerAndCustomerList();
			
			cacl = customerDao.selectCustomerOne(conn, customerId);
			
			map.put("cacl", cacl);
			
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
	
	// 고객 활동 상태 변경
	public void modifyCustomerActive(int customerId, int active) {
		Connection conn = null;
		CustomerDao customerDao = null;
		
		try {
			customerDao = new CustomerDao();
			conn = DBUtil.getConnection();
			customerDao.updateCustomerActive(conn, customerId, active);
			
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
	// 도시,국가 리스트 가져오기
	public List<CityAndCountry> getCityAndCountryList(){
		Connection conn = null;
		CustomerDao customerDao = null;
		List<CityAndCountry> list = null;
		
		try {
			list = new ArrayList<CityAndCountry>();
			customerDao = new CustomerDao();
			conn = DBUtil.getConnection();
			
			list = customerDao.selectCityAndCountryList(conn);
			
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

	// 고객의 주소 등록 후 주소 키값 가져오기
	public int addAddress(Address address) {
		Connection conn = null;
		CustomerDao customerDao = null;
		int lastId = 0;
		try {
			customerDao = new CustomerDao();
			conn = DBUtil.getConnection();
			customerDao.insertAddress(conn, address);
			lastId = customerDao.selectLastInsertId(conn);
			
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
		
		return lastId;
	}
	// 고객 추가하기
	public void addCustomer (Customer customer) {
		Connection conn = null;
		CustomerDao customerDao = null;
		
		try {
			customerDao = new CustomerDao();
			conn = DBUtil.getConnection();
			customerDao.insertCustomer(conn, customer);
			
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
