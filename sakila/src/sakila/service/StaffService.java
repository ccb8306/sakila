package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;

import sakila.common.DBUtil;
import sakila.dao.StaffDao;
import sakila.vo.Staff;
import sakila.vo.StaffAndStaffList;

public class StaffService {
	// 로그인 
	public Staff getStaffByKey(Staff staff) {
		Staff returnStaff = null;
		StaffDao staffDao = null;
		Connection conn =  null;
		
		try {
			conn = DBUtil.getConnection(); // connection
			staffDao = new StaffDao();
			
			// 인증 확인 -> 결과 저장
			returnStaff = staffDao.selectStaffByKey(conn, staff);
			
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
		return returnStaff;
	}
	
	// 직원 정보 상세보기
	public StaffAndStaffList getStaffOne(Staff staff) {
		StaffAndStaffList sasl = null;
		StaffDao staffDao = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();			
			sasl = new StaffAndStaffList();
			staffDao = new StaffDao();
			
			sasl = staffDao.selectStaffOne(conn, staff);
			
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
		
		return sasl;
	}
}
