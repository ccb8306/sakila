package sakila.dao;

import java.sql.*;
import java.util.*;

import sakila.query.CustomerQuery;
import sakila.query.RentalQuery;
import sakila.vo.*;

public class RentalDao {
	// 대여중인 전체 대여 리스트 출력
	public List<RentalAndFilm> selectRentalList(Connection conn, int storeId, int currentPage, int rowPage) throws Exception{
		List<RentalAndFilm> list = new ArrayList<RentalAndFilm>();
		RentalAndFilm raf = null;
		Rental rental = null;
		Film film = null;
		
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.SELECT_RENTAL_LIST);
		stmt.setInt(1, storeId);  // 상점아이디
		stmt.setInt(2,(int)((currentPage -1) * rowPage));  // 페이징
		stmt.setInt(3, rowPage); // 페이징	
		System.out.println(stmt + "<--select rentalList stmt");
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			rental = new Rental();
			film = new Film();
			
			rental.setRentalId(rs.getInt("r.rental_id"));
			film.setTitle(rs.getString("f.title"));
			rental.setRentalDate(rs.getString("rental_date"));
			film.setRentalDuration(rs.getInt("f.rental_duration"));
			rental.setReturnDueDate(rs.getString("return_due_date"));
			
			raf = new RentalAndFilm();
			raf.setFilm(film);
			raf.setRental(rental);
			
