package DataOverflowOps;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookStoreController {

  public static boolean writeAuthor(AuthorParser author) throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;

    boolean saved = false;


      try {
        if ((conn = getConnection()) != null) {

          ps = conn.prepareStatement("INSERT INTO author (author_name, author_email, author_url) VALUES (?,?,?)");
          ps.setString(1, author.getName());
          if(author.getEmail().isBlank() || author.getEmail().isEmpty()){
            ps.setString(2, author.getName() + "has no email");
          }else{
            ps.setString(2, author.getEmail());
          }
          if(author.getUrl().isBlank() || author.getUrl().isEmpty()){
            ps.setString(3, author.getName() + "has no email");
          }else{
            ps.setString(3, author.getUrl());
          }

          ps.executeUpdate();

          saved = true;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        try {
          if (ps != null) {
            ps.close();
          }
          if (conn != null) {
            conn.close();
          }
        } catch (SQLException e) {
          System.out.println("Error because: " + e.getMessage());
        }
      }
    return saved;
  }
  private static Connection getConnection(){
    Connection conn = null;
    if (Files.exists(Paths.get("src/Data/BookStore.db"))){
      try {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:src/Data/BookStore.db");
      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
      }
    }
    return conn;
  }

  public static boolean writeCSV(String[] rows){
    Connection conn = null;
    PreparedStatement ps = null;

    boolean saved = false;

    try{
      if((conn = getConnection()) != null){
        ps = conn.prepareStatement("INSERT OR IGNORE INTO book (isbn, book_title, author_name, " +
            "publisher_name)Values (?,?,?,?)");

        ps.setString(1, rows[0]); //isbn
        ps.setString(2, rows[1]); //book title
        ps.setString(3, rows[2]); //publisher name
        ps.setString(4, rows[3]); //author name

        ps.executeUpdate();
        saved = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (ps != null){
          ps.close();
        }
        if (conn != null){
          conn.close();
        }
      } catch (SQLException e) {
        System.out.println("DB still open");
      }
    }
    return saved;
  }
}
