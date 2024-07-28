package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.Feedback;

@Repository
@Service
public class FeedbackDaoImpl implements FeedbackDao{

	@Autowired
	private FeedbackRepository repository;
	
	@Override
	public void save(Feedback feedback) {
		repository.save(feedback);
	}

	@Override
	public List<Feedback> showAllFeedbacks() {
		return repository.findAll();
	}

}
