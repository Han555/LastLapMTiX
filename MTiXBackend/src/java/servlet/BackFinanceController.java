/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.BookingFeesManager;
import manager.LicensePaymentManager;
import session.stateless.BookingFeesSessionLocal;
import session.stateless.LicensePaymentSessionLocal;

/**
 *
 * @author Student-ID
 */
@WebServlet(name = "BackFinanceController", urlPatterns = {"/BackFinanceController", "/BackFinanceController?*"})
public class BackFinanceController extends HttpServlet {

    @EJB
    private LicensePaymentSessionLocal licensePaymentSession;
    @EJB
    private BookingFeesSessionLocal bookingFeesSession;

    String currentUser;
    String role;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action;
            BookingFeesManager bookingManager = new BookingFeesManager(bookingFeesSession);
            LicensePaymentManager licensePaymentManager = new LicensePaymentManager(licensePaymentSession);
            int page = 1;
            int recordsPerPage = 8;

            action = request.getParameter("action");

            System.out.println("Action = " + action);

            if (action.equals("finances")) {
                currentUser = request.getParameter("username");
                role = request.getParameter("role");
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                System.out.println("Current User: " + currentUser);
                System.out.println("Role: " + role);
                request.getRequestDispatcher("/finances.jsp").forward(request, response);
            } else if (action.equals("createFees")) {
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                request.getRequestDispatcher("/createFees.jsp").forward(request, response);
            } else if (action.equals("makeFees")) {
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                String event = request.getParameter("event");
                String organizer = request.getParameter("organizer");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                String status = request.getParameter("status");
                String fees = request.getParameter("fees");
                bookingManager.createFee(event, organizer, startDate, endDate, fees, status);
                request.getRequestDispatcher("/createFees.jsp").forward(request, response);
            } else if (action.equals("trackFees")) {
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<ArrayList<String>> fees = bookingManager.getBookingFees();
                int noOfRecords = fees.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<ArrayList<String>> feesPage = bookingManager.feesPage(fees, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(feesPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("inbox", feesPage);
                request.getRequestDispatcher("/trackFees.jsp").forward(request, response);
            } else if (action.equals("editFeesStatus")) {
                String feesid = request.getParameter("feesid");
                String status = request.getParameter("paymentstatus");
                bookingManager.editBookingStatus(feesid, status);
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<ArrayList<String>> fees = bookingManager.getBookingFees();
                int noOfRecords = fees.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<ArrayList<String>> feesPage = bookingManager.feesPage(fees, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(feesPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("inbox", feesPage);
                request.getRequestDispatcher("/trackFees.jsp").forward(request, response);
            } else if (action.equals("createLicensePayment")) {
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                request.getRequestDispatcher("/createLicensePayment.jsp").forward(request, response);
            } else if (action.equals("makeLicensePayment")) {
                String company = request.getParameter("company");
                String email = request.getParameter("email");
                String amount = request.getParameter("amount");
                String date = request.getParameter("date");
                licensePaymentManager.addLicensingPayment(company, email, amount, date, currentUser);
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                request.getRequestDispatcher("/createLicensePayment.jsp").forward(request, response);
            } else if (action.equals("pasAccounts")) {
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<ArrayList<String>> accounts = licensePaymentManager.getLicensingAccounts();
                int noOfRecords = accounts.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<ArrayList<String>> accountPage = licensePaymentManager.accountPage(accounts, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(accountPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("inbox", accountPage);
                request.getRequestDispatcher("/pasAccounts.jsp").forward(request, response);
            } else if (action.equals("sendPasInvoice")) {
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                String accountId = request.getParameter("accountid");
                licensePaymentManager.updatePasInvoiceStatus(accountId);
                ArrayList<String> accountRecord = licensePaymentManager.getAccRecord(accountId);
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
                licensePaymentManager.sendEmail(accountRecord.get(1), "is3102mtix@gmail.com", "Invoice From MTiX\nInvoice date: " + timeStamp + "\nInvoiced to: " + accountRecord.get(0) + "\nItem: MTiX Platform License "  + "\nTotal Amount($): " + accountRecord.get(2) + "\nTransaction Date: " + accountRecord.get(3)+ "\n\nNote: This is a Computer generated invoice and thus requires no signature.", "MTiX Invoice", "smtp.gmail.com");
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<ArrayList<String>> accounts = licensePaymentManager.getLicensingAccounts();
                int noOfRecords = accounts.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<ArrayList<String>> accountPage = licensePaymentManager.accountPage(accounts, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(accountPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("inbox", accountPage);
                request.getRequestDispatcher("/pasAccounts.jsp").forward(request, response);
            } else if (action.equals("markPaidPas")) {
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                String accountId = request.getParameter("accountid");
                licensePaymentManager.markAsPaid(accountId);
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<ArrayList<String>> accounts = licensePaymentManager.getLicensingAccounts();
                int noOfRecords = accounts.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<ArrayList<String>> accountPage = licensePaymentManager.accountPage(accounts, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(accountPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("inbox", accountPage);
                request.getRequestDispatcher("/pasAccounts.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
