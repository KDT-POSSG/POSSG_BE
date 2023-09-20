package possg.com.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import possg.com.a.service.PointService;

@RestController
public class PointController {
	
	@Autowired
	PointService service;
	
	
}
