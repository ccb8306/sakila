package sakila.query;

public class CustomerQuery {
	// 고객 전체 리스트
	public final static String SELECT_CUSTOMER_LIST = "SELECT cl.ID, cl.name, cl.phone, cl.address, cl.city, cl.country, cl.notes," + 
			" IF((SELECT COUNT(*) FROM rental WHERE return_date IS NULL AND customer_id = cl.ID) > 0,'Y','N') AS overdue FROM customer_list cl" + 
			" WHERE cl.SID=? ORDER BY cl.ID DESC LIMIT ?, ?";
	// 고객 전체 리스트 개수
	public final static String SELECT_CUSTOMER_LIST_COUNT = "SELECT COUNT(*) FROM customer_list WHERE SID=?";
	
	// 고객 전체 리스트 - 이름 검색
	public final static String SELECT_CUSTOMER_LIST_BY_NAME = "SELECT cl.ID, cl.name, cl.phone, cl.address, cl.city, cl.country, cl.notes," + 
			" IF((SELECT COUNT(*) FROM rental WHERE return_date IS NULL AND customer_id = cl.ID) > 0,'Y','N') AS overdue FROM customer_list cl" + 
			" WHERE cl.SID=? AND cl.name like ? ORDER BY cl.ID DESC LIMIT ?, ?";
	// 고객 전체 리스트 개수 - 이름 검색
	public final static String SELECT_CUSTOMER_LIST_COUNT_BY_NAME = "SELECT COUNT(*) FROM customer_list WHERE SID=? AND name like ?";
	
	// 고객 상세보기
	public final static String SELECT_CUSTOMER_ONE = "SELECT cl.ID, cl.SID,cl.name,cl.phone,cl.address,cl.city,cl.country,cl.notes,c.email,c.create_date FROM customer_list cl JOIN customer c ON cl.ID=c.customer_id WHERE cl.ID=?";
	
	// 고객 활동 상태 변경
	public final static String UPDATE_CUSTOMER_ACTIVE = "UPDATE customer SET active = ? WHERE customer_id = ?";
	
	// 도시, 국가 목록 
	public final static String SELECT_CITY_COUNTRY_LIST = "SELECT c.city_id, c.city, ct.country FROM city c JOIN country ct ON c.country_id = ct.country_id";
	
	// 고객의 주소 추가
	public final static String INSERT_ADDRESS = "INSERT INTO address(address, district, city_id, postal_code, phone) VALUES (?,?,?,?,?)";
	
	// 최신 키값 가져오기
	public final static String SELECT_LAST_INSERT_ID = "SELECT LAST_INSERT_ID() id";
	
	// 고객 정보 추가하기
	public final static String INSERT_CUSTOMER = "INSERT INTO customer(store_id, first_name, last_name, email, address_id, active, create_date) VALUES (?,?,?,?,?,1,now())";
	
}
