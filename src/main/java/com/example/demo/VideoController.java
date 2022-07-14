package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VideoController {
	@GetMapping("/video")
	public ModelAndView video(ModelAndView mv) {
		mv.setViewName("video");
		return mv;
	}
}
