package com.sortnumbers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {
	/**
	 * @returns the "SortNumber" which is the file name of JSP.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage() {
		return "SortNumber";
	}
}