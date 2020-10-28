package sakila.query;

public class StaffQuery {
	/* 직원 */
	// 로그인
	public static final String SELECT_STAFF_BY_KEY = "SELECT * FROM staff WHERE email=? AND password=PASSWORD(?)";
	// 직원정보 상세보기
	public static final String SELECT_STAFF_ONE = "SELECT s.staff_id, sl.name, s.picture, s.email, s.store_id, s.username, sl.address, sl.city, sl.country, sl.phone FROM staff s JOIN staff_list sl ON s.staff_id = sl.ID WHERE s.staff_id=?";
	
}
