package controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import model.Address;
import model.User;
import model.UserPaymentMethod;

/**
 * Servlet implementation class updateInfoServlet
 */
@WebServlet("/UpdateInfoServlet")
public class UpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserPaymentDAO userPaymentDAO;
	private AddressDAO addressDAO;
	private UserDAO userDAO;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init() throws ServletException {
		super.init();
		try {
			// Initialize the DAOs
			userPaymentDAO = new UserPaymentDAOImpl();
			addressDAO = new AddressDAOImpl();
			userDAO = new UserDAOImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//Handle different types of Info Updates based on type parameter
	    String type = request.getParameter("type");
	    if ("new_account".equals(type)) {
	        handleNewAccount(request, response);
	    } else if ("general".equals(type)){
	    	handleGeneralInfo(request, response);
	    }else if ("paymeth".equals(type)) {
            handlePaymentMethod(request, response);
        }else if ("address".equals(type)) {
            handleAddressUpdate(request, response);
        }else if ("addressadd".equals(type)) {
            handleAddressAdd(request, response);
        }else if ("addressrem".equals(type)) {
            handleAddressRem(request, response);
        }
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void handleNewAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int addId = Integer.parseInt(request.getParameter("AddId"));
		 
        // Create a new address object with the updated values
        Address updatedAddress = new Address();
        updatedAddress.setId(addId);
        updatedAddress.setUnitNumber(request.getParameter("unitNumber"));
        updatedAddress.setAddressLine1(request.getParameter("addressLine1"));
        updatedAddress.setCity(request.getParameter("city"));
        updatedAddress.setRegion(request.getParameter("region"));
        updatedAddress.setPostalCode(request.getParameter("postalCode"));
        updatedAddress.setCountry(request.getParameter("country"));

        // Obtain fields for credit card
        int userId = Integer.parseInt(request.getParameter("userId"));
        int upmId = Integer.parseInt(request.getParameter("upmId"));
        int cvv = Integer.parseInt(request.getParameter("cvv"));
        String cardProvider = request.getParameter("cardProvider");
        String cardNumber = request.getParameter("cardNumber");
        int expYear = Integer.parseInt(request.getParameter("expYear"));
        int expMonth = Integer.parseInt(request.getParameter("expMonth"));
        
        // Create a UserPaymentMethod object with updated information
        UserPaymentMethod updatedPaymentMethod = new UserPaymentMethod();
        updatedPaymentMethod.setUPM_ID(upmId);
        updatedPaymentMethod.setUser_id(userId);
        updatedPaymentMethod.setCVV(cvv);
        updatedPaymentMethod.setCardProvider(cardProvider);
        updatedPaymentMethod.setCardNumber(cardNumber);
        updatedPaymentMethod.setExp_year(expYear);
        updatedPaymentMethod.setExp_month(expMonth);
        
        //Update in database Address and Payment
        boolean addressUpdated = addressDAO.updateAddress(updatedAddress);
        boolean updatePayment = userPaymentDAO.updateUserPaymentMethod(updatedPaymentMethod);

        if (addressUpdated && updatePayment) {
            // If the address was updated successfully, redirect home
            response.sendRedirect("./");
        } else {
        	// Else go back to new_account.jsp
			UserPaymentMethod userPayMeth = userPaymentDAO.getLatestUserPaymentMethod(userId);
			request.setAttribute("latestPayMethod", userPayMeth);

			Address userAdd = addressDAO.getLatestAddressMethod(userId);
			request.setAttribute("latestAddress", userAdd);
        	request.getRequestDispatcher("new_account.jsp").forward(request, response);
        }
	}
	
	
	private void handleGeneralInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    int userId = Integer.parseInt(request.getParameter("userId"));
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String phoneNumber = request.getParameter("phoneNumber");

	    
	    // Update user details in the database
	    boolean updateSuccess = userDAO.updateUserDetails(userId, firstName, lastName, phoneNumber);

	    if (updateSuccess) {
	        //Update the user object
	    	HttpSession session = request.getSession(false);
			if (session != null) {
				User user = (User) session.getAttribute("user");
				if (user != null) {
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setPhoneNumber(phoneNumber);
				}
			}
			request.setAttribute("msg", "General info updated!");
			// If successful, redirect back to account
			request.getRequestDispatcher("./account").forward(request, response);
	    } else {
	    	 response.sendRedirect("./");
	    }
	}
	
    private void handlePaymentMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int upmId = Integer.parseInt(request.getParameter("upmId"));
        int cvv = Integer.parseInt(request.getParameter("cvv"));
        String cardProvider = request.getParameter("cardProvider");
        String cardNumber = request.getParameter("cardNumber");
        int expYear = Integer.parseInt(request.getParameter("expYear"));
        int expMonth = Integer.parseInt(request.getParameter("expMonth"));

        // Create a UserPaymentMethod object with updated information
        UserPaymentMethod updatedPaymentMethod = new UserPaymentMethod();
        updatedPaymentMethod.setUPM_ID(upmId);
        updatedPaymentMethod.setUser_id(userId);
        updatedPaymentMethod.setCVV(cvv);
        updatedPaymentMethod.setCardProvider(cardProvider);
        updatedPaymentMethod.setCardNumber(cardNumber);
        updatedPaymentMethod.setExp_year(expYear);
        updatedPaymentMethod.setExp_month(expMonth);

        // Update payment method in DB
        boolean updatePayment = userPaymentDAO.updateUserPaymentMethod(updatedPaymentMethod);

        if (updatePayment) {
        	request.setAttribute("msg", "Payment method updated successfully!");
            // Redirect back to pay method page and notify successful
        	request.getRequestDispatcher("./account?section=paymeth").forward(request, response);
        } else {
            // If update fails, get the latest and go back
            UserPaymentMethod userPayMeth = userPaymentDAO.getLatestUserPaymentMethod(userId);
            request.setAttribute("latestPayMethod", userPayMeth);
            request.setAttribute("msg", "Failed to update payment.");
            request.getRequestDispatcher("account_paymeth.jsp").forward(request, response);
        }
    }
    
    private void handleAddressUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int addressId = Integer.parseInt(request.getParameter("addressId"));
        String unitNumber = request.getParameter("unitNumber");
        String addressLine1 = request.getParameter("addressLine1");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String postalCode = request.getParameter("postalCode");
        String country = request.getParameter("country");

        Address updatedAddress = new Address();
        updatedAddress.setId(addressId);
        updatedAddress.setUnitNumber(unitNumber);
        updatedAddress.setAddressLine1(addressLine1);
        updatedAddress.setCity(city);
        updatedAddress.setRegion(region);
        updatedAddress.setPostalCode(postalCode);
        updatedAddress.setCountry(country);

        boolean addressUpdated = addressDAO.updateAddress(updatedAddress);

        if (addressUpdated) {
        	//Notify if updated successfully
        	request.setAttribute("msg", "Address updated successfully!");
        	request.getRequestDispatcher("./account?section=addresses").forward(request, response);
        } else {
            // Else show latest address when redirecting to edit
            int userId = Integer.parseInt(request.getParameter("userId"));
            Address userAddress = addressDAO.getLatestAddressMethod(userId);
            request.setAttribute("latestAddress", userAddress);
            request.setAttribute("msg", "Failed to change address.");
            // If adding address fails, redirect back to the address addition page
            request.getRequestDispatcher("account_addresses.jsp").forward(request, response);
        }
    }
    
    private void handleAddressAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        int userId = Integer.parseInt(request.getParameter("userId"));

        // Obtain details from the request parameters
        String unitNumber = request.getParameter("unitNumber");
        String addressLine1 = request.getParameter("addressLine1");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String postalCode = request.getParameter("postalCode");
        String country = request.getParameter("country");

        // Create Address object with details
        Address newAddress = new Address();
        newAddress.setUnitNumber(unitNumber);
        newAddress.setAddressLine1(addressLine1);
        newAddress.setCity(city);
        newAddress.setRegion(region);
        newAddress.setPostalCode(postalCode);
        newAddress.setCountry(country);

        // Add new address database
        boolean addressAdded = addressDAO.addAddress(userId, newAddress);

        if (addressAdded) {
            // If address added successfully, redirect to addresses and notify
            request.setAttribute("msg", "Address added successfully!");
            request.getRequestDispatcher("./account?section=addresses").forward(request, response);
        } else {
            // If adding address fails, redirect back to the address addition page
            request.setAttribute("msg", "Failed to add address. Please try again.");
            request.getRequestDispatcher("account_add_address.jsp").forward(request, response);
        }
    }
    
    //Handler to remove address
    private void handleAddressRem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int addressId = Integer.parseInt(request.getParameter("addressId"));

        boolean addressRemoved = addressDAO.removeAddressByID(userId, addressId);

        if (addressRemoved) {
            // If successfully deleted, notify on page
            request.setAttribute("msg", "Address removed successfully!");
            request.getRequestDispatcher("./account?section=addresses").forward(request, response);
        } else {
            // If removal fails, notify on page constraints
            request.setAttribute("msg", "Failed to remove address. At least one address is required. Consider adding or updating.");
            request.getRequestDispatcher("./account?section=addresses").forward(request, response);
        }
    }



}
