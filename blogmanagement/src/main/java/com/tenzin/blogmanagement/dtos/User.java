package com.tenzin.blogmanagement.dtos;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Tenzin Woesel Oct 17, 2020
 */
public class User {

    private int userId;

    @NotBlank(message = "UserName must not be empty.")
    @Size(max = 45, message = "UserName must be less than 45 characters.")
    private String userName;
    @NotBlank(message = "First name must not be empty.")
    @Size(max = 45, message = "First name must be less than 45 characters.")
    private String firstName;
    @NotBlank(message = "Last name must not be empty.")
    @Size(max = 45, message = "Last name must be less than 45 characters.")
    private String lastName;
    @NotBlank(message = "Password must not be empty.")
    @Size(max = 45, message = "Password must be less than 45 characters.")
    private String password;
    private String photoFileName;
    @Size(max = 10, min = 10, message = "Phone number should be of 10 digits.")
    private String phone;
    private boolean enabled;
    private List<Role> roles;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.userId;
        hash = 23 * hash + Objects.hashCode(this.userName);
        hash = 23 * hash + Objects.hashCode(this.firstName);
        hash = 23 * hash + Objects.hashCode(this.lastName);
        hash = 23 * hash + Objects.hashCode(this.password);
        hash = 23 * hash + Objects.hashCode(this.photoFileName);
        hash = 23 * hash + Objects.hashCode(this.phone);
        hash = 23 * hash + (this.enabled ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.roles);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.photoFileName, other.photoFileName)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", photoFileName=" + photoFileName + ", phone=" + phone + ", enabled=" + enabled + ", roles=" + roles + '}';
    }

}
