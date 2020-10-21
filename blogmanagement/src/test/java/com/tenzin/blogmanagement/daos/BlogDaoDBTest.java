/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.Blog;
import com.tenzin.blogmanagement.dtos.Hashtag;
import com.tenzin.blogmanagement.dtos.Role;
import com.tenzin.blogmanagement.dtos.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
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
public class BlogDaoDBTest {

    @Autowired
    BlogDao blogDao;

    @Autowired
    HashtagDao hashtagDao;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    public BlogDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    /**
     * Test of adding and getting of Blog by id method, of class BlogDaoDB.
     */
    @Test
    public void testAddAndGetBlogById() {
        Role role = new Role();
        role.setRole("Admin");

        role = roleDao.addRole(role);

        List<Role> listOfRoles = new ArrayList<>();
        listOfRoles.add(role);

        User userOne = new User();
        userOne.setUserName("first");
        userOne.setFirstName("Ten");
        userOne.setLastName("zin");
        userOne.setPassword("1234");
        userOne.setPhone("1234567890");
        userOne.isEnabled();
        userOne.setPhotoFileName("tent.png");
        userOne.setRoles(listOfRoles);

        userOne = userDao.addUser(userOne);

        Hashtag tagOne = new Hashtag();
        tagOne.setName("#awesome");

        tagOne = hashtagDao.addHashtag(tagOne);

        List<Hashtag> listOftags = new ArrayList<>();
        listOftags.add(tagOne);
        
//        System.out.println(LocalDateTime.now().withNano(0));

        Blog blog = new Blog();
        blog.setTitle("First Blog");
        blog.setContent("Bunch of text.");
        blog.setVerified(true);
        blog.setStaticPage(true);
        blog.setBlogPosted(LocalDateTime.now().withNano(0));
        blog.setExpiryDate(LocalDateTime.now().plusDays(1).withNano(0));
        blog.setBlogCreated(LocalDateTime.now().minusDays(1).withNano(0));
        blog.setPhotoFileName("C://image.png");
        blog.setUser(userOne);
        blog.setTags(listOftags);

        blog = blogDao.addBlog(blog);

        Blog fromDB = blogDao.getBlogById(blog.getBlogId());

        assertEquals(blog, fromDB);

    }

    /**
     * n
     * Test of getBlogByTitle method, of class BlogDaoDB.
     */
    @Test
    public void testGetBlogByTitle() {
    }

    /**
     * Test of addBlog method, of class BlogDaoDB.
     */
    @Test
    public void testAddBlog() {
    }

    /**
     * Test of getAllBlogs method, of class BlogDaoDB.
     */
    @Test
    public void testGetAllBlogs() {
    }

    /**
     * Test of deleteBlog method, of class BlogDaoDB.
     */
    @Test
    public void testDeleteBlog() {
    }

    /**
     * Test of updateBlog method, of class BlogDaoDB.
     */
    @Test
    public void testUpdateBlog() {
    }

}
