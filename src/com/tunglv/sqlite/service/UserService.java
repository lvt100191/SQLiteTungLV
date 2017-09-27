/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunglv.sqlite.service;

import com.tunglv.sqlite.entity.User;

/**
 *
 * @author TUNGLV
 */
public interface UserService {

    public void insert(User user);

    public void createTable();
    
}
