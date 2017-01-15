package web;

import dao.AccountDaoImpl;
import dao.FinanceDaoImpl;
import dao.IFinanceDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.IAccountDao;
import dao.IOperationDao;
import dao.OperationDaoImpl;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Fin;
import model.Operation;

/**
 *
 * @author Sergey
 */
@WebServlet("/cash")
public class CashServlet extends HttpServlet {

    private final IFinanceDao financeDao = new FinanceDaoImpl();
    private final IAccountDao accountDao = new AccountDaoImpl();
    private final IOperationDao operationDao = new OperationDaoImpl();
    int idAccount = 0;
    Date date1 = null;
    Date date2 = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        int idUser = Integer.parseInt((String) httpSession.getAttribute("idUser"));

        if (request.getParameter("addFinance") != null) {
            Operation operation = new Operation(Integer.parseInt(request.getParameter("nameOperation")));
            Account account = new Account(Integer.parseInt(request.getParameter("nameAccount")));
            Date data = verificateDate(request);
            int balans = operationDao.getBalans(Integer.parseInt(request.getParameter("nameOperation")));
            int finrez = verificateFinrez(request) * balans;
            if (true) {
                financeDao.addFin(new Fin(data, finrez, account, operation, idUser));
                response.sendRedirect("cash");
                return;
            }
        }
        if (request.getParameter("formOperation") != null) {
            String operationName = request.getParameter("operationName");
            int balans = Integer.parseInt(request.getParameter("profits"));
            if (true) {
                operationDao.addOperation(new Operation(operationName, balans, idUser));
                response.sendRedirect("cash");
                return;
            }
        }
        request.setAttribute("cash", financeDao.getAll(idUser));
        request.setAttribute("sumAll", financeDao.sumAll(idUser));
        request.setAttribute("sumAllAcaunt", financeDao.sumAll(idUser));

        if (request.getParameter("formSort") != null) {
            idAccount = 0;
            date1 = new Date(System.currentTimeMillis());
            date2 = new Date(System.currentTimeMillis());

            try {
                if (request.getParameter("select") == null) {
                    idAccount = 0;
                } else {
                    idAccount = Integer.parseInt(request.getParameter("select"));
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat();
                dateFormat.applyPattern("dd.MM.yyyy");
                date1 = (Date) dateFormat.parse(request.getParameter("date1"));
                date2 = (Date) dateFormat.parse(request.getParameter("date2"));
            } catch (ParseException ex) {
                Logger.getLogger(CashServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (true) {
                request.setAttribute("cash", financeDao.getSort(idAccount, date1, date2, idUser));
                request.setAttribute("sumAll", financeDao.sumSort(idAccount, date1, date2, idUser));
            }
        }
        request.setAttribute("acc", accountDao.getAllAccount());
        request.setAttribute("operat", operationDao.getAllOperatin(idUser));

        request.getRequestDispatcher("WEB-INF/views/cash.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        int idUser = Integer.parseInt((String) httpSession.getAttribute("idUser"));
        if (httpSession.getAttribute("auth") == null) {
            request.setAttribute("error", "Session expired!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }
        String param = request.getParameter("remove");
        if (existRemove(param)) {
            financeDao.delRow(Integer.parseInt(param));
        }
        String paramSelect = request.getParameter("select");
        if (existRemove(paramSelect)) {
            int paramSelectInt = Integer.parseInt(paramSelect);
            request.setAttribute("cash", financeDao.allRecordsByIdAccount(paramSelectInt, idUser));
            request.setAttribute("sumByIdAccount", financeDao.sumByIdAccount(paramSelectInt, idUser));
        } else {
            request.setAttribute("cash", financeDao.getAll(idUser));
        }
        request.setAttribute("acc", accountDao.getAllAccount());
        request.setAttribute("operat", operationDao.getAllOperatin(idUser));
        request.setAttribute("sumAll", financeDao.sumAll(idUser));
        request.setAttribute("sumAllAccount", financeDao.sumAll(idUser));
        request.getRequestDispatcher("WEB-INF/views/cash.jsp").forward(request, response);
    }

    private int verificateFinrez(HttpServletRequest request) throws NumberFormatException {
        int finrez;
        String param = request.getParameter("finrez");
        if (param == null || param.trim().isEmpty()) {
            finrez = 0;
        } else if (param.matches("\\d+")) {
            finrez = Integer.parseInt(param);
        } else {
            finrez = 0;
        }
        return finrez;
    }

    private Date verificateDate(HttpServletRequest request) {
        Date data = new Date(System.currentTimeMillis());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            dateFormat.applyPattern("dd.MM.yyyy");
            if (request.getParameter("data") != null) {
                data = (Date) dateFormat.parse(request.getParameter("data"));
            }
        } catch (ParseException ex) {
            Logger.getLogger(CashServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    private boolean existRemove(String param) {
        return param != null && !param.trim().isEmpty() && param.matches("\\d+");
    }
}
