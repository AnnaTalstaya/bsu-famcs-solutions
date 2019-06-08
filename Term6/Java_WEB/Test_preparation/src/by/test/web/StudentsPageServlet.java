package by.talstaya.test.web;

import by.talstaya.test.database.StudentsDatabase;
import by.talstaya.test.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentsPageServlet extends HttpServlet {

    private StudentsDatabase studentsDatabase;

//    @Override
//    public void init() throws ServletException {
//        super.init();
//
//        studentsDatabase = new StudentsDatabase();
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        List<Student> students = studentsDatabase.getStudents();
//        req.setAttribute("students", students);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("students.jsp");  //перенаправляет на страницу
        requestDispatcher.forward(req, resp);
    }
}
