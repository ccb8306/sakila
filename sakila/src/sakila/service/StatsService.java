package sakila.service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import sakila.dao.StatsDao;
import sakila.vo.Stats;

public class StatsService {
	private StatsDao statsDao;
	
	// 오늘 날짜가 있는지 확인하는 메서드
	public Stats getStats() {
		statsDao = new StatsDao();
		Stats stats = new Stats();
		Stats returnStats = new Stats();
		
		// db 접속 정보
		final String URL = "jdbc:mariadb://localhost:3306/sakila";
		final String USER = "root";
		final String PASSWORD = "java1004";
		Connection conn = null; 	
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("db connection 성공");
			// 오토커밋 false
			conn.setAutoCommit(false); 

			// stats에 오늘 날짜 추가
			stats = getToday();

			//디버깅
			System.out.println(stats.getDay() + "<--stats.today");
			// 쿼리 실행
			returnStats = statsDao.selectDay(conn, stats);
			
			// 커밋
			conn.commit(); 
		}catch (Exception e) {
			try {
				// 롤백
				conn.rollback(); 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				// close
				conn.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return returnStats;
	}
	
	// 방문자 숫자 카운팅
	public void countStats() {
		Stats stats = new Stats();
		statsDao = new StatsDao();

		// db 접속 정보
		final String URL = "jdbc:mariadb://localhost:3306/sakila";
		final String USER = "root";
		final String PASSWORD = "java1004";
		Connection conn = null; 	
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("db connection 성공");
			// 오토커밋 false
			conn.setAutoCommit(false); 

			// db에서 오늘 날짜를 가져옴
			stats = getStats();
			
			// 오늘 날짜가 db에 없을 경우
			if(stats == null) {
				// 오늘 날짜를 db에 추가, count + 1
				System.out.println("오늘 날짜 없음");
				stats = getToday();
				statsDao.insertStats(conn, stats);
			// 오늘 날짜가 있을경우
			}else {
				// count + 1
				System.out.println("오늘 날짜 있음");
				statsDao.updateStats(conn, stats);
			}
			
			// 커밋
			conn.commit(); 
		}catch (Exception e) {
			try {
				// 롤백
				conn.rollback(); 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				// close
				conn.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 오늘 날짜 구하기
	private Stats getToday() {
		Calendar today = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String day = formater.format(today.getTime());

		// stats에 오늘 날짜 추가
		Stats stats = new Stats();
		stats.setDay(day);
		
		return stats;
	}
}
