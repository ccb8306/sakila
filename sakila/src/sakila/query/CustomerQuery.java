package sakila.query;

public class CustomerQuery {
	// 고객 전체 리스트
	public final static String SELECT_CUSTOMER_LIST = "SELECT cl.ID, cl.name, cl.phone, cl.address, cl.city, cl.country, cl.notes," + 
			" IF((SELECT COUNT(*) FROM rental WHERE return_date IS NULL AND customer_id = cl.ID) > 0,'Y','N') AS overdue FROM customer_list cl" + 
			" WHERE cl.SID=? ORDER BY cl.ID ASC LIMIT ?, ?";
	// 고객 전체 리스트 개수
	public final static String SELECT_CUSTOMER_LIST_COUNT = "SELECT COUNT(*) FROM customer_list WHERE SID=?";
}
