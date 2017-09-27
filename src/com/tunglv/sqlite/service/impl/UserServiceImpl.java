/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunglv.sqlite.service.impl;

import com.tunglv.sqlite.dao.UserDao;
import com.tunglv.sqlite.entity.User;
import com.tunglv.sqlite.service.UserService;
import com.tunglv.sqlite.ui.user.UserInfo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TUNGLV
 */
public class UserServiceImpl implements UserService {

    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public void insert(User user) {
        UserDao dao = new UserDao();
        try {
            dao.insert(user);
        } catch (Exception ex) {
            logger.info("UserServiceImpl.insert() Exception" + ex.getMessage());
        }
    }

    @Override
    public void createTable() {
        UserDao dao = new UserDao();
        try {
            dao.createTable();
        } catch (Exception ex) {
            logger.info("UserServiceImpl.createTable() Exception" + ex.getMessage());
        }
    }

}
