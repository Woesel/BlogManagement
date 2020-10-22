
package com.tenzin.blogmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Tenzin Woesel
 * Oct 22, 2020
 */
@Controller
public class SignupController {
    
     @GetMapping("/signup")
    public String showLoginForm() {
        return "signup";
    }

}
