package sakila.query;

public class FilmQuery {
	// 영화 리스트
	public final static String SELECT_FILM_LIST = "SELECT i.inventory_id, f.FID, f.title, f.price, f.rating, f.length, COUNT(i.inventory_id) AS stock," + 
			" (select COUNT(film_id) FROM inventory WHERE film_id=i.film_id AND store_id=?) AS total" +
			" FROM film_list f INNER JOIN inventory i ON f.FID = i.film_id" +
			" WHERE inventory_in_stock(inventory_id) AND i.store_id=? GROUP BY f.FID LIMIT ?, ?";
	// 영화 리스트 최대 페이지
	public final static String SELECT_FILM_LIST_ENDPAGE = "SELECT COUNT(distinct f.FID) FROM film_list f INNER JOIN inventory i ON f.FID = i.film_id" +
			" WHERE inventory_in_stock(inventory_id) AND i.store_id=?";
	// 영화 상세보기
	public final static String SELECT_FILM_ONE = "SELECT f.film_id, f.title, f.description, f.release_year, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, c.name, l.name" + 
			" FROM film f JOIN film_category fc ON f.film_id = fc.film_id JOIN category c ON fc.category_id=c.category_id" + 
			" JOIN language l ON f.language_id=l.language_id WHERE f.film_id=?";
	
}
