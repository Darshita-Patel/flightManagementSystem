package com.teamAirlines.flightManagementSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamAirlines.flightManagementSystem.bean.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback,String>{

}
