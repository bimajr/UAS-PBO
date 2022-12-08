package models;

import java.sql.SQLException;

import database.Database;

public class Film extends Database {
    // Constructor untuk Connect ke Database
    public Film() throws ClassNotFoundException, SQLException {
        super();
    }

    // Create
    public void insert(String judul, String genre, int tiket) throws SQLException {
        String sql = String.format("INSERT INTO movie (judul, genre, tiket) VALUE ('%s', '%s', %d)", judul, genre,
                tiket);
        this.setQuery(sql);
        this.execute();
    }

    // Read
    public void getAll() throws SQLException {
        String sql = "SELECT * FROM movie";
        this.setQuery(sql);
        this.take();
    }
    
    // Update
    public void update(int id, String judul, String genre, int tiket) throws SQLException {
        String sql = String.format("UPDATE movie SET judul = '%s', genre = '%s', tiket = %d WHERE id = %d",
                judul, genre, tiket, id);
        this.setQuery(sql);
        this.execute();
    }

    // Delete
    public void delete(int id) throws SQLException {
        String sql = String.format("DELETE FROM movie WHERE id = %d", id);
        this.setQuery(sql);
        this.execute();
    }

    // Validation untuk mencegah redudansi data
    public boolean check(String judul) throws SQLException {
        getAll();
        while (this.value.next()) {
            if (this.value.getString("judul").equals(judul)) {
                return false;
            }
        }
        return true;
    }

    public String[][] show() throws SQLException {
        String[][] data = new String[this.len()][4];
        getAll();
        this.take();
        int i = 0;
        while (this.value.next()) {
            data[i][0] =  Integer.toString(this.value.getInt("id"));
            data[i][1] = this.value.getString("judul");
            data[i][2] = this.value.getString("genre");
            data[i][3] = Integer.toString(this.value.getInt("tiket"));
            i++;
        }
        return data;
    }
    
    public int len() throws SQLException {
        getAll();
        this.take();
        int i = 0;
        while (this.value.next()) {
            i++;
        }
        return i;
    }
}
