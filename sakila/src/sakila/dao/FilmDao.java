package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import sakila.query.FilmQuery;
import sakila.vo.*;

public class FilmDao {
	// 영화 전체 리스트 출력
	public List<FilmList> selectFilmList(Connection conn, Staff staff, int currentPage, int rowPage) throws Exception{
		List<FilmList> list = new ArrayList<FilmList>();
		FilmList fl = null;
		
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.SELECT_FILM_LIST);
		stmt.setInt(1, staff.getStoreId());
		stmt.setInt(2, (int)(currentPage-1)*rowPage);
		stmt.setInt(3, rowPage);
		
		System.out.println(stmt + "<--selectFilmList stmt");
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			fl = new FilmList();
			fl.setFid(rs.getInt("fl.FID"));
			fl.setTitle(rs.getString("fl.title"));
			fl.setCategory(rs.getString("fl.category"));
			fl.setPrice(rs.getDouble("fl.price"));
			fl.setRating(rs.getString("fl.rating"));
			fl.setLength(rs.getInt("fl.length"));
			
			list.add(fl);
		}
		
		return list;
	}
	// 영화 전체리스트 마지막 페이지 구하기
	public int selectFilmListEndPage(Connection conn, Staff staff, int rowPage) throws Exception{
		int endPage = 1;
		
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.SELECT_FILM_LIST_COUNT);
		stmt.setInt(1, staff.getStoreId());
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(stmt + "<--selectFilmListEndPage stmt");
		
		if(rs.next()) {
			endPage = rs.getInt("COUNT(distinct fl.FID)");
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
}
