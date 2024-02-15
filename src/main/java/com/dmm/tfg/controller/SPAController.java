package com.dmm.tfg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for handling Single Page Application (SPA) routing in a Spring Boot application.
 * This controller redirects all non-static requests to the root path, allowing client-side routing to handle the path.
 */
@Controller
public class SPAController {
    /**
     * Redirects all non-static (i.e., non-file) requests to the root path ("/").
     * This method supports client-side routing by ensuring that all navigation is handled by the SPA's router.
     *
     * @return A forward directive to the root path, enabling client-side routing to take over.
     */
    @RequestMapping(value = {"/{path:[^\\.]*}"})
    public String redirect() {
        return "forward:/";
    }
}
