package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import sakila.query.FilmQuery;
import sakila.vo.*;

public class FilmDao {
	// 영화 재고 리스트
	public List<FilmList> selectFilmList(Connection conn, Staff staff, int currentPage, int rowPage) throws Exception{
		List<FilmList> list = new ArrayList<FilmList>();
		FilmList filmList = null;
		
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.SELECT_FILM_LIST);
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
	// 영화 리스트 마지막 페이지
	public int selectEndPage(Connection conn, Staff staff, int rowPage)throws Exception {
		int endPage = 0;
		
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.SELECT_FILM_LIST_ENDPAGE);
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
	// 영화 상세보기
	public FIlmAndCategoryAndLanguage selectFilmOne(Connection conn, int filmId)throws Exception{
		Film film = null;
		Category category = null;
		Language language = null;
		FIlmAndCategoryAndLanguage facal = null;
		
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.SELECT_FILM_ONE);
		stmt.setInt(1, filmId);
		
		ResultSet rs = stmt.executeQuery();

		if(rs.next()) {
			facal = new FIlmAndCategoryAndLanguage();
			film = new Film();
			category = new Category();
			language = new Language();
			
			film.setFilmId(rs.getInt("f.film_id"));
			film.setTitle(rs.getNString("f.title"));
			film.setDescription(rs.getString("f.description"));
			film.setReleaseYear(rs.getString("f.release_year"));
			film.setRentalDuration(rs.getInt("f.rental_duration"));
			film.setRentalRate(rs.getInt("f.rental_rate"));
			film.setLength(rs.getInt("f.length"));
			film.setReplacementCost(rs.getDouble("f.replacement_cost"));
			film.setRating(rs.getString("f.rating"));
			film.setSpecialFeatures(rs.getString("f.special_features"));
			category.setName(rs.getString("c.name"));
			language.setName(rs.getString("l.name"));
			
			facal.setCategory(category);
			facal.setFilm(film);
			facal.setLanguage(language);
		}
		
		return facal;
		
	}
	// 해당 영화의 재고 상태
	public List<Rental> selectFilmStockList(Connection conn, int storeId, int filmId)throws Exception {
		List<Rental> list = new ArrayList<>();
		Rental rental = null;

		PreparedStatement stmt = conn.prepareStatement(FilmQuery.SELECT_FILM_STOCK_LIST);
		stmt.setInt(1, storeId);
		stmt.setInt(2, filmId);
		System.out.println(stmt + "<--selectFilmStockList stmt");
		
		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			System.out.println(rs.getInt("i.inventory_id") + "<--rs.getInt(i.inventory_id)");
			rental = new Rental();
			rental.setInventoryId(rs.getInt("i.inventory_id"));
			rental.setRentalDate(rs.getString("r.rental_date"));
			rental.setReturnDate(rs.getString("r.return_date"));
			rental.setRentalId(rs.getInt("r.rental_id"));
			
			list.add(rental);
		}
		
		return list;
	}
}
