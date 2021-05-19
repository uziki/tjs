package com.tsystems.javaschool.web;

import com.tsystems.javaschool.model.Role;
import com.tsystems.javaschool.model.User;
import com.tsystems.javaschool.service.user.UserService;
import com.tsystems.javaschool.util.exception.NotUniqEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.tsystems.javaschool.util.ValidationUtil.checkNew;

@Controller
@RequestMapping
public class UserController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request) {
        User user = new User(request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("name"),
                Role.valueOf(request.getParameter("role")));
        log.info("create user {}", user);
        checkNew(user);
        try {
            userService.create(user);
        } catch (NotUniqEmailException e) {
            return "redirect:/register?message=" + e.getMessage();
        }
        return "redirect:/login?message=registred_" + user.getEmail();
    }
}