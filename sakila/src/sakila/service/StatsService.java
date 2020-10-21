package sakila.service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import sakila.common.DBUtil;
import sakila.dao.StatsDao;
import sakila.vo.Stats;

public class StatsService {
	private StatsDao statsDao;
	
	// 오늘 날짜가 있는지 확인하는 메서드
	// 오늘 날짜가 db에 없으면 null을 반환함
	public Map<String, Object> getStats() {
		Map<String, Object> map = null;
		statsDao = new StatsDao();
		Stats todayStats = new Stats();
		Connection conn = null; 	
		
		try {
			// db 연결
			conn = DBUtil.getConnection();
			System.out.println("db connection 성공");
		
			conn.setAutoCommit(false); // 오토커밋 false
			todayStats = getToday(); // stats에 오늘 날짜 추가
			todayStats = statsDao.selectDay(conn, todayStats); // 오늘 날짜와 카운트

			int totalCnt = statsDao.selectTotalCnt(conn); // 전체 접속자 수
			
			// 디버깅
			System.out.println(todayStats.getDay() + "<--getStats().todayStats Day");
			System.out.println(todayStats.getCnt() + "<--getStats().todayStats Cnt");
			System.out.println(totalCnt + "<--getStats() totalCnt");		

			// map
			map = new HashMap<String, Object>();
			map.put("todayStats",todayStats);
			map.put("totalCnt", totalCnt);
			
			conn.commit(); // 커밋
		}catch (Exception e) {
			try {
				conn.rollback(); // 롤백 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.close(); // close
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
	// 방문자 숫자 카운트
	// db에 오늘 날짜가 있는지 확인 후 없으면 오늘 날짜를 생성 후 카운트 있으면 카운트만 해줌.
	public void countStats() {
		Stats stats = new Stats();
		statsDao = new StatsDao();
		Connection conn = null; 	
		
		try {
			// db 연결
			conn = DBUtil.getConnection();
			System.out.println("db connection 성공");
			conn.setAutoCommit(false); // 오토커밋 false

			stats = getToday(); // stats에 오늘 날짜 추가
			stats = statsDao.selectDay(conn, stats); // db에서 오늘 날짜가 있는지 확인함

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
			
			// 디버깅
			System.out.println(stats.getDay() + "<--countStats().stats Day");
			System.out.println(stats.getCnt() + "<--countStats().stats Cnt");
			
			conn.commit(); // 커밋
		}catch (Exception e) {
			try {
				conn.rollback(); // 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.close(); // close
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
		
		// 디버깅
		System.out.println(stats.getDay() + "<--getToday() today");
		
		return stats;
	}
}
