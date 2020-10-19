/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.User;
import java.util.List;

/**
 *
 * @author Tenzin Woesel
 */
public interface UserDao {

    User getUserById(int userId);

    User addUser(User user);

    List<User> getAllUsers();

    void updateUserById(User user);

    void deleteUserByUsername(int userId);

    User getUserByUsername(String userName);

}
