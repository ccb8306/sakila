package sakila.query;

public class FilmQuery {
	// 영화 전체 리스트 출력
	public final static String SELECT_FILM_LIST = "SELECT distinct fl.FID, fl.category, fl.title, fl.price, fl.rating, fl.length from film_list fl JOIN inventory i ON i.film_id=fl.FID WHERE i.store_id =? LIMIT ?, ?";
	// 영화 전체리스트 개수
	public final static String SELECT_FILM_LIST_COUNT = "SELECT COUNT(distinct fl.FID) from film_list fl JOIN inventory i ON i.film_id=fl.FID WHERE i.store_id =?";

	// 영화 상세보기
	public final static String SELECT_FILM_ONE = "SELECT f.film_id, f.title, f.description, f.release_year, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, c.name, l.name" + 
			" FROM film f JOIN film_category fc ON f.film_id = fc.film_id JOIN category c ON fc.category_id=c.category_id" + 
			" JOIN language l ON f.language_id=l.language_id WHERE f.film_id=?";
}
