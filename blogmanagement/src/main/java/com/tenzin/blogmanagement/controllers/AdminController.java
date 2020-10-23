package com.tenzin.blogmanagement.controllers;

import com.tenzin.blogmanagement.daos.ImageDao;
import com.tenzin.blogmanagement.daos.RoleDao;
import com.tenzin.blogmanagement.daos.UserDao;
import com.tenzin.blogmanagement.dtos.Role;
import com.tenzin.blogmanagement.dtos.User;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tenzin Woesel Oct 21, 2020
 */
@Controller
public class AdminController {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roles;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ImageDao imageDao;

    private final String USER_UPLOAD_FILE = "admin";

    @GetMapping("/admin")
    public String displayAdminPage(Model model) {
        model.addAttribute("users", userDao.getAllUsers());
        return "admin";
    }

    @PostMapping("/addUser")
    public String addUser(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

        String fileLocation = imageDao.saveImage(file, Long.toString(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)), USER_UPLOAD_FILE);

        User user = new User();
        user.setUserName(request.getParameter("username"));
        user.setPassword(encoder.encode(request.getParameter("password")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setPhone(request.getParameter("phone"));
        user.setEnabled(true);
        user.setPhotoFileName(fileLocation);

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roles.getRoleByRole("ROLE_USER"));
        user.setRoles(userRoles);

        userDao.addUser(user);

        return "redirect:/admin";
    }
    
    @PostMapping("/deleteUser")
    public String deleteUser(Integer id) {
        userDao.deleteUser(id);
        return "redirect:/admin";
    }

}
