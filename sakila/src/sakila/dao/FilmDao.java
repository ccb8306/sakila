package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import sakila.query.FilmQuery;
import sakila.vo.*;

public class FilmDao {
	// 영화 리스트
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
	// 영화 리스트 - 제목 검색
	public List<FilmList> selectFilmListByFilmTitle(Connection conn, Staff staff, String filmTitle, int currentPage, int rowPage) throws Exception{
		List<FilmList> list = new ArrayList<FilmList>();
		FilmList filmList = null;
		
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.SELECT_FILM_LIST_BY_FILM_TITLE);
		stmt.setInt(1, staff.getStoreId());
		stmt.setInt(2, staff.getStoreId());
		stmt.setString(3, "%" + filmTitle + "%");
		stmt.setInt(4, (int)(currentPage-1)*rowPage);
		stmt.setInt(5, rowPage);
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
	// 영화 리스트 마지막 페이지 - 영화 제목 검색
	public int selectEndPageByFilmTitle(Connection conn, Staff staff, String filmTitle, int rowPage)throws Exception {
		int endPage = 0;
		
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.SELECT_FILM_LIST_ENDPAGE_BY_FILM_TITLE);
		stmt.setInt(1, staff.getStoreId());
		stmt.setString(2, "%" + filmTitle + "%");
		
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
			film.setReleaseYear(rs.getString("f.release_year").substring(0,4));
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
	// 언어 목록 가져오기
	public List<Language> selectLanguageList(Connection conn)throws Exception {
		List<Language> list = new ArrayList<>();
		Language language = null;

		PreparedStatement stmt = conn.prepareStatement(FilmQuery.SELECT_LANGUAGE_LIST);
		System.out.println(stmt + "<--selectLanguageList stmt");
		
		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			language = new Language();
			language.setLanguage(rs.getInt("language_id"));
			language.setName(rs.getString("name"));
			
			list.add(language);
		}
		
		return list;
	}
	// 카테고리 목록 가져오기
	public List<Category> selectCategoryList(Connection conn)throws Exception {
		List<Category> list = new ArrayList<>();
		Category category = null;

		PreparedStatement stmt = conn.prepareStatement(FilmQuery.SELECT_CATEGORY_LIST);
		System.out.println(stmt + "<--selectLanguageList stmt");
		
		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			category = new Category();
			category.setCategoryId(rs.getInt("category_id"));
			category.setName(rs.getString("name"));
			
			list.add(category);
		}
		
		return list;
	}
	// 영화 등록하기
	public void insertFilm(Connection conn, Film film) throws Exception {
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.INSERT_FILM);
		stmt.setString(1, film.getTitle());
		stmt.setString(2, film.getDescription());
		stmt.setString(3, film.getReleaseYear());
		stmt.setInt(4, film.getLanguageId());
		stmt.setInt(5, film.getRentalDuration());
		stmt.setDouble(6, film.getRentalRate());
		stmt.setInt(7, film.getLength());
		stmt.setDouble(8, film.getReplacementCost());
		stmt.setString(9, film.getRating());
		stmt.setString(10, film.getSpecialFeatures());
		System.out.println(stmt + "<--insertFilm stmt");
		
		stmt.executeUpdate();

	}
	// 영화 키값 가져오기
	public int selectLastInsertFilmId(Connection conn) throws Exception {
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.SELECT_LAST_INSERT_ID);
		int filmId = 0;
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			filmId = rs.getInt("id");
		}
		System.out.println(filmId + "<--filmId");
		
		return filmId;
	}
	// 등록한 영화 인벤토리에 추가
	public void insertInventory(Connection conn, int filmId, int storeId) throws Exception {
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.INSERT_INVENTORY);
		stmt.setInt(1, filmId);
		stmt.setInt(2, storeId);
		System.out.println(stmt + "<--insertInventory stmt");
		
		stmt.executeUpdate();
	}
	// 영화 카테고리 추가
	public void insertFilmCategory(Connection conn, int filmId, int categoryId) throws Exception {
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.INSERT_FILM_CATEGORY);
		stmt.setInt(1, filmId);
		stmt.setInt(2, categoryId);
		System.out.println(stmt + "<--insertFilmCategory stmt");
		
		stmt.executeUpdate();
	}
	// 영화 정보 수정
	public void updateFilm(Connection conn, Film film) throws Exception {
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.UPDATE_FILM);
		stmt.setString(1, film.getTitle());
		stmt.setString(2, film.getDescription());
		stmt.setString(3, film.getReleaseYear());
		stmt.setInt(4, film.getLanguageId());
		stmt.setInt(5, film.getRentalDuration());
		stmt.setDouble(6, film.getRentalRate());
		stmt.setInt(7, film.getLength());
		stmt.setDouble(8, film.getReplacementCost());
		stmt.setString(9, film.getRating());
		stmt.setInt(10, film.getFilmId());
		System.out.println(stmt + "<--updateFilm stmt");
		
		stmt.executeUpdate();
	}
	// 영화 카테고리 수정
	public void updateFilmCategory(Connection conn, int filmId, int categoryId) throws Exception {
		PreparedStatement stmt = conn.prepareStatement(FilmQuery.UPDATE_FILM_CATEGORY);
		stmt.setInt(1, categoryId);
		stmt.setInt(2, filmId);
		System.out.println(stmt + "<--updateFilm stmt");
		
		stmt.executeUpdate();
	}
}
