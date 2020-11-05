package sakila.service;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.common.DBUtil;
import sakila.dao.FilmStockDao;
import sakila.vo.FilmList;
import sakila.vo.Staff;

public class FilmStockService {
	// 필름 재고 리스트
	public Map<String, Object> getFilmStockList(Staff staff, int currentPage, int rowPage){
		Connection conn = null;
		FilmStockDao filmStockDao = null;
		List<FilmList> filmList = null;
		Map<String, Object> map = null;
		
		try {
			conn = DBUtil.getConnection();
			filmStockDao = new FilmStockDao();
			map = new HashMap<String, Object>();
			
			int endPage = filmStockDao.selectEndPage(conn, staff, rowPage);
			filmList = filmStockDao.selectFilmStockList(conn, staff, currentPage, rowPage);
			
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
}
