package com.maxcheung.timemachine.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxcheung.timemachine.service.TimeMachineService;

@Controller
@RestController
@RequestMapping("timeMachine")
public class TimeMachineController {

	private static final Logger LOG = LoggerFactory.getLogger(TimeMachineController.class);

	@Autowired
	private TimeMachineService timeMachineService;

	@RequestMapping(value = "/getNow", method = RequestMethod.GET)
	public @ResponseBody LocalDateTime getNow() {
		LOG.info(" getNow");
		return timeMachineService.now();
	}

	@RequestMapping(value = "/useFixedClockAt", method = RequestMethod.PUT)
	public @ResponseBody void useFixedClockAt(@RequestParam("clockDate") 
											@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime clockDate) {
		LOG.info(" useFixedClockAt");
		timeMachineService.useFixedClockAt(clockDate);
	}

	@RequestMapping(value = "/useSystemDefaultZoneClock", method = RequestMethod.PUT)
	public @ResponseBody void useSystemDefaultZoneClock() {
		//2000-10-31T01:30:00.000-05:00
		LOG.info(" useFixedClockAt");
		timeMachineService.useSystemDefaultZoneClock();
	}

}