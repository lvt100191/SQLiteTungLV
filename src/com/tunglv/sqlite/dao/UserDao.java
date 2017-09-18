/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunglv.sqlite.dao;

import com.tunglv.sqlite.db.DBUtil;
import com.tunglv.sqlite.entity.User;
import com.tunglv.sqlite.util.Config;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 *
 * @author TUNGLV
 */
public class UserDao {
    
    static Logger logger = Logger.getLogger(UserDao.class.getName());
    
    public static void createTable() throws SQLException {
        Connection c = null;
        Statement stmt = null;
        try {
            //create table
            c = DBUtil.connectDB(Config.DB_NAME);
            
            stmt = c.createStatement();
            String sql = "CREATE TABLE " + User.TABLE_NAME
                    + "(id INT PRIMARY KEY     NOT NULL,"
                    + " fullname           TEXT, "
                    + " date_of_birth            DATE, "
                    + " address           TEXT, "
                    + " username           TEXT, "
                    + " password           TEXT, "
                    + " create_date            DATE, "
                    + " create_by            INT, "
                    + " expire_date            DATE, "
                    + " modify_date            DATE, "
                    + " modify_by            INT, "
                    + " note         TEXT)";
            stmt.executeUpdate(sql);
            
        } catch (Exception ex) {
            logger.error("them moi nguoi dung error");
        }
        finally {
            stmt.close();
            c.close();
        }
    }
    
    public static void insert(User user) throws SQLException {
        Connection c = null;
        Statement stmt = null;
        
        try {
            c = DBUtil.connectDB(Config.DB_NAME);
            stmt = c.createStatement();
            String sql = "INSERT INTO " + User.TABLE_NAME
                    + "(ID, fullname,date_of_birth,address,username,password,create_date,create_by,expire_date,modify_date,modify_by,note) "
                    + "VALUES (1, 'Lam Viet Tung', '', 'BSD', 'tunglv','123456','','','','','','' );";
            stmt.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            stmt.close();
            c.close();
        }
    }
}
