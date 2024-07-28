package com.teamAirlines.flightManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teamAirlines.flightManagementSystem.bean.Feedback;
import com.teamAirlines.flightManagementSystem.dao.FeedbackDao;

@RestController
public class FeedbackController {
	
	@Autowired
	private FeedbackDao feedbackDao;
	
	@GetMapping("/feedback")
	public ModelAndView showNewAirportPage() {
		Feedback feedback = new Feedback();
		ModelAndView mv = new ModelAndView("feedbackPage");
		mv.addObject("feedback",feedback);
		return mv;
	}
	
	@PostMapping("/saveFeedback")
	public ModelAndView saveNewAirportPage(@ModelAttribute ("feedback") Feedback feedback) {
		feedbackDao.save(feedback);
		return new ModelAndView("redirect:/index");
	}
	
	@GetMapping("/viewAllFeedbacks")
	public ModelAndView showViewAllFeedbackPage() {
		List <Feedback> li = feedbackDao.showAllFeedbacks();
		ModelAndView mv = new ModelAndView("viewAllFeedbacks"); 
		mv.addObject("list",li);
		return mv;
	}
	
}
