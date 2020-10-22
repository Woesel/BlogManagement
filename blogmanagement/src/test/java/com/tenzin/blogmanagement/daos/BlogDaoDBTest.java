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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    private Role role;
    private List<Role> listOfRoles;
    private User userOne;
    private Hashtag tagOne;
    private List<Hashtag> listOftags;
    private Blog blog;

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

        List<Blog> blogs = blogDao.getAllBlogs();
        for (Blog blog : blogs) {
            blogDao.deleteBlog(blog.getBlogId());
        }
        
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            userDao.deleteUser(user.getUserId());
        }
        
        List<Role> roles = roleDao.getAllRoles();
        for (Role role : roles) {
            roleDao.deleteRole(role.getRoleId());
        }
        
        List<Hashtag> tags= hashtagDao.getAllHashtags();
        for (Hashtag tag : tags) {
            hashtagDao.deleteHashtag(tag.getHashtagId());
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

        userOne = userDao.addUser(userOne);

        tagOne = new Hashtag();
        tagOne.setName("#awesome");

        tagOne = hashtagDao.addHashtag(tagOne);

        listOftags = new ArrayList<>();
        listOftags.add(tagOne);

        blog = new Blog();
        blog.setTitle("First Blog");
        blog.setContent("Bunch of text.");
        blog.setVerified(true);
        blog.setStaticPage(true);
        blog.setBlogPosted(LocalDateTime.now().withNano(0));
        blog.setExpiryDate(LocalDateTime.now().plusDays(1).withNano(0));
        blog.setBlogCreated(LocalDateTime.now().minusDays(2).withNano(0));
        blog.setPhotoFileName("C://image.png");
        blog.setUser(userOne);
        blog.setTags(listOftags);

    }

    /**
     * Test of adding and getting of Blog by id method, of class BlogDaoDB.
     */
    @Test
    public void testAddAndGetBlogById() {

//        System.out.println(LocalDateTime.now().withNano(0));
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

        blog = blogDao.addBlog(blog);

        Blog fromDB = blogDao.getBlogByTitle("First Blog");

        assertEquals(blog, fromDB);

    }

    /**
     * Test of getAllBlogs method, of class BlogDaoDB.
     */
    @Test
    public void testGetAllBlogs() {

        blog = blogDao.addBlog(blog);

        Blog blog2 = new Blog();
        blog2.setTitle("First Blog");
        blog2.setContent("Bunch of text.");
        blog2.setVerified(true);
        blog2.setStaticPage(true);
        blog2.setBlogPosted(LocalDateTime.now().withNano(0));
        blog2.setExpiryDate(LocalDateTime.now().plusDays(1).withNano(0));
        blog2.setBlogCreated(LocalDateTime.now().minusDays(2).withNano(0));
        blog2.setPhotoFileName("C://image.png");
        blog2.setUser(userOne);

        blog2 = blogDao.addBlog(blog2);

        List<Blog> listOfBlogs = blogDao.getAllBlogs();
        System.out.println("This is the list" + listOfBlogs);

        assertNotNull(listOfBlogs, "The list should not be empty.");
        assertEquals(listOfBlogs.size(), 2, " The size of the list should be 2.");
        assertTrue(listOfBlogs.contains(blog));
        System.out.println(blog2);
        assertTrue(listOfBlogs.contains(blog2));

    }

    /**
     * Test of deleteBlog method, of class BlogDaoDB.
     */
    @Test
    public void testDeleteBlog() {
        blog = blogDao.addBlog(blog);

        Blog fromDB = blogDao.getBlogById(blog.getBlogId());

        assertNotNull(fromDB);

        //ACT
        blogDao.deleteBlog(blog.getBlogId());

        fromDB = blogDao.getBlogById(blog.getBlogId());

        assertNull(fromDB);
    }

    /**
     * Test of updateBlog method, of class BlogDaoDB.
     */
    @Test
    public void testUpdateBlog() {

        blog = blogDao.addBlog(blog);

        Blog fromDB = blogDao.getBlogById(blog.getBlogId());

        assertEquals(blog, fromDB);

        //ACT
        blog.setContent("New block of text.");
        blog.setTitle("New Edited Blog");

        blogDao.updateBlog(blog);

        assertNotEquals(blog, fromDB);

        fromDB = blogDao.getBlogById(blog.getBlogId());

        assertEquals(blog, fromDB);
    }

}
