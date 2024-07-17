package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import com.teamAirlines.flightManagementSystem.bean.Route;

public interface RouteDao {
	public void addRoute(Route route);
	public List<Route> showAllRoutes();
	public Route showRoute(Long id);
	public List<Long> findAllRouteId();
	public Route findRouteBySourceAndDestination(String source, String destination);
	public Long generateRouteId();
}
