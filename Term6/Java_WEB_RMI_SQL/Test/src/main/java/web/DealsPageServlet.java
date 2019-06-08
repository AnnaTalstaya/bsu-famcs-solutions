package web;

import database.DealsDatabase;
import model.Deal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DealsPageServlet extends HttpServlet {

    private DealsDatabase dealsDatabase;

    @Override
    public void init() throws ServletException {
        super.init();

        dealsDatabase = new DealsDatabase();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Deal> deals = dealsDatabase.getDeals();
        req.setAttribute("deals", deals);

        HttpSession session = req.getSession();
        String lang = req.getParameter("lang");
        session.setAttribute("lang",lang);

        Integer count =  dealsDatabase.getCount();
        req.setAttribute("count",count);

        int sum = dealsDatabase.getCommonSum();
        req.setAttribute("sum", sum);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("deals.jsp");  //перенаправляет на страницу
        requestDispatcher.forward(req, resp);
    }

}
