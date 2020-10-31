package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import sakila.common.DBUtil;
import sakila.dao.FilmDao;
import sakila.vo.*;

public class FilmService {
	// 영화 전체 리스트 출력
	public Map<String, Object> getFilmList(Staff staff, int currentPage, int rowPage){
		Map<String, Object> map = null;
		FilmDao filmDao = null;
		List<FilmList> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			map = new HashMap<String, Object>();
			filmDao = new FilmDao();
			
			list = filmDao.selectFilmList(conn, staff, currentPage, rowPage);
			int endPage = filmDao.selectFilmListEndPage(conn, staff, rowPage);
			
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
}
