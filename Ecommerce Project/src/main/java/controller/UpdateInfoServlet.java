package controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddressDAO;
import dao.AddressDAOImpl;
import dao.UserPaymentDAO;
import dao.UserPaymentDAOImpl;
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
		
		int addId = Integer.parseInt(request.getParameter("AddId"));
		String address = request.getParameter("addressLine1");
		//Split for number and street name separation
		String[] parts = address.split(" ", 2);
		String number = parts[0];
		String street = parts.length > 1 ? parts[1] : "";
		 
        // Create a new address object with the updated values
        Address updatedAddress = new Address();
        updatedAddress.setId(addId);
        updatedAddress.setUnitNumber(request.getParameter("unitNumber"));
        updatedAddress.setStreetNumber(number);
        updatedAddress.setAddressLine1(street);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
