package com.maxcheung.timemachine.service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

@Service
public class TimeMachineServiceImpl implements TimeMachineService {

	private static Clock clock = Clock.systemDefaultZone();
	private static ZoneId zoneId = ZoneId.systemDefault();

	
	@Override
	public  LocalDateTime now() {
		return LocalDateTime.now(getClock());
	}

	@Override
	public LocalDate getToday() {
		return now().toLocalDate();
	}
	
	
	@Override
	public  void useFixedClockAt(LocalDateTime date) {
		clock = Clock.fixed(date.atZone(zoneId).toInstant(), zoneId);
	}

	@Override
	public  void useSystemDefaultZoneClock() {
		clock = Clock.systemDefaultZone();
	}

	private static Clock getClock() {
		return clock;
	}



}
