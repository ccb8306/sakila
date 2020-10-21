package sakila.query;

public class test {
	
	/* 영화 반납*/
	// 반납해야 할 리스트 - 페이징
	public static final String SELECT_RENTAL_LIST = "SELECT r.rental_id, f.title, r.rental_date, f.rental_duration, DATE_ADD(r.rental_date,INTERVAL f.rental_duration DAY) AS 반납예정일 " + 
			"FROM rental r JOIN inventory i ON r.inventory_id = i.inventory_id " + 
			"JOIN film f ON f.film_id = i.film_id WHERE r.return_date IS NULL LIMIT ?, ?";
	// 대여 아이디로 대여 리스트 검색 
	public static final String SELECT_RENTAL_LIST_BY_RENTAL_ID = "SELECT r.rental_id, f.title, r.rental_date, f.rental_duration, DATE_ADD(r.rental_date,INTERVAL f.rental_duration DAY) AS 반납예정일 " + 
			"FROM rental r JOIN inventory i ON r.inventory_id = i.inventory_id " + 
			"JOIN film f ON f.film_id = i.film_id WHERE r.return_date IS NULL ADN r.rental_id=?";
	// 영화 반납하기
	public static final String UPDATE_RETURN_FILM = "UPDATE FROM rental SET return_date=now() WHERE rental_id=?";
	
	/* 회원*/
	// 회원 리스트 - 페이징
	public static final String SELECT_CUSTOMER_LIST = "SELECT c.customer_id, c.first_name, c.last_name, c.email, a.address, c.active FROM customer c JOIN address a ON c.address_id=a.address_id LIMIT ?, ?"; 
	// 회원 검색 - 회원이름으로, 페이징
	public static final String SELECT_CUSTOMER_LIST_BY_NAME = "SELECT c.customer_id, c.first_name, c.last_name, c.email, a.address, c.active FROM customer c JOIN address a ON c.address_id=a.address_id LIMIT ?, ?"; 
	// 회원 검색 - 연체 없는 사람만
	public static final String SELECT_CUSTOMER_LIST_NOT_DELAY = "SELECT c.customer_id, c.first_name, c.last_name, c.email, a.address, c.active " + 
			"FROM customer c JOIN address a ON c.address_id=a.address_id " + 
			"JOIN rental r ON r.customer_id = c.customer_id WHERE r.rental_date IS NOT NULL"; 
	// 회원 검색 - 연체 있는 사람만
	public static final String SELECT_CUSTOMER_LIST_DELAY = "SELECT c.customer_id, c.first_name, c.last_name, c.email, a.address, c.active " + 
			"FROM customer c JOIN address a ON c.address_id=a.address_id " + 
			"JOIN rental r ON r.customer_id = c.customer_id WHERE r.rental_date IS NULL"; 
	// 신규 회원 등록
	public static final String INSERT_CUSTOMER = "INSERT INTO customer(store_id, first_name, last_name, email, address_id, create_date) VALUES(?,?,?,?,?,now())";
	// 회원 상세보기
	public static final String SELECT_CUSTOMER_ONE = "SELECT * FROM customer_list WHERE ID=?;";
	// 해당 회원의 연체 리스트
	public static final String SELECT_CUSTOMER_DELAY = "SELECT r.customer_id, r.rental_id, r.rental_date,  DATE_ADD(r.rental_date,INTERVAL f.rental_duration DAY) AS 반납예정일 " + 
			"FROM rental r JOIN inventory i ON r.inventory_id = i.inventory_id JOIN film f ON i.film_id = f.film_id WHERE r.customer_id = 200 AND r.return_date IS NULL";
	
}
