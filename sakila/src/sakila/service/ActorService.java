package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.common.DBUtil;
import sakila.dao.ActorDao;
import sakila.vo.Actor;

public class ActorService {
	// 배우 전체 리스트 불러오기
	public Map<String, Object> getActorList(int currentPage, int rowPage){
		Map<String, Object> map = null;
		List<Actor> list = null;
		Connection conn = null;
		ActorDao actorDao = null;
		
		try {
			// 객체 생성
			actorDao = new ActorDao();
			conn = DBUtil.getConnection();
			list = new ArrayList<Actor>();
			map = new HashMap<String, Object>();
			
			// 배우 목록과 마지막 페이지 가져오기
			list = actorDao.selectActorList(conn, currentPage, rowPage);
			int endPage = actorDao.selectActorEndPage(conn, rowPage);
			
			// 맵에 추가
			map.put("list", list);
			map.put("endPage", endPage);
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
	// 배우 상세보기
	public Actor getActorOne(int actorId) {
		Connection conn = null;
		ActorDao actorDao = null;
		Actor actor = null;
		try {
			// 객체 생성
			actorDao = new ActorDao();
			conn = DBUtil.getConnection();
			actor = new Actor();
			
			// 배우 목록과 마지막 페이지 가져오기
			actor = actorDao.selectActorOne(conn, actorId);
	
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return actor;
		
	}
	// 영화에 출연배우 추가하기
	public void addFilmActor(int actorId, int filmId) {
		Connection conn = null;
		ActorDao actorDao = null;
		try {
			// 객체 생성
			actorDao = new ActorDao();
			conn = DBUtil.getConnection();
			
			actorDao.insertFilmActor(conn, actorId, filmId);
	
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
