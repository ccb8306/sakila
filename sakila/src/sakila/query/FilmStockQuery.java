package sakila.query;

public class FilmStockQuery {
	public final static String SELECT_FILM_STOCK_LIST = "SELECT i.inventory_id, f.FID, f.title, f.price, f.rating, f.length, COUNT(i.inventory_id) AS stock," + 
							" (select COUNT(film_id) FROM inventory WHERE film_id=i.film_id AND store_id=?) AS total" +
							" FROM film_list f INNER JOIN inventory i ON f.FID = i.film_id" +
							" WHERE inventory_in_stock(inventory_id) AND i.store_id=? GROUP BY f.FID LIMIT ?, ?";
	public final static String SELECT_FILM_STOCK_COUNT = "SELECT COUNT(distinct f.FID) FROM film_list f INNER JOIN inventory i ON f.FID = i.film_id" +
							" WHERE inventory_in_stock(inventory_id) AND i.store_id=?";

}
