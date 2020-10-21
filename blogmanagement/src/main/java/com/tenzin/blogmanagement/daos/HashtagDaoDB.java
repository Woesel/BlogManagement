package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.Hashtag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tenzin Woesel Oct 18, 2020
 */
@Repository
public class HashtagDaoDB implements HashtagDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Hashtag getHashtagById(int hashtagId) {
        try {
            final String SELECT_HASHTAG_BY_ID = "SELECT * FROM Hashtag WHERE hashtagId=?";
            return jdbc.queryForObject(SELECT_HASHTAG_BY_ID, new HashtagMapper(), hashtagId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Hashtag getHashtagByTags(String tag) {
        try {
            final String SELECT_HASHTAG_BY_TAGS = "SELECT * FROM Hashtag WHERE name=?";
            return jdbc.queryForObject(SELECT_HASHTAG_BY_TAGS, new HashtagMapper(), tag);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        final String SELECT_ALL_HASHTAG = "SELECT * FROM Hashtag";
        return jdbc.query(SELECT_ALL_HASHTAG, new HashtagMapper());
    }

    @Override
    public Hashtag addHashtag(Hashtag hashtag) {
        final String INSERT_HASHTAG = "INSERT INTO Hashtag(name) VALUES (?)";
        jdbc.update(INSERT_HASHTAG, hashtag.getName());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hashtag.setHashtagId(newId);

        return hashtag;
    }

    @Override
    public void deleteHashtag(int hashtagId) {
        final String DELETE_BLOG_HASHTAG = "DELETE FROM Blog_Hashtag WHERE hashtagId=?";
        final String DELETE_HASHTAG = "DELETE FROM Hashtag WHERE hashtagId=?";

        jdbc.update(DELETE_BLOG_HASHTAG, hashtagId);
        jdbc.update(DELETE_HASHTAG, hashtagId);
    }

    @Override
    public void updateHashtag(Hashtag hashtag) {
        final String UPDATE_HASHTAG = "UPDATE Hashtag SET name=? WHERE hashtagId=?";
        jdbc.update(UPDATE_HASHTAG, hashtag.getName(), hashtag.getHashtagId());

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////MAPPER
    public static final class HashtagMapper implements RowMapper<Hashtag> {

        @Override
        public Hashtag mapRow(ResultSet rs, int i) throws SQLException {
            Hashtag tag = new Hashtag();
            tag.setHashtagId(rs.getInt("hashtagId"));
            tag.setName(rs.getString("name"));

            return tag;
        }
    }

}
