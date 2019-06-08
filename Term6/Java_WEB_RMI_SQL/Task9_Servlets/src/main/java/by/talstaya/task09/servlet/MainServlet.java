package by.talstaya.task09.servlet;

import by.talstaya.task09.model.camera.NotebookDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private NotebookDao notebookDao;

    @Override
    public void init() throws ServletException {
        super.init();

        notebookDao = NotebookDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notebook = request.getParameter("notebook");
        String add = request.getParameter("add");
        if(notebook!=null) {
            request.setAttribute("notebook", notebookDao.getNotebook());
            request.getRequestDispatcher("/notebook.jsp").forward(request, response);
        } else if(add!=null) {
            request.setAttribute("notebook", notebookDao.find(request.getParameter("query")));
            request.getRequestDispatcher("/addPerson.jsp").forward(request, response);
        }else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("main.jsp");  //перенаправляет на страницу
            requestDispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String delete = req.getParameter("delete");
        String query = req.getParameter("query");
        String firstName = req.getParameter("firstName");
        String surname = req.getParameter("surname");
        String mobileNumber = req.getParameter("mobileNumber");
        if(delete != null) {
            notebookDao.deletePerson(Integer.parseInt(delete));
            req.setAttribute("notebook", notebookDao.getNotebook());
        }else if(query!=null){
            String opt1 = req.getParameter("1");
            String opt2 = req.getParameter("2");
            if(opt1!=null){
                req.setAttribute("notebook",notebookDao.showMobileNumberBySurname(query));
            }else if (opt2!=null){
                req.setAttribute("notebook", notebookDao.showSurnameByMobileNumber(query));
            }else {
                req.setAttribute("notebook", notebookDao.getNotebook());
            }
        } else if(firstName != null && surname!=null && mobileNumber!=null) {
                notebookDao.insertPerson(firstName, surname, mobileNumber);
            req.setAttribute("notebook", notebookDao.getNotebook());
            }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/notebook.jsp");
        requestDispatcher.forward(req,resp);
    }

}
