package sakila.query;

public class StaffQuery {
	/* 직원 */
	// 로그인
	public static final String SELECT_STAFF_BY_KEY = "SELECT * FROM staff WHERE email=? AND password=PASSWORD(?)";
	// 직원정보 상세보기
	public static final String SELECT_STAFF_ONE = "SELECT * FROM staff WHERE email=?";
	// 직원 정보 수정
	public static final String UPDATE_STAFF_INFO = "UPDATE FROM staff SET first_name=?, last_name=?, address_id=?, username=? WHERE email=?";
	// 직원 사진 수정
	public static final String UPDATE_STAFF_PIC = "UPDATE FROM staff SET picture=? WHERE email=?";
}
