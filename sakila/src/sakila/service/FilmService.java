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
	
	// 영화 전체 리스트 출력 - 영화 제목으로 검색
	public Map<String, Object> getFilmListByFilmTitle(Staff staff, String filmTitle, int currentPage, int rowPage){
		Connection conn = null;
		FilmDao filmStockDao = null;
		List<FilmList> filmList = null;
		Map<String, Object> map = null;
		
		try {
			conn = DBUtil.getConnection();
			filmStockDao = new FilmDao();
			map = new HashMap<String, Object>();
			
			int endPage = filmStockDao.selectEndPageByFilmTitle(conn, staff, filmTitle, rowPage);
			filmList = filmStockDao.selectFilmListByFilmTitle(conn, staff, filmTitle, currentPage, rowPage);
			
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
	public Map<String, Object> getFilmOne(int storeId, int filmId) {
		FIlmAndCategoryAndLanguage facal = null;
		FilmDao filmDao = null;
		List<Actor> actorList = null;
		List<Rental> stockList = null;
		ActorDao actorDao = null;
		Connection conn = null;
		Map<String, Object> map = null;
		try {
			conn = DBUtil.getConnection();
			filmDao = new FilmDao();
			actorDao = new ActorDao();
			actorList = new ArrayList<Actor>();
			stockList = new ArrayList<Rental>();
			facal = new FIlmAndCategoryAndLanguage();
			facal = new FIlmAndCategoryAndLanguage();
			
			stockList = filmDao.selectFilmStockList(conn, storeId, filmId); // 재고 목록
			actorList = actorDao.selectFilmActorOne(conn, filmId); // 출연 영화배우 목록
			facal = filmDao.selectFilmOne(conn, filmId); // 영화 정보
			
			map = new HashMap<String, Object>();
			map.put("stockList", stockList);
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
	
	// 언어 목록 가져오기
	public List<Language> getLanguageList() {
		FilmDao filmDao = null;
		List<Language> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			filmDao = new FilmDao();
			list = filmDao.selectLanguageList(conn);
			
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
		
		return list;
	}
	// 카테고리 목록 가져오기
	public List<Category> getCategoryList() {
		FilmDao filmDao = null;
		List<Category> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			filmDao = new FilmDao();
			list = filmDao.selectCategoryList(conn);
			
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
		
		return list;
	}
	// 영화 등록하기
	public void addFilm(Film film, int storeId, int categoryId) {
		Connection conn = null;
		FilmDao filmDao = null;
		try {
			conn = DBUtil.getConnection();
			filmDao = new FilmDao();
			filmDao.insertFilm(conn, film);
			int filmId = filmDao.selectLastInsertFilmId(conn);
			filmDao.insertInventory(conn, filmId, storeId);
			filmDao.insertFilmCategory(conn, filmId, categoryId);
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
	
	// 영화를 인벤토리에 추가하기
	public void addInventory(int filmId, int storeId) {
		Connection conn = null;
		FilmDao filmDao = null;
		try {
			conn = DBUtil.getConnection();
			filmDao = new FilmDao();
			filmDao.insertInventory(conn, filmId, storeId);
			
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
	
	// 영화 정보 수정하기
	public void modifyFilm(Film film, int categoryId) {
		Connection conn = null;
		FilmDao filmDao = null;
		try {
			conn = DBUtil.getConnection();
			filmDao = new FilmDao();
			filmDao.updateFilm(conn, film);
			filmDao.updateFilmCategory(conn, film.getFilmId(), categoryId);
			
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
