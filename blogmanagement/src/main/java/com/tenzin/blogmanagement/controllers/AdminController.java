
package com.tenzin.blogmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Tenzin Woesel
 * Oct 21, 2020
 */
@Controller
public class AdminController {
    
    @GetMapping("/admin")
    public String displayAdminPage() {
        return "admin";
    }
}
