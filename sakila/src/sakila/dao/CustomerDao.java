package sakila.dao;

import java.sql.*;
import java.util.*;

import sakila.query.CustomerQuery;
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
}
