package com.sortnumbers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// the angular component used for easy data binding,low memory consumption,increase speed of application
@Controller
public class WebController {
	/**
	 * @returns the "SortNumber" which is the file name of JSP.
	 */

	@GetMapping(value = "/")
	public String homePage() {
		return "sortNumber";
	}
}