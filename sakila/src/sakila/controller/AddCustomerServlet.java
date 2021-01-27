package sakila.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sakila.service.CustomerService;
import sakila.vo.Address;
import sakila.vo.CityAndCountry;
import sakila.vo.Customer;
import sakila.vo.Staff;

@WebServlet("/auth/AddCustomerServlet")
public class AddCustomerServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerService customerService = new CustomerService();
		List<CityAndCountry> cacList = customerService.getCityAndCountryList();
		
		request.setAttribute("cacList", cacList);
		request.getRequestDispatcher("/WEB-INF/views/auth/customer/addCustomer.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Staff staff = new Staff();
		staff = (Staff)session.getAttribute("loginStaff");		
		int storeId = staff.getStoreId();
		
		CustomerService customerService = new CustomerService();
		
		Address address = new Address();
		address.setAddress(request.getParameter("address"));
		address.setDistrict(request.getParameter("district"));
		address.setCityId(Integer.parseInt(request.getParameter("cityId")));
		address.setPostalCode(request.getParameter("postalCode"));
		address.setPhone(request.getParameter("phone"));
		int addressId = customerService.addAddress(address);
		
		Customer customer = new Customer();
		customer.setStoreId(storeId);
		customer.setFirstName(request.getParameter("firstName"));
		customer.setLastName(request.getParameter("lastName"));
		customer.setEmail(request.getParameter("email"));
		customer.setAddressId(addressId);
		
		customerService.addCustomer(customer);
		
		response.sendRedirect(request.getContextPath() + "/auth/CustomerListServlet");
	}

}
