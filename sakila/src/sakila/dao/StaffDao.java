package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sakila.query.StaffQuery;
import sakila.vo.Staff;
import sakila.vo.StaffAndStaffList;
import sakila.vo.StaffList;

public class StaffDao {
	// 직원 인증(로그인)
	public Staff selectStaffByKey(Connection conn, Staff staff) throws Exception {
		Staff returnStaff = null;
		PreparedStatement stmt = conn.prepareStatement(StaffQuery.SELECT_STAFF_BY_KEY);
		stmt.setString(1, staff.getEmail());
		stmt.setString(2, staff.getPassword());
		
		System.out.println(stmt + "<--selectStaffByKey stmt");
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			returnStaff = new Staff();
			returnStaff.setStaffId(rs.getInt("staff_id"));
			returnStaff.setEmail(rs.getString("email"));
			returnStaff.setFirstName(rs.getString("first_name"));
			returnStaff.setLastName(rs.getString("last_name"));
			returnStaff.setStoreId(rs.getInt("store_id"));
			returnStaff.setPicture(rs.getString("picture"));
			returnStaff.setUsername(rs.getString("username"));
		}
		
		return returnStaff;
	}
	
	// 직원 정보 상세보기
	public StaffAndStaffList selectStaffOne(Connection conn, Staff staff)throws Exception{
		StaffAndStaffList sasl = null;
		PreparedStatement stmt = conn.prepareStatement(StaffQuery.SELECT_STAFF_ONE);
		stmt.setInt(1, staff.getStaffId());
		
		System.out.println(stmt + "<--selectStaffOne stmt");
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
		    sasl = new StaffAndStaffList();
			Staff s = new Staff();
			StaffList sl = new StaffList();
			s.setStaffId(rs.getInt("s.staff_id"));
			sl.setName(rs.getString("sl.name"));
			s.setEmail(rs.getString("s.email"));
			s.setStoreId(rs.getInt("s.store_id"));
			s.setPicture(rs.getString("s.picture"));
			s.setUsername(rs.getString("s.username"));
			sl.setAddress(rs.getString("sl.address"));
			sl.setCity(rs.getString("sl.city"));
			sl.setCountry(rs.getString("sl.country"));
			sl.setPhone(rs.getString("sl.phone"));
			
			sasl.setStaff(s);
			sasl.setStaffList(sl);
		}
		return sasl;
	}
}
