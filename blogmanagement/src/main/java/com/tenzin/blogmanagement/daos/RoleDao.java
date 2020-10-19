/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.Role;
import java.util.List;


/**
 *
 * @author Tenzin Woesel
 */
public interface RoleDao {

    Role getRoleById(int roleId);
    
    Role getRoleByRole(String role);

    List<Role> getAllRoles();

    Role addRole(Role role);

    void deleteRole(int roleId);

    void updateRole(Role role);

}
