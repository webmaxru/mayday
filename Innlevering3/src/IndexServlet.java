import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ekaterina Orlova on 16/10/14.
 */

@WebServlet("/")
public class IndexServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MusicHandler mh;
        ArrayList<String> genres = new ArrayList<>();

        try {
            mh = new MusicHandler();
            genres = mh.getGenres();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("genres", genres);

        RequestDispatcher view =
                request.getRequestDispatcher("genres.jsp");

        view.forward(request, response);


    }

}
