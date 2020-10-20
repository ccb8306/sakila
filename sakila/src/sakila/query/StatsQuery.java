package sakila.query;

public class StatsQuery {
	// 오늘 날짜가 있는지
	public final static String SELECT_STATS = "SELECT * FROM stats WHERE day=?";
	// 오늘 날짜가 없을시 --> 오늘 날짜 입력 , count + 1
	public final static String INSERT_STATS = "INSERT INTO stats VALUES(?, 1)";
	// 오늘 날짜가 있을시 -- > count + 1
	public final static String UPDATE_STATS = "UPDATE stats SET count=count+1 WHERE day=?";
	
}	
