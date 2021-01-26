package sakila.query;

public class FilmQuery {
	// 영화 리스트
	public final static String SELECT_FILM_LIST = "SELECT i.inventory_id, f.FID, f.title, f.price, f.rating, f.length, COUNT(i.inventory_id) AS stock," + 
			" (select COUNT(film_id) FROM inventory WHERE film_id=i.film_id AND store_id=?) AS total" +
			" FROM film_list f INNER JOIN inventory i ON f.FID = i.film_id" +
			" WHERE inventory_in_stock(inventory_id) AND i.store_id=? GROUP BY f.FID ORDER BY f.FID DESC LIMIT ?, ?";
	// 영화 리스트 최대 페이지
	public final static String SELECT_FILM_LIST_ENDPAGE = "SELECT COUNT(distinct f.FID) FROM film_list f INNER JOIN inventory i ON f.FID = i.film_id" +
			" WHERE inventory_in_stock(inventory_id) AND i.store_id=?";
	// 영화 리스트
	public final static String SELECT_FILM_LIST_BY_FILM_TITLE = "SELECT i.inventory_id, f.FID, f.title, f.price, f.rating, f.length, COUNT(i.inventory_id) AS stock," + 
			" (select COUNT(film_id) FROM inventory WHERE film_id=i.film_id AND store_id=?) AS total" +
			" FROM film_list f INNER JOIN inventory i ON f.FID = i.film_id" +
			" WHERE inventory_in_stock(inventory_id) AND i.store_id=? AND f.title LIKE ? GROUP BY f.FID ORDER BY f.FID DESC LIMIT ?, ?";
	// 영화 리스트 최대 페이지
	public final static String SELECT_FILM_LIST_ENDPAGE_BY_FILM_TITLE = "SELECT COUNT(distinct f.FID) FROM film_list f INNER JOIN inventory i ON f.FID = i.film_id" +
			" WHERE inventory_in_stock(inventory_id) AND i.store_id=? AND f.title LIKE ?";
	// 영화 상세보기
	public final static String SELECT_FILM_ONE = "SELECT f.film_id, f.title, f.description, f.release_year, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, c.name, l.name" + 
			" FROM film f LEFT JOIN film_category fc ON f.film_id = fc.film_id LEFT JOIN category c ON fc.category_id=c.category_id" + 
			" JOIN language l ON f.language_id=l.language_id WHERE f.film_id=?";
	
	// 해당 영화의 재고 목록
	public final static String SELECT_FILM_STOCK_LIST = "SELECT i.inventory_id, r.rental_date, r.return_date, r.rental_id FROM inventory i left join rental r ON r.inventory_id = i.inventory_id WHERE i.store_id=? AND i.film_id = ?" +
			" AND (r.rental_date=(SELECT MAX(r2.rental_date) FROM rental r2 WHERE i.inventory_id = r2.inventory_id) OR r.rental_date IS NULL)";	

	// 언어 목록 가져오기
	public final static String SELECT_LANGUAGE_LIST = "SELECT language_id, name FROM language";
	// 카테고리 목록 가져오기
	public final static String SELECT_CATEGORY_LIST = "SELECT category_id, name FROM category";
	
	// 영화 등록하기
	public final static String INSERT_FILM = "INSERT INTO film(title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) VALUES(?,?,?,?,?,?,?,?,?,?)";
	
	// 영화 키값 가져오기
	public final static String SELECT_LAST_INSERT_ID = "SELECT LAST_INSERT_ID() id";
	
	// 등록한 영화 인벤토리에 추가
	public final static String INSERT_INVENTORY = "INSERT INTO inventory(film_id, store_id) VALUES (?, ?)";
	
	// 영화에 카테고리 추가
	public final static String INSERT_FILM_CATEGORY = "INSERT INTO film_category(film_id, category_id) VALUES (?, ?)";
	
	// 영화 정보 수정하기
	public final static String UPDATE_FILM = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ? WHERE film_id = ?";
	// 영화 카테고리 수정하기
	public final static String UPDATE_FILM_CATEGORY = "UPDATE film_category SET category_id = ? WHERE film_id =?";
}
