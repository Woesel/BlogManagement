package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.daos.RoleDaoDB.RoleMapper;
import com.tenzin.blogmanagement.dtos.Role;
import com.tenzin.blogmanagement.dtos.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tenzin Woesel Oct 18, 2020
 */
@Repository
public class UserDaoDB implements UserDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public User getUserById(int userId) {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM User WHERE userId=?";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), userId);
            user.setRoles(getRolesForUser(user.getUserId()));
            return user;
        } catch (DataAccessException ex) {
            
            return null;
        }
    }
    
    @Override
    @Transactional
    public User addUser(User user) {
        final String INSERT_USER = "INSERT INTO User(username, password, firstName, lastName, phone, photoFileName, enabled) "
                + " VALUES (?,?,?,?,?,?,?) ";
        jdbc.update(INSERT_USER,
                user.getUserName(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getPhotoFileName(),
                user.isEnabled());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setUserId(newId);
        insertRole(user);
        
        return user;
        
    }
    
    @Override
    public List<User> getAllUsers() {
        final String SELECT_ALL_USERS = "SELECT * FROM User";
        List<User> users = jdbc.query(SELECT_ALL_USERS, new UserMapper());
        associateRole(users);
        
        return users;
    }
    
    @Override
    public void updateUser(User user) {
        final String UPDATE_USER = "UPDATE User SET username=?, password = ?, "
                + " firstName=?, lastName=?, phone=?, photoFileName=?, enabled=? "
                + " WHERE userId=?";
        jdbc.update(UPDATE_USER,
                user.getUserName(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getPhotoFileName(),
                user.isEnabled(),
                user.getUserId());
        
        final String DELETE_USER_ROLE = "DELETE FROM User_Role WHERE userId=?";
        jdbc.update(DELETE_USER_ROLE, user.getUserId());
        insertRole(user);
        
    }
    
    @Override
    public void deleteUser(int userId) {
        final String DELETE_USER_ROLE = "DELETE FROM User_Role WHERE userId=?";
        final String DELETE_USER = "DELETE FROM User WHERE userId=?";
        jdbc.update(DELETE_USER_ROLE, userId);
        jdbc.update(DELETE_USER, userId);
    }
    
    @Override
    public User getUserByUsername(String userName) {
        try {
            final String SELECT_USER_BY_USERNAME = "SELECT * FROM User WHERE username=?";
            User user = jdbc.queryForObject(SELECT_USER_BY_USERNAME, new UserMapper(), userName);
            user.setRoles(getRolesForUser(user.getUserId()));
            return user;
        } catch (DataAccessException ex) {
            return null;
        }
        
    }

    ///////////////////////////////////////////////////////////////
    //////////////HELPER METHODS
    private void insertRole(User user) {
        final String INSERT_USER_ROLE = "INSERT into User_Role (userId, roleId) VALUES"
                + " (?,?)";
        for (Role role : user.getRoles()) {
            
            jdbc.update(INSERT_USER_ROLE, user.getUserId(), role.getRoleId());
            
        }
    }
    
    private List<Role> getRolesForUser(int userId) throws DataAccessException {
        
        final String SELECT_ROLES_FOR_USER = "    SELECT r.* FROM User_Role ur JOIN Role r ON ur.roleId = r.roleId WHERE ur.userId = ?";
        List<Role> roles = jdbc.query(SELECT_ROLES_FOR_USER, new RoleMapper(), userId);
        return roles;
    }
    
    private void associateRole(List<User> users) {
        for (User user : users) {
            user.setRoles(getRolesForUser(user.getUserId()));
            
        }
    }

    /////////////////////////////////////////////////////////////
    //////////////////////Mapper
    public static final class UserMapper implements RowMapper<User> {
        
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEnabled(rs.getBoolean("enabled"));
            user.setPhone(rs.getString("phone"));
            user.setPhotoFileName(rs.getString("photoFileName"));
            
            return user;
        }
    }
    
}
