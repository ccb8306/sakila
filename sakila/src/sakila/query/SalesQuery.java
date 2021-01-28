package sakila.query;

public class SalesQuery {
	// 매장 통계
	public final static String SELECT_SALES_BY_STORE = "SELECT store, manager, total_sales FROM sales_by_store WHERE manager = ?";
	
	// 카테고리별 통계
	public final static String SELECT_SALES_BY_CATEGORY = "SELECT category, total_sales FROM sales_by_film_category";
}
