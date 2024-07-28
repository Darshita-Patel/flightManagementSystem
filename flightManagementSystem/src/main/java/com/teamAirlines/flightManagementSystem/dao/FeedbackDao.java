package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import com.teamAirlines.flightManagementSystem.bean.Feedback;

public interface FeedbackDao {
	public void save(Feedback feedback);
	public List<Feedback> showAllFeedbacks();
}
