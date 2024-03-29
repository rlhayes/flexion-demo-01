/*
 * Copyright 2002-2010 the original author or authors
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package com.flexion.demo.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private List<String> processed = new ArrayList<String>();
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value="/")
    public String home(Model model) {

    	model.addAttribute("processed", processed);
        return "home";
    }

    public synchronized void note(Object x) {
    	logger.debug("Noting {}", x);
    	processed.add(String.valueOf(x));
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value="/ajax")
    public String ajaxCall(Model model) {

        logger.info("Ajax");

        return "processingLog";

    }
}

