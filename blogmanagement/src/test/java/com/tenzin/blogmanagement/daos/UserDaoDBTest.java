/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.Role;
import com.tenzin.blogmanagement.dtos.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
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
public class UserDaoDBTest {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    private Role role;
    private List<Role> listOfRoles;
    private User userOne;

    public UserDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            userDao.deleteUser(user.getUserId());
        }

        List<Role> roles = roleDao.getAllRoles();
        for (Role role : roles) {
            roleDao.deleteRole(role.getRoleId());
        }

        role = new Role();
        role.setRole("Admin");

        role = roleDao.addRole(role);

        listOfRoles = new ArrayList<>();
        listOfRoles.add(role);

        userOne = new User();
        userOne.setUserName("first");
        userOne.setFirstName("Ten");
        userOne.setLastName("zin");
        userOne.setPassword("1234");
        userOne.setPhone("1234567890");
        userOne.isEnabled();
        userOne.setPhotoFileName("tent.png");
        userOne.setRoles(listOfRoles);

    }

    /**
     * Test of adding and getting an user method, of class UserDaoDB.
     */
    @Test
    public void testAddAndGetUserById() {

        userOne = userDao.addUser(userOne);
        //System.out.println(user.getUserId());
        User fromDB = userDao.getUserById(userOne.getUserId());
        //System.out.println(fromDB);

        assertEquals(userOne, fromDB);

    }

    /**
     * Test of getAllUsers method, of class UserDaoDB.
     */
    @Test
    public void testGetAllUsers() {

        userOne = userDao.addUser(userOne);

        User userTwo = new User();
        userTwo = new User();
        userTwo.setUserName("second");
        userTwo.setFirstName("Kel");
        userTwo.setLastName("sang");
        userTwo.setPassword("6789");
        userTwo.setPhone("0987654321");
        userTwo.isEnabled();
        userTwo.setPhotoFileName("tent.png");
        userTwo.setRoles(listOfRoles);

        userTwo = userDao.addUser(userTwo);

        List<User> users = userDao.getAllUsers();

        assertNotNull(users, "The list should not be null.");
        assertEquals(users.size(), 2, "The list should have 2 users");
        assertTrue(users.contains(userOne), "List should contain userOne");
        assertTrue(users.contains(userTwo), "List should contain userTwo");

    }

    /**
     * Test of updateUser method, of class UserDaoDB.
     */
    @Test
    public void testUpdateUserById() {

        userOne = userDao.addUser(userOne);

        User fromDB = userDao.getUserById(userOne.getUserId());

        assertEquals(userOne, fromDB);

        userOne.setFirstName("Peter");
        userOne.setLastName("Parker");
        userOne.setPassword("0000");

        userDao.updateUser(userOne);

        assertNotEquals(userOne, fromDB);

        fromDB = userDao.getUserById(userOne.getUserId());

        assertEquals(userOne, fromDB);

    }

    /**
     * Test of deleteUser method, of class UserDaoDB.
     */
    @Test
    public void testDeleteUser() {
        //ARRANGE
        userOne = userDao.addUser(userOne);
        
        User fromDB = userDao.getUserById(userOne.getUserId());
        
        assertNotNull(fromDB, "User should not be null");
        
        //ACT
        userDao.deleteUser(userOne.getUserId());
        
        fromDB = userDao.getUserById(userOne.getUserId());
        
        assertNull(fromDB, "User should be null");
        
    }

    /**
     * Test of getUserByUsername method, of class UserDaoDB.
     */
    @Test
    public void testGetUserByUsername() {
        //Arrange
        userOne = userDao.addUser(userOne);
        
        //ACT
        User fromDB = userDao.getUserByUsername(userOne.getUserName());
        
        assertEquals(userOne, fromDB, "Both the user should be same");
        
    }

}
