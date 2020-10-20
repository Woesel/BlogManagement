/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.Hashtag;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class HashtagDaoDBTest {
    
    @Autowired
    HashtagDao hashtagDao;

    public HashtagDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of adding and getting hashtag method, of class HashtagDaoDB.
     */
    @Test
    public void testAddAndGetHashtagById() {
        
        Hashtag tag = new Hashtag();
        tag.setName("#Awesome");
        
        tag = hashtagDao.addHashtag(tag);
        
        Hashtag fromDB = hashtagDao.getHashtagById(tag.getHashtagId());
        
        assertEquals(tag, fromDB, "Both hashtag should be same.");
    }

    /**
     * Test of getHashtagByTags method, of class HashtagDaoDB.
     */
    @Test
    public void testGetHashtagByTags() {
    }

    /**
     * Test of getAllHashtags method, of class HashtagDaoDB.
     */
    @Test
    public void testGetAllHashtags() {
    }

    /**
     * Test of addHashtag method, of class HashtagDaoDB.
     */
    @Test
    public void testAddHashtag() {
    }

    /**
     * Test of deleteById method, of class HashtagDaoDB.
     */
    @Test
    public void testDeleteById() {
    }

    /**
     * Test of updateById method, of class HashtagDaoDB.
     */
    @Test
    public void testUpdateById() {
    }

}
