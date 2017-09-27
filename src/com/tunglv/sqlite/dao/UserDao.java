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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
                    + "(id INTEGER PRIMARY KEY autoincrement   NOT NULL,"
                    + " fullname           TEXT, "
                    + " date_of_birth            TEXT, "
                    + " address           TEXT, "
                    + " username           TEXT, "
                    + " password           TEXT, "
                    + " create_date            TEXT, "
                    + " create_by            TEXT, "
                    + " expire_date            TEXT, "
                    + " modify_date            TEXT, "
                    + " modify_by            TEXT, "
                    + " note         TEXT, CONSTRAINT username_unique UNIQUE (username))";
            stmt.executeUpdate(sql);

        } catch (Exception ex) {
            logger.error("them moi nguoi dung error");
        } finally {
            stmt.close();
            c.close();
        }
    }

    public static void insert(User user) throws SQLException {
        Connection c = null;
        PreparedStatement pst = null;

        try {
            c = DBUtil.connectDB(Config.DB_NAME);

            String query = "INSERT INTO " + User.TABLE_NAME
                    + "(fullname,date_of_birth,address,username,password,create_date,create_by,expire_date,modify_date,modify_by,note) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
            pst = c.prepareStatement(query);
            pst.setString(1, user.getFullname());
            pst.setString(2, "");
            pst.setString(3, user.getAddress());
            pst.setString(4, user.getUsername());
            pst.setString(5, user.getPassword());
            pst.setString(6, "");
            pst.setString(7, "");
            pst.setString(8, "");
            pst.setString(9, "");
            pst.setString(10, "");
            pst.setString(11, "");
            pst.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            pst.close();
            c.close();
        }
    }
}
