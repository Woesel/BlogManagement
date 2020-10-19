package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
public class RoleDaoDB implements RoleDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Role getRoleById(int roleId) {
        try {
            final String SELECT_ROLE_BY_ID = "SELECT * FROM Role WHERE roleId=?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ID, new RoleMapper(), roleId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Role getRoleByRole(String role) {
        try {
            final String SELECT_ROLE_BY_ROLE = "SELECT * FROM Role WHERE role=? ";
            return jdbc.queryForObject(SELECT_ROLE_BY_ROLE, new RoleMapper(), role);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Role> getAllRoles() {
        final String SELECT_ALL_ROLES = "SELECT * FROM Role";
        return jdbc.query(SELECT_ALL_ROLES, new RoleMapper());
    }

    @Override
    @Transactional
    public Role addRole(Role role) {

        final String INSERT_ROLE = "INSERT INTO Role (role) VALUES(?)";
        jdbc.update(INSERT_ROLE, role.getRole());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        role.setRoleId(newId);

        return role;
    }

    @Override
    public void deleteRole(int roleId) {
        final String DELETE_USER_ROLE = "DELETE FROM User_Role WHERE roleId=?";
        final String DELETE_ROLE = "DELETE FROM Role WHERE roleId=?";

        jdbc.update(DELETE_USER_ROLE, roleId);
        jdbc.update(DELETE_ROLE, roleId);

    }

    @Override
    public void updateRole(Role role) {
        final String UPDATE_ROLE = "UPDATE Role SET role = ? WHERE roleId = ? ";
        jdbc.update(UPDATE_ROLE, role.getRole(), role.getRoleId());
    }

    ////////////////////////////////////////////////////////
    ////////////Mapper
    public static final class RoleMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int i) throws SQLException {
            Role role = new Role();
            role.setRoleId(rs.getInt("roleId"));
            role.setRole(rs.getString("role"));
            return role;
        }
    }

}
