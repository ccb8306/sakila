package sakila.query;

public class ActorQuery {
	// 배우 목록
	public final static String SELECT_ACTOR_LIST = "SELECT actor_id, first_name, last_name FROM actor_info ORDER BY actor_id ASC LIMIT ?, ?";
	// 배우 목록 마지막 페이지
	public final static String SELECT_ACTOR_LIST_ENDPAGE = "SELECT COUNT(*) FROM actor_info";
	
	// 배우 상세보기
	public final static String SELECT_ACTOR_ONE = "SELECT actor_id, first_name, last_name, film_info FROM actor_info WHERE actor_id=?";
	
	// 한 영화의 배우 불러오기
	public final static String SELECT_FILM_ACTOR_ONE = "SELECT a.actor_id, a.first_name, a.last_name FROM actor a JOIN film_actor fa ON a.actor_id = fa.actor_id JOIN film f ON f.film_id = fa.film_id WHERE f.film_id = ?";

}
