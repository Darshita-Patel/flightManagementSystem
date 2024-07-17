package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.Route;

@Repository
@Service
public class RouteDaoImpl implements RouteDao {
	
	@Autowired
	private RouteRepository repository;
	
	@Override
	public void addRoute(Route route) {
		repository.save(route);
	}

	@Override
	public Route showRoute(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Route> showAllRoutes() {
		return repository.findAll();
	}

	@Override
	public List<Long> findAllRouteId() {
		return repository.findAllRouteId();
	}

	@Override
	public Route findRouteBySourceAndDestination(String source, String destination) {
		return repository.findRouteBySourceAndDestination(source, destination);
	}

	@Override
	public Long generateRouteId() {
		Long val = repository.findLastRouteId();
		if(val==null) 
			val=101L;
		else
			val=val+1;
		
		return val;
	}

}
