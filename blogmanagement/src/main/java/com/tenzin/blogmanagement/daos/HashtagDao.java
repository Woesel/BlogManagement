/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.Hashtag;
import java.util.List;

/**
 *
 * @author Tenzin Woesel
 */
public interface HashtagDao {

    Hashtag getHashtagById(int hashtagId);

    Hashtag getHashtagByTags(String tag);

    List<Hashtag> getAllHashtags();

    Hashtag addHashtag(Hashtag hashtag);

    void deleteById(int hashtagId);

    void updateById(int hashtagId);

}
