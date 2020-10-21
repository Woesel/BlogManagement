/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.Hashtag;
import java.util.List;
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
public class HashtagDaoDBTest {

    @Autowired
    HashtagDao hashtagDao;

    private Hashtag tagOne;

    public HashtagDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {

        List<Hashtag> tags = hashtagDao.getAllHashtags();
        for (Hashtag tag : tags) {
            hashtagDao.deleteHashtag(tag.getHashtagId());
        }
        tagOne = new Hashtag();
        tagOne.setName("#awesome");
    }

    /**
     * Test of adding and getting hashtag method, of class HashtagDaoDB.
     */
    @Test
    public void testAddAndGetHashtagById() {

        tagOne = hashtagDao.addHashtag(tagOne);

        Hashtag fromDB = hashtagDao.getHashtagById(tagOne.getHashtagId());

        assertEquals(tagOne, fromDB, "Both hashtag should be same.");
    }

    /**
     * Test of getHashtagByTags method, of class HashtagDaoDB.
     */
    @Test
    public void testGetHashtagByTags() {

        tagOne = hashtagDao.addHashtag(tagOne);

        System.out.println(tagOne.getName());

        Hashtag fromDB = hashtagDao.getHashtagByTags(tagOne.getName());
        System.out.println(fromDB);

        assertEquals(tagOne, fromDB, "Both the hashtag should be same.");

    }

    /**
     * Test of getAllHashtags method, of class HashtagDaoDB.
     */
    @Test
    public void testGetAllHashtags() {
        tagOne = hashtagDao.addHashtag(tagOne);

        Hashtag tagTwo = new Hashtag();
        tagTwo.setName("#great");

        tagTwo = hashtagDao.addHashtag(tagTwo);

        List<Hashtag> listOfTags = hashtagDao.getAllHashtags();

        assertNotNull(listOfTags, "The list should not be null");
        assertEquals(listOfTags.size(), 2, " The size of the list should be 2");
        assertTrue(listOfTags.contains(tagOne));
        assertTrue(listOfTags.contains(tagTwo));
    }

    /**
     * Test of deleteHashtag method, of class HashtagDaoDB.
     */
    @Test
    public void testDeleteById() {

        tagOne = hashtagDao.addHashtag(tagOne);

        Hashtag fromDB = hashtagDao.getHashtagById(tagOne.getHashtagId());

        assertNotNull(fromDB, " The hashtag should not be null.");

        //ACT
        hashtagDao.deleteHashtag(tagOne.getHashtagId());

        fromDB = hashtagDao.getHashtagById(tagOne.getHashtagId());

        assertNull(fromDB, "The hashtag should be null. ");

    }

    /**
     * Test of updateHashtag method, of class HashtagDaoDB.
     */
    @Test
    public void testUpdateHashtag() {

        tagOne = hashtagDao.addHashtag(tagOne);

        Hashtag fromDB = hashtagDao.getHashtagById(tagOne.getHashtagId());

        assertEquals(tagOne, fromDB, " THe hashtag should be same.");

        tagOne.setName("#change");

        hashtagDao.updateHashtag(tagOne);

        assertNotEquals(tagOne, fromDB, "The hashtag shouldnt be same.");

        fromDB = hashtagDao.getHashtagById(tagOne.getHashtagId());

        assertEquals(tagOne, fromDB, " The hashtag should be same.");
    }

}
