package possg.com.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import possg.com.a.service.TextService;

@RestController
public class TextController {
	
	@Autowired
	TextService service;

}
