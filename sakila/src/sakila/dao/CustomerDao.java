package sakila.dao;

import java.sql.*;
import java.util.*;

import sakila.query.CustomerQuery;
import sakila.query.FilmQuery;
import sakila.vo.*;

public class CustomerDao {
	// 고객 전체 리스트 불러오기
	public List<CustomerList> selectCustomerList(Connection conn, Staff staff, int currentPage, int rowPage) throws Exception{
		List<CustomerList> list = new ArrayList<CustomerList>();
		CustomerList cl = null;
		
		PreparedStatement stmt = conn.prepareStatement(CustomerQuery.SELECT_CUSTOMER_LIST);
		stmt.setInt(1, staff.getStoreId());
		stmt.setInt(2, (int)(currentPage -1) * rowPage);
		stmt.setInt(3, rowPage);
		System.out.println(stmt + "<--selectCustomerList stmt");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			cl = new CustomerList();
			cl.setId(rs.getInt("cl.ID"));
			cl.setName(rs.getString("cl.name"));
			cl.setAddress(rs.getString("cl.address"));
			cl.setPhone(rs.getString("cl.phone"));
			cl.setCity(rs.getString("cl.city"));
			cl.setCountry(rs.getString("cl.country"));
			cl.setNotes(rs.getString("cl.notes"));
			cl.setOverdue(rs.getString("overdue"));
			
			list.add(cl);
		}
		return list;
	}
	
	// 고객 전체 리스트 마지막 페이지 구하기
	public int selectCustomerEndPage(Connection conn, Staff staff, int rowPage)throws Exception {
		int endPage = 0;
		
		PreparedStatement stmt = conn.prepareStatement(CustomerQuery.SELECT_CUSTOMER_LIST_COUNT);
		stmt.setInt(1, staff.getStoreId());
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(stmt + "<--selectCustomerEndPage stmt");
		
		if(rs.next()) {
			endPage = rs.getInt("COUNT(*)");
			if(endPage%rowPage == 0)			
				endPage = (int)(endPage/rowPage);
			else
				endPage = (int)(endPage/rowPage) + 1;
		}
		return endPage;
	}
	// 고객 전체 리스트 불러오기 - 이름 검색
	public List<CustomerList> selectCustomerListByName(Connection conn, Staff staff, String name, int currentPage, int rowPage) throws Exception{
		List<CustomerList> list = new ArrayList<CustomerList>();
		CustomerList cl = null;
		
		PreparedStatement stmt = conn.prepareStatement(CustomerQuery.SELECT_CUSTOMER_LIST_BY_NAME);
		stmt.setInt(1, staff.getStoreId());
		stmt.setString(2, "%" + name + "%");
		stmt.setInt(3, (int)(currentPage -1) * rowPage);
		stmt.setInt(4, rowPage);
		System.out.println(stmt + "<--selectCustomerList stmt");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			cl = new CustomerList();
			cl.setId(rs.getInt("cl.ID"));
			cl.setName(rs.getString("cl.name"));
			cl.setAddress(rs.getString("cl.address"));
			cl.setPhone(rs.getString("cl.phone"));
			cl.setCity(rs.getString("cl.city"));
			cl.setCountry(rs.getString("cl.country"));
			cl.setNotes(rs.getString("cl.notes"));
			cl.setOverdue(rs.getString("overdue"));
			
			list.add(cl);
		}
		return list;
	}
	
	// 고객 전체 리스트 마지막 페이지 구하기 - 이름 검색
	public int selectCustomerEndPageByName(Connection conn, Staff staff, String name, int rowPage)throws Exception {
		int endPage = 0;
		
		PreparedStatement stmt = conn.prepareStatement(CustomerQuery.SELECT_CUSTOMER_LIST_COUNT_BY_NAME);
		stmt.setInt(1, staff.getStoreId());
		stmt.setString(2, "%" + name + "%");
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(stmt + "<--selectCustomerEndPage stmt");
		
		if(rs.next()) {
			endPage = rs.getInt("COUNT(*)");
			if(endPage%rowPage == 0)			
				endPage = (int)(endPage/rowPage);
			else
				endPage = (int)(endPage/rowPage) + 1;
		}
		return endPage;
	}
	
	// 고객 정보 상세보기
	public CustomerAndCustomerList selectCustomerOne(Connection conn, int customerId)throws Exception {
		CustomerAndCustomerList cacl = null;
		
		PreparedStatement stmt = conn.prepareStatement(CustomerQuery.SELECT_CUSTOMER_ONE);
		stmt.setInt(1, customerId);
		System.out.println(stmt + "<--selectCustomerOne stmt");
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			cacl = new CustomerAndCustomerList();
			Customer c = new Customer();
			CustomerList cl = new CustomerList();

			cl.setId(rs.getInt("cl.ID"));
			cl.setSid(rs.getInt("cl.SID"));
			cl.setName(rs.getString("cl.name"));
			cl.setPhone(rs.getString("cl.phone"));
			cl.setAddress(rs.getString("cl.address"));
			cl.setCity(rs.getString("cl.city"));
			cl.setCountry(rs.getString("cl.country"));
			cl.setNotes(rs.getString("cl.notes"));
			c.setEmail(rs.getString("c.email"));
			
			cacl.setCustomer(c);
			cacl.setCustomerList(cl);
		}
		
		return cacl;
	}
	// 고객 활동 상태 변경
	public void updateCustomerActive (Connection conn, int customerId, int active)throws Exception {		
		PreparedStatement stmt = conn.prepareStatement(CustomerQuery.UPDATE_CUSTOMER_ACTIVE);
		stmt.setInt(1, active);
		stmt.setInt(2, customerId);
		System.out.println(stmt + "<--updateCustomerActive stmt");
		
		stmt.executeUpdate();		
	}
	// 도시,국가 리스트 가져오기
	public List<CityAndCountry> selectCityAndCountryList (Connection conn)throws Exception {		
		List<CityAndCountry> list = new ArrayList<CityAndCountry>();
		CityAndCountry cac = null;
		PreparedStatement stmt = conn.prepareStatement(CustomerQuery.SELECT_CITY_COUNTRY_LIST);
		System.out.println(stmt + "<--selectCityAndCountryList stmt");
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			cac = new CityAndCountry();

			cac.setCityId(rs.getInt("c.city_id"));
			cac.setCity(rs.getString("c.city"));
			cac.setCountry(rs.getString("ct.country"));
			
			list.add(cac);
		}
		
		return list;	
	}
	// 고객의 주소 추가하기
	public void insertAddress (Connection conn, Address address)throws Exception {		
		PreparedStatement stmt = conn.prepareStatement(CustomerQuery.INSERT_ADDRESS);
		stmt.setString(1, address.getAddress());
		stmt.setString(2, address.getDistrict());
		stmt.setInt(3, address.getCityId());
		stmt.setString(4, address.getPostalCode());
		stmt.setString(5, address.getPhone());
		System.out.println(stmt + "<--insertAddress stmt");
		
		stmt.executeUpdate();
	}
	// 마지막 키값 가져오기
	public int selectLastInsertId(Connection conn) throws Exception {
		PreparedStatement stmt = conn.prepareStatement(CustomerQuery.SELECT_LAST_INSERT_ID);
		int lastId = 0;
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			lastId = rs.getInt("id");
		}
		System.out.println(lastId + "<--lastId");
		
		return lastId;
	}
	// 고객 추가하기
	public void insertCustomer (Connection conn, Customer customer)throws Exception {		
		PreparedStatement stmt = conn.prepareStatement(CustomerQuery.INSERT_CUSTOMER);
		stmt.setInt(1, customer.getStoreId());
		stmt.setString(2, customer.getFirstName());
		stmt.setString(3, customer.getLastName());
		stmt.setString(4, customer.getEmail());
		stmt.setInt(5, customer.getAddressId());
		System.out.println(stmt + "<--insertCustomer stmt");
		
		stmt.executeUpdate();
	}
}