			list.add(raf);
		}
		
		return list;
	}
	// 대여중인 전체 대여 리스트 최대 페이지
	public int selectRentalListEndPage(Connection conn, int storeId, int rowPage)throws Exception {
		int endPage = 1;
		
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.SELECT_RENTAL_LIST_COUNT);
		stmt.setInt(1, storeId);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			endPage = rs.getInt("COUNT(*)");
			if(endPage%rowPage == 0)			
				endPage = (int)(endPage/rowPage);
			else
				endPage = (int)(endPage/rowPage) + 1;
			
		}	
		return endPage;
	}
	// 대여중인 전체 대여 리스트 출력
	public List<RentalAndFilm> selectRentalListByFilmTitle(Connection conn, int storeId, String filmTitle, int currentPage, int rowPage) throws Exception{
		List<RentalAndFilm> list = new ArrayList<RentalAndFilm>();
		RentalAndFilm raf = null;
		Rental rental = null;
		Film film = null;
		
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.SELECT_RENTAL_LIST_BY_FilmTitle);
		stmt.setInt(1, storeId);  // 상점아이디
		stmt.setString(2, "%" + filmTitle + "%"); // 영화 제목 or 대여 id
		stmt.setString(3, filmTitle); // 영화 제목 or 대여 id
		stmt.setInt(4,(int)((currentPage -1) * rowPage));  // 페이징
		stmt.setInt(5, rowPage); // 페이징	
		System.out.println(stmt + "<--select rentalList stmt");
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			rental = new Rental();
			film = new Film();
			
			rental.setRentalId(rs.getInt("r.rental_id"));
			film.setTitle(rs.getString("f.title"));
			rental.setRentalDate(rs.getString("rental_date"));
			film.setRentalDuration(rs.getInt("f.rental_duration"));
			rental.setReturnDueDate(rs.getString("return_due_date"));
			
			raf = new RentalAndFilm();
			raf.setFilm(film);
			raf.setRental(rental);
			
			list.add(raf);
		}
		
		return list;
	}
	// 대여중인 전체 대여 리스트 최대 페이지
	public int selectRentalListByFilmTitleEndPage(Connection conn, int storeId, String filmTitle, int rowPage)throws Exception {
		int endPage = 1;
		
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.SELECT_RENTAL_LIST_BY_FilmTitle_COUNT);
		stmt.setInt(1, storeId);
		stmt.setString(2, "%" + filmTitle + "%"); // 영화 제목 or 대여 id
		stmt.setString(3, filmTitle); // 영화 제목 or 대여 id
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			endPage = rs.getInt("COUNT(*)");
			if(endPage%rowPage == 0)			
				endPage = (int)(endPage/rowPage);
			else
				endPage = (int)(endPage/rowPage) + 1;
			
		}	
		return endPage;
	}
	// 한 고객의 연체 리스트
	public List<RentalAndFilm> selectCustomerOverDueList(Connection conn , int customerId)throws Exception{
		List<RentalAndFilm> list = new ArrayList<RentalAndFilm>();
		RentalAndFilm raf = null;
		
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.SELECT_CUSTOMER_OVERDUE_LIST);
		stmt.setInt(1, customerId);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Rental rental = new Rental();
			Film film = new Film();
			raf = new RentalAndFilm();
			
			film.setTitle(rs.getString("f.title"));
			rental.setRentalId(rs.getInt("r.rental_id"));
			rental.setRentalDate(rs.getString("r.rental_date"));
			rental.setReturnDueDate(rs.getString("return_due_date"));
			
			raf.setFilm(film);
			raf.setRental(rental);
			
			list.add(raf);
		}
		
		return list;
	}
		
	// 한 고객의 대여 리스트
	public List<RentalAndFilm> selectCustomerRentalList(Connection conn , int customerId)throws Exception{
		List<RentalAndFilm> list = new ArrayList<RentalAndFilm>();
		RentalAndFilm raf = null;
		
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.SELECT_CUSTOMER_RENTAL_LIST);
		stmt.setInt(1, customerId);
		System.out.println(stmt + "<--selectCustomerRentalList stmt");
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Rental rental = new Rental();
			Film film = new Film();
			raf = new RentalAndFilm();
			
			film.setTitle(rs.getString("f.title"));
			rental.setRentalId(rs.getInt("r.rental_id"));
			rental.setRentalDate(rs.getString("r.rental_date"));
			rental.setReturnDate(rs.getString("r.return_date"));
			
			raf.setFilm(film);
			raf.setRental(rental);
			
			list.add(raf);
		}
		
		return list;
	}
	
	// 대여 비디오 반납
	public void updateRentalReturnDate(Connection conn , int rentalId)throws Exception{	
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.UPDATE_RENTAL_RETURN_DATE);
		stmt.setInt(1, rentalId);
		System.out.println(stmt + "<--updateRentalReturnDate stmt");
		
		stmt.executeUpdate();
	}
	
	// 영화 대여하기
	public void insertRental(Connection conn, Rental rental) throws Exception{
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.INSERT_RENTAL);
		stmt.setInt(1, rental.getInventoryId());
		stmt.setInt(2, rental.getCusotomerId());
		stmt.setInt(3, rental.getStaffId());
		System.out.println(stmt + "<--insertRental stmt");
		
		stmt.executeUpdate();
	}
	
	// 마지막 키값 가져오기
	public int selectLastInsertId(Connection conn) throws Exception {
		PreparedStatement stmt = conn.prepareStatement(CustomerQuery.SELECT_LAST_INSERT_ID);
		int lastId = 0;
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			lastId = rs.getInt("id");
		}
		System.out.println(lastId + "<--lastId");
		
		return lastId;
	}
	
	// 영화 결제하기
	public void insertPayment(Connection conn, Payment payment) throws Exception{
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.INSERT_PAYMENT);
		stmt.setInt(1, payment.getCustomerId());
		stmt.setInt(2, payment.getStaffId());
		stmt.setInt(3, payment.getRental_id());
		stmt.setDouble(4, payment.getAmount());
		System.out.println(stmt + "<--insertPayment stmt");
		
		stmt.executeUpdate();
	}
	// 렌탈id로 해당 비디오 가격 가져오기
	public double selectRentalRateByRentalId(Connection conn, int rentalId) throws Exception {
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.SELECT_RENTAL_RATE_BY_RENTAL_ID);
		stmt.setInt(1, rentalId);
		ResultSet rs = stmt.executeQuery();
		
		double rentalRate = 0;
		if(rs.next()) {
			rentalRate = rs.getDouble("f.rental_rate");
		}
		
		return rentalRate;
	}
}
