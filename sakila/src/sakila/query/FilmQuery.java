package sakila.query;

public class FilmQuery {
	// 영화 전체 리스트 출력
	public final static String SELECT_FILM_LIST = "SELECT distinct fl.FID, fl.category, fl.title, fl.price, fl.rating, fl.length from film_list fl JOIN inventory i ON i.film_id=fl.FID WHERE i.store_id =? LIMIT ?, ?";
	// 영화 전체리스트 개수
	public final static String SELECT_FILM_LIST_COUNT = "SELECT COUNT(distinct fl.FID) from film_list fl JOIN inventory i ON i.film_id=fl.FID WHERE i.store_id =?";
}
