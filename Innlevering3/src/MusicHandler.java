import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ekaterina Orlova on 16/10/14.
 */
public class MusicHandler {
    ConnectToDB db;
    ArrayList<Album> albumList = new ArrayList<>();

    String title;
    String artist;
    String genre;
    int tracks;
    int releaseYear;

    public MusicHandler() throws SQLException {
        db = new ConnectToDB("localhost", "test", "root", "");
    }

    ArrayList<Album> getAlbum(String genre) throws SQLException {

        Connection connection = db.getConnection();
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select Artist, Title, Track, ReleaseYear, Genre" +
                    " from album where Genre = ?;\n ");
            preparedStatement.setString(1, genre);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                artist = rs.getString(1);//get Artist
                title = rs.getString(2);//get Title
                tracks = rs.getInt(3);//get Tracks
                releaseYear = rs.getInt(4);//get year
                genre = rs.getString(5);

                //Create an album with data from resultSet
                Album album = new Album(title, artist, genre, tracks, releaseYear);
                albumList.add(album);
            }
        }

        return albumList;

    }

   ArrayList<String> getGenres() throws SQLException {

       ArrayList<String> genres = new ArrayList<>();
       Connection connection = db.getConnection();
       {
           PreparedStatement preparedStatement = connection.prepareStatement("select distinct Genre from album;");
           ResultSet rs = preparedStatement.executeQuery();
           while (rs.next()) {

               genres.add(rs.getString(1));
           }
       }
       return genres;

   }
}
