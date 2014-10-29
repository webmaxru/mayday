import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ekaterina Orlova on 16/10/14.
 */

@WebServlet("/albums")
public class MusicChoiceServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MusicHandler mh;

        String genre = request.getParameter("genre");

        ArrayList<Album> albums = new ArrayList<>();
        try {
            mh = new MusicHandler();
            albums = mh.getAlbum(genre);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("albums", albums);

        RequestDispatcher view =
                request.getRequestDispatcher("albums.jsp");

        view.forward(request, response);


    }

}
