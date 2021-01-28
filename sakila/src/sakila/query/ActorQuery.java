package sakila.query;

public class ActorQuery {
	// 배우 목록
	public final static String SELECT_ACTOR_LIST = "SELECT actor_id, first_name, last_name FROM actor_info ORDER BY actor_id DESC LIMIT ?, ?";
	// 배우 목록 마지막 페이지
	public final static String SELECT_ACTOR_LIST_ENDPAGE = "SELECT COUNT(*) FROM actor_info";
	// 배우 목록 - 배우 이름 검색
	public final static String SELECT_ACTOR_LIST_BY_NAME = "SELECT actor_id, first_name, last_name FROM actor_info WHERE first_name like ? OR last_name like? ORDER BY actor_id DESC LIMIT ?, ?";
	// 배우 목록 마지막 페이지 - 배우 이름 검색
	public final static String SELECT_ACTOR_LIST_ENDPAGE_BY_NAME = "SELECT COUNT(*) FROM actor_info WHERE first_name like ? OR last_name like?";
	
	// 배우 상세보기
	public final static String SELECT_ACTOR_ONE = "SELECT actor_id, first_name, last_name, film_info FROM actor_info WHERE actor_id=?";
	
	// 한 영화의 배우 불러오기
	public final static String SELECT_FILM_ACTOR_ONE = "SELECT a.actor_id, a.first_name, a.last_name FROM actor a JOIN film_actor fa ON a.actor_id = fa.actor_id JOIN film f ON f.film_id = fa.film_id WHERE f.film_id = ?";

	// 영화에 출연 배우 추가
	public final static String INSERT_FILM_ACTOR = "INSERT INTO film_actor(actor_id, film_id) VALUES(?, ?)";
	
	// 배우 추가하기
	public final static String INSERT_ACTOR = "INSERT INTO actor(first_name, last_name) VALUES(?, ?)";
	
	// 배우 정보 수정
	public final static String UPDATE_ACTOR = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
}
