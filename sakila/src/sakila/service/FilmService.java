package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import sakila.common.DBUtil;
import sakila.dao.ActorDao;
import sakila.dao.FilmDao;
import sakila.vo.*;

public class FilmService {
	// 영화 전체 리스트 출력
	public Map<String, Object> getFilmList(Staff staff, int currentPage, int rowPage){
		Connection conn = null;
		FilmDao filmStockDao = null;
		List<FilmList> filmList = null;
		Map<String, Object> map = null;
		
		try {
			conn = DBUtil.getConnection();
			filmStockDao = new FilmDao();
			map = new HashMap<String, Object>();
			
			int endPage = filmStockDao.selectEndPage(conn, staff, rowPage);
			filmList = filmStockDao.selectFilmList(conn, staff, currentPage, rowPage);
			
			map.put("endPage", endPage);
			map.put("filmList", filmList);
			
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
	
	// 영화 상세보기
	public Map<String, Object> getFilmOne(int filmId) {
		FIlmAndCategoryAndLanguage facal = null;
		FilmDao filmDao = null;
		List<Actor> actorList = null;
		ActorDao actorDao = null;
		Connection conn = null;
		Map<String, Object> map = null;
		try {
			conn = DBUtil.getConnection();
			filmDao = new FilmDao();
			actorDao = new ActorDao();
			actorList = new ArrayList<Actor>();
			facal = new FIlmAndCategoryAndLanguage();
			
			actorList = actorDao.selectFilmActorOne(conn, filmId);
			facal = filmDao.selectFilmOne(conn, filmId);
			
			map = new HashMap<String, Object>();
			map.put("actorList", actorList);
			map.put("facal", facal);
			
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
}
