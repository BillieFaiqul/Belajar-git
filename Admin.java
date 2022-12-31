/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author USER
 */
import java.util.ArrayList;
import java.sql.*;
public class Admin {
    private int id_admin;
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(int id_admin, String username, String password) {
        this.id_admin = id_admin;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id_admin;
    }

    public void setId(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
     public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    

   
    
    public  Admin getById(int id_admin) {
        Admin adm = new Admin();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM Admin "
                                            + " WHERE id_admin = '" + id_admin + "'");
        try 
        {
            while(rs.next()) 
            {
                adm = new Admin();
                adm.setId(rs.getInt("id_admin"));
                adm.setUsername(rs.getString("username"));
                adm.setPassword(rs.getString("password"));
            }
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
        return adm;
    }
    public ArrayList<Admin> getAll() {
        ArrayList<Admin> ListAdmin = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM admin");
        
        try 
        {
            while(rs.next()) 
            {
               Admin adm = new Admin();
                adm.setId(rs.getInt("id_admin"));
                adm.setUsername(rs.getString("username"));
                adm.setPassword(rs.getString("password"));
                
                ListAdmin.add(adm);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return ListAdmin;
    }
    public ArrayList<Admin> search(String keyword) {
        ArrayList<Admin> ListAdmin = new ArrayList();
        
        String sql = "SELECT * FROM admin WHERE "
                    + "    username LIKE '%" + keyword + "%' ";
        
        ResultSet rs = DBHelper.selectQuery(sql);
        
        try 
        {
            while(rs.next()) {
                 Admin adm = new Admin();
                adm.setId(rs.getInt("id_admin"));
                adm.setUsername(rs.getString("username"));
                adm.setPassword(rs.getString("password"));
                
                ListAdmin.add(adm);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return ListAdmin;
    }
    public void save() {
        if(getById(id_admin).getId() == 0) 
        {
            String SQL = "INSERT INTO admin (id_admin, username, password) VALUES("
                    + "     '" + this.id_admin + "', "
                    + "     '" + this.username + "', "
                    + "     '" + this.password + "' "
                    + "     )";
            this.id_admin = DBHelper.insertQueryGetId(SQL);
        }
        else 
        {
            String SQL = "UPDATE admin SET "
                    + "     id_admin       = '" + this.id_admin + "', "
                    + "     username = '" + this.username + "', "
                    + "     password = '" + this.password + "' "
                    + "     WHERE id_admin = '" + this.id_admin + "'";
            DBHelper.executeQuery(SQL);
        }
        
        
    }
    public void hapus(){
        String SQL = "DELETE FROM admin WHERE id_admin = '" + this.id_admin + "'";
        DBHelper.executeQuery(SQL);
    }
    
     public boolean login(String username, String password){
        boolean berhasil = true;
        ResultSet rs = DBHelper.selectQuery("SELECT id_admin AS id_admin FROM admin WHERE username = '"+username+"' AND password = '"+password+"'");
        try
        {
            if(rs.next()) {
                this.id_admin=(rs.getInt("id_admin"));
                this.username=(rs.getString("username"));
                this.password=(rs.getString("password"));
                berhasil = true;
            }else{
                berhasil = false;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return berhasil;
    }
    
}
