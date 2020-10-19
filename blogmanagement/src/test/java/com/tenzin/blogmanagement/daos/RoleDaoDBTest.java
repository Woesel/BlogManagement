/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.Role;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Tenzin Woesel
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleDaoDBTest {

    @Autowired
    RoleDao roleDao;

    public RoleDaoDBTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

        List<Role> roles = roleDao.getAllRoles();
        for (Role role : roles) {
            roleDao.deleteRole(role.getRoleId());
        }

    }

    /**
     * Test of getRoleById method, of class RoleDaoDB.
     */
    @Test
    public void testAddAndGetRoleById() {

        Role role = new Role();
        role.setRole("Admin");

        role = roleDao.addRole(role);

        Role fromDB = roleDao.getRoleById(role.getRoleId());

        assertEquals(role, fromDB);
    }

    /**
     * Test of getRoleByRole method, of class RoleDaoDB.
     */
    @Test
    public void testGetAllRoleByRole() {
        //ARRANGE
        Role role = new Role();
        role.setRole("User");

        role = roleDao.addRole(role);
        //ACT
        Role fromDB = roleDao.getRoleByRole(role.getRole());
        //ASSERT
        assertEquals(role, fromDB);

    }

    /**
     * Test of getAllRoles method, of class RoleDaoDB.
     */
    @Test
    public void testGetAllRoles() {

        //ARRANGE
        Role role = new Role();
        role.setRole("User");
        roleDao.addRole(role);

        Role role2 = new Role();
        role2.setRole("Second User");
        roleDao.addRole(role2);

        //ACT
        List<Role> roles = roleDao.getAllRoles();

        //ASSERT
        assertEquals(2, roles.size());
        assertFalse(roles.isEmpty());

    }

    /**
     * Test of deleteRole method, of class RoleDaoDB.
     */
    @Test
    public void testDeleteRole() {

        //ARRANGE
        Role role = new Role();
        role.setRole("User");

        role = roleDao.addRole(role);

        //ACT
        Role fromDB = roleDao.getRoleById(role.getRoleId());
        assertEquals(role, fromDB);

        roleDao.deleteRole(role.getRoleId());

        fromDB = roleDao.getRoleById(role.getRoleId());
        //ASSERT
        assertNull(fromDB);
    }

    /**
     * Test of updateRole method, of class RoleDaoDB.
     */
    @Test
    public void testUpdateRole() {

        //ARRANGE
        Role role = new Role();
        role.setRole("User");
        role = roleDao.addRole(role);

        Role fromDB = roleDao.getRoleById(role.getRoleId());
        assertEquals(role, fromDB);

        //ACT
        role.setRole("Admin");
        roleDao.updateRole(role);

        //ASSERT
        assertNotEquals(role, fromDB);

        fromDB = roleDao.getRoleById(role.getRoleId());

        assertEquals(role, fromDB);
    }

}
