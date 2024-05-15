package com.example.springProject.URLShortener;

import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class URLShortenerDAO{

    private JdbcTemplate template;

    public JdbcTemplate getTemplate(){
        return template;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }

    public int save(URL url){
        String query = "insert into URLMODEL (id, longURL, shortURL) values (?,?,?)";
        int rowsAffected = 0;
        try{
            rowsAffected = template.update(query, url.getId(), url.getLongURL(), url.getShortURL());
        }
        catch (Exception e){
            System.out.println("ERROR - " + e.getMessage());
        }
        finally {
            return rowsAffected;
        }
    }

    public URL fetch(URL url){
        String query = "select * from URLMODEL where longUrl = ?";
        RowMapper<URL> mapper = new RowMapper<URL>() {
            @Override
            public URL mapRow(ResultSet rs, int rowNum) throws SQLException {
                URL urlObj = new URL();
                urlObj.setId(rs.getLong(1));
                urlObj.setLongURL(rs.getString(2));
                urlObj.setShortURL(rs.getString(3));
                return urlObj;
            }
        };
        System.out.println("Making Fetch query!!");
        URL fetchedUrl = new URL();
        try {
            fetchedUrl = template.queryForObject(query, mapper, url.getLongURL());
        }
        catch(Exception e){
            System.out.println("Error - "  + e);
        }
        finally {
            return fetchedUrl;
        }
    }

    public List<URL> fetchAll(){
        String query = "select * from URLMODEL";
        RowMapper<URL> mapper = new RowMapper<URL>() {
            @Override
            public URL mapRow(ResultSet rs, int rowNum) throws SQLException {
                URL urlObj = new URL();
                urlObj.setId(rs.getLong(1));
                urlObj.setLongURL(rs.getString(2));
                urlObj.setShortURL(rs.getString(3));
                return urlObj;
            }
        };
        List<URL> urls = new List<URL>();
        try{
            urls = template.query(query, mapper);
        }
        catch (Exception e){
            System.out.println("ERROR - " + e.getMessage());
        }
        finally {
            return urls;
        }
    }

    public int delete(URL url){
        String query = "delete from URLMODEL where longUrl = ?";
        int rowsAffected = 0;
        try{
            rowsAffected = template.update(query, url.getLongURL());
        }
        catch (Exception e){
            System.out.println("ERROR - " + e.getMessage());
        }
        finally {
            return rowsAffected;
        }
    }

    public int deleteAll(){
        String query = "delete from URLMODEL";
        int rowsAffected = 0;
        try{
            rowsAffected = template.update(query);
        }
        catch (Exception e){
            System.out.println("ERROR - " + e.getMessage());
        }
        finally {
            return rowsAffected;
        }
    }
}
