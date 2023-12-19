package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddressDAO;
import dao.AddressDAOImpl;
import dao.ProductsDetailsDAO;
import dao.ProductsDetailsDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import dao.UserPaymentDAO;
import dao.UserPaymentDAOImpl;
import model.Address;
import model.User;
import model.UserPaymentMethod;

@WebServlet("/AdminUpdateServlet")
public class AdminUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserPaymentDAO userPaymentDAO;
    private AddressDAO addressDAO;
    private UserDAO userDAO;
    private ProductsDetailsDAO productDetailsDAO;

    public AdminUpdateServlet() {
        super();
    }

    public void init() throws ServletException {
        super.init();
        try {
            userPaymentDAO = new UserPaymentDAOImpl();
            addressDAO = new AddressDAOImpl();
            userDAO = new UserDAOImpl();
            productDetailsDAO = new ProductsDetailsDAOImpl();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        
        if ("general".equals(type)){
            handleGeneralInfo(request, response);
        } else if ("paymeth".equals(type)) {
            handlePaymentMethod(request, response);
        } else if ("address".equals(type)) {
            handleAddressUpdate(request, response);
        } else if ("addressadd".equals(type)) {
            handleAddressAdd(request, response);
        } else if ("addressrem".equals(type)) {
            handleAddressRem(request, response);
        }else if ("productqty".equals(type)) {
        	handleProductQuantityUpdate(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    // Methods for handling different types of updates
    
    private void handleGeneralInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Updating general information of a user
        int userId = Integer.parseInt(request.getParameter("userId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");

        boolean updateSuccess = userDAO.updateUserDetails(userId, firstName, lastName, phoneNumber);

        if (updateSuccess) {
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
            request.getRequestDispatcher("./admin?action=EditUser&selectedUser=" + userId).forward(request, response);
        } else {
            response.sendRedirect("./");
        }
    }


    //Method to update the Payment method of User selected
    private void handlePaymentMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int upmId = Integer.parseInt(request.getParameter("upmId"));
        int cvv = Integer.parseInt(request.getParameter("cvv"));
        String cardProvider = request.getParameter("cardProvider");
        String cardNumber = request.getParameter("cardNumber");
        int expYear = Integer.parseInt(request.getParameter("expYear"));
        int expMonth = Integer.parseInt(request.getParameter("expMonth"));

        UserPaymentMethod updatedPaymentMethod = new UserPaymentMethod();
        updatedPaymentMethod.setUPM_ID(upmId);
        updatedPaymentMethod.setUser_id(userId);
        updatedPaymentMethod.setCVV(cvv);
        updatedPaymentMethod.setCardProvider(cardProvider);
        updatedPaymentMethod.setCardNumber(cardNumber);
        updatedPaymentMethod.setExp_year(expYear);
        updatedPaymentMethod.setExp_month(expMonth);

        boolean updatePayment = userPaymentDAO.updateUserPaymentMethod(updatedPaymentMethod);

        if (updatePayment) {
            request.setAttribute("msg", "Payment method updated successfully!");
            request.getRequestDispatcher("./admin?action=EditUser&selectedUser=" + userId).forward(request, response);
        } else {
            UserPaymentMethod userPayMeth = userPaymentDAO.getLatestUserPaymentMethod(userId);
            request.setAttribute("latestPayMethod", userPayMeth);
            request.setAttribute("msg", "Failed to update payment.");
            request.getRequestDispatcher("./admin?action=EditUser&selectedUser=" + userId).forward(request, response);
        }
    }

    //Method to update the Address of User Selected
    private void handleAddressUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	int userId = Integer.parseInt(request.getParameter("userId"));
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
            request.setAttribute("msg", "Address updated successfully!");
            request.getRequestDispatcher("./admin?action=EditUser&selectedUser=" + userId).forward(request, response);
        } else {
            Address userAddress = addressDAO.getLatestAddressMethod(userId);
            request.setAttribute("latestAddress", userAddress);
            request.setAttribute("msg", "Failed to change address.");
            request.getRequestDispatcher("./admin?action=EditUser&selectedUser=" + userId).forward(request, response);
        }
    }
    
    //Handler to add address for User Selected
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

        // Add new address to the database
        boolean addressAdded = addressDAO.addAddress(userId, newAddress);

        if (addressAdded) {
            // Address added successfully
            request.setAttribute("msg", "Address added successfully!");
            request.getRequestDispatcher("./admin?action=EditUser&selectedUser=" + userId).forward(request, response);
        } else {
            // Failed to add address
            request.setAttribute("msg", "Failed to add address. Please try again.");
            request.getRequestDispatcher("./admin?action=EditUser&selectedUser=" + userId).forward(request, response);
        }
    }

    //Handler to remove address for User
    private void handleAddressRem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int addressId = Integer.parseInt(request.getParameter("addressId"));

        boolean addressRemoved = addressDAO.removeAddressByID(userId, addressId);

        if (addressRemoved) {
            // If successfully deleted, notify
            request.setAttribute("msg", "Address removed successfully!");
            request.getRequestDispatcher("./admin?action=EditUser&selectedUser=" + userId).forward(request, response);
        } else {
            // If removal fails, notify
            request.setAttribute("msg", "Failed to remove address. At least one address is required. Consider adding or updating.");
            request.getRequestDispatcher("./admin?action=EditUser&selectedUser=" + userId).forward(request, response);
        }
    }
    
    //Method to update the quantity remaining of a product
    private void handleProductQuantityUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("prodId"));
        int updatedQuantity = Integer.parseInt(request.getParameter("quantityRemaining"));
        String productName = request.getParameter("productName");

        try {
            boolean success = productDetailsDAO.updateProductQuantity(productId, updatedQuantity);

            if (success) {
                request.setAttribute("msg", "Product: " + productName + " quantity updated successfully! Check list to verify.");
            } else {
                request.setAttribute("msg", "Failed to update product quantity.");
            }

            // Redirect back to Admin inventory section
            request.getRequestDispatcher("./admin?section=inventory").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
