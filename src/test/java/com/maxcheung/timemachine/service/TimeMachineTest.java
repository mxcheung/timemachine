package com.maxcheung.timemachine.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class TimeMachineTest {

    private LocalDateTime now;
    private  LocalDateTime twoWeeksAgo;

	@Before
	public void setup() {
		now = LocalDateTime.now();
		twoWeeksAgo = now.minusWeeks(2);
	}

	@Test
	public void test() {
	    TimeMachineService timeMachineService = new TimeMachineServiceImpl();
	    timeMachineService.useFixedClockAt(twoWeeksAgo);
	    assertEquals(twoWeeksAgo.toLocalDate(), timeMachineService.getToday());
	    timeMachineService.useSystemDefaultZoneClock();
	    assertEquals(now.toLocalDate(), timeMachineService.getToday());
	}
}