import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "Test", value = "/action")
public class Test extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String page = request.getParameter("user_link");
        try {
            LinkStorage ls = new LinkStorage(page);
            request.setAttribute("links", ls.getLinks());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}