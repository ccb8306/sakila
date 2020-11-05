package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.query.FilmStockQuery;
import sakila.vo.FilmList;
import sakila.vo.Staff;

public class FilmStockDao {
	// 필름 재고 리스트
	public List<FilmList> selectFilmStockList(Connection conn, Staff staff, int currentPage, int rowPage) throws Exception{
		List<FilmList> list = new ArrayList<FilmList>();
		FilmList filmList = null;
		
		PreparedStatement stmt = conn.prepareStatement(FilmStockQuery.SELECT_FILM_STOCK_LIST);
		stmt.setInt(1, staff.getStoreId());
		stmt.setInt(2, staff.getStoreId());
		stmt.setInt(3, (int)(currentPage-1)*rowPage);
		stmt.setInt(4, rowPage);
		System.out.println(stmt + "<--selectFilmStockList stmt");
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			filmList = new FilmList();
			filmList.setFid(rs.getInt("f.FID"));
			filmList.setTitle(rs.getString("f.title"));
			filmList.setPrice(rs.getDouble("f.price"));
			filmList.setRating(rs.getString("f.rating"));
			filmList.setLength(rs.getInt("f.length"));
			filmList.setStock(rs.getInt("stock"));
			filmList.setTotal(rs.getInt("total"));
			
			list.add(filmList);
		}
		
		return list;
	}
	// 필름 재고 리스트 마지막 페이지
	public int selectEndPage(Connection conn, Staff staff, int rowPage)throws Exception {
		int endPage = 0;
		
		PreparedStatement stmt = conn.prepareStatement(FilmStockQuery.SELECT_FILM_STOCK_COUNT);
		stmt.setInt(1, staff.getStoreId());
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(stmt + "<--selectEndPage stmt");
		
		if(rs.next()) {
			endPage = rs.getInt("COUNT(distinct f.FID)");
			if(endPage%rowPage == 0)			
				endPage = (int)(endPage/rowPage);
			else
				endPage = (int)(endPage/rowPage) + 1;
		}
		return endPage;
	}
}
