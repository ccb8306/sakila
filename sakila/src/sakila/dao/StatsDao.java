package sakila.dao;

import java.sql.*;
import java.util.Calendar;

import sakila.vo.Stats;
import sakila.query.*;

public class StatsDao {
	
	// 오늘 날짜가 있는지
	public Stats selectDay(Connection conn, Stats stats) throws Exception{
		Stats returnStats = null;
		PreparedStatement stmt = conn.prepareStatement(StatsQuery.SELECT_STATS);
		stmt.setString(1, stats.getDay());
		//디버깅
		System.out.println(stmt + "<--selectDay stmt");
		
		ResultSet rs = stmt.executeQuery();
		
		// 오늘 날짜가 있을 시 
		if(rs.next()) {
			returnStats = new Stats();
			returnStats.setDay(rs.getString("day"));
			returnStats.setCount(rs.getInt("count"));
		}
		
		return returnStats;
	}
	
	// 오늘 날짜가 없을시 --> 오늘 날짜 입력 , count + 1
	public void insertStats(Connection conn, Stats stats) throws Exception{
		PreparedStatement stmt = conn.prepareStatement(StatsQuery.INSERT_STATS);
		stmt.setString(1, stats.getDay());
		//디버깅
		System.out.println(stmt + "<--insertStats stmt");
		
		stmt.executeUpdate();
	}
	
	// 오늘 날짜가 있을시 -- > count + 1
	public void updateStats(Connection conn, Stats stats) throws Exception{
		PreparedStatement stmt = conn.prepareStatement(StatsQuery.UPDATE_STATS);
		stmt.setString(1, stats.getDay());
		//디버깅
		System.out.println(stmt + "<--updateStats stmt");
		
		stmt.executeUpdate();
	}
	
	
}
