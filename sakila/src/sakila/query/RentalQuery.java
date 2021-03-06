package sakila.query;

public class RentalQuery {
	// 대여 중인 영화 목록 출력
	public final static String SELECT_RENTAL_LIST = "SELECT r.rental_id, f.title, cl.ID, cl.name, r.rental_date, f.rental_duration, DATE_ADD(r.rental_date,INTERVAL f.rental_duration DAY) AS return_due_date" + 
			" FROM rental r JOIN inventory i ON r.inventory_id = i.inventory_id" + 
			" JOIN film f ON f.film_id = i.film_id JOIN customer_list cl ON cl.ID = r.customer_id WHERE i.store_id=? AND  r.return_date IS NULL ORDER BY r.rental_date DESC LIMIT ?, ?";
	// 대여 중인 영화 목록 최대 페이지 구하기 위한 쿼리
	public final static String SELECT_RENTAL_LIST_COUNT= "SELECT COUNT(*) FROM rental r JOIN inventory i ON r.inventory_id = i.inventory_id" + 
			" JOIN film f ON f.film_id = i.film_id JOIN customer_list cl ON cl.ID = r.customer_id WHERE i.store_id=? AND  r.return_date IS NULL";
	
	// 대여 중인 영화 목록 출력 - 대여 아이디로 검색
	public final static String SELECT_RENTAL_LIST_BY_FilmTitle = "SELECT r.rental_id, cl.ID, cl.name, f.title, r.rental_date, f.rental_duration, DATE_ADD(r.rental_date,INTERVAL f.rental_duration DAY) AS return_due_date FROM rental r" + 
			" JOIN inventory i ON r.inventory_id = i.inventory_id JOIN  film f ON f.film_id = i.film_id JOIN customer_list cl ON cl.ID = r.customer_id WHERE r.return_date IS NULL AND i.store_id=? AND (f.title LIKE ? OR r.rental_id = ? OR cl.name LIKE ?) ORDER BY r.rental_date DESC LIMIT ?, ?"; 

	// 대여 중인 영화 목록 개수 - 대여 아이디로 검색
	public final static String SELECT_RENTAL_LIST_BY_FilmTitle_COUNT = "SELECT COUNT(*) FROM rental r" + 
			" JOIN inventory i ON r.inventory_id = i.inventory_id JOIN film f ON f.film_id = i.film_id JOIN customer_list cl ON cl.ID = r.customer_id WHERE r.return_date IS NULL AND i.store_id=? AND (f.title LIKE ? OR r.rental_id = ? OR cl.name LIKE ?)"; 
	
	// 영화 반납하기
	public final static String UPDATE_RENTAL_RETURN_DATE = "UPDATE rental SET return_date=now() WHERE rental_id=?";
	
	// 한 고객의 연체 리스트
	public final static String SELECT_CUSTOMER_OVERDUE_LIST = "SELECT f.title, r.customer_id, r.rental_id, r.rental_date,  DATE_ADD(r.rental_date,INTERVAL f.rental_duration DAY) AS return_due_date FROM rental r JOIN inventory i ON r.inventory_id = i.inventory_id JOIN film f ON i.film_id = f.film_id WHERE r.customer_id = ? AND r.return_date IS NULL"; 
	// 한 고객의 정상 대여 리스트
	public final static String SELECT_CUSTOMER_RENTAL_LIST = "SELECT f.title, r.customer_id, r.rental_id, r.rental_date, r.return_date FROM rental r JOIN inventory i ON r.inventory_id = i.inventory_id JOIN film f ON i.film_id = f.film_id WHERE r.customer_id = ? AND r.return_date IS NOT NULL"; 
	
	// 영화 대여하기
	public final static String INSERT_RENTAL = "INSERT INTO rental(rental_date, inventory_id, customer_id, staff_id) VALUES (NOW(), ?,?,?)";

	// 최신 키값 가져오기
	public final static String SELECT_LAST_INSERT_ID = "SELECT LAST_INSERT_ID() id";
	
	// 결제 하기
	public final static String INSERT_PAYMENT = "INSERT INTO payment(customer_id, staff_id, rental_id, amount, payment_date) VALUES (?, ?, ?, ?, now())";

	// 대여 id로 해당 비디오 가격 가져오기
	public final static String SELECT_RENTAL_RATE_BY_RENTAL_ID = "SELECT f.rental_rate FROM film f JOIN inventory i ON f.film_id = i.film_id JOIN rental r ON r.inventory_id = i.inventory_id WHERE r.rental_id = ?";
}
