package com.maxcheung.timemachine.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

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
	public void testUseFixedClockAt() throws InterruptedException {
	    TimeMachineService timeMachineService = new TimeMachineServiceImpl();
	    timeMachineService.useFixedClockAt(twoWeeksAgo);
	    assertEquals(twoWeeksAgo.toLocalDate(), timeMachineService.getToday());
	    TimeUnit.MILLISECONDS.sleep(10);
	    assertEquals(0, timeMachineService.now().until( twoWeeksAgo, ChronoUnit.MILLIS));
	    assertEquals(twoWeeksAgo, timeMachineService.now());
	    timeMachineService.useSystemDefaultZoneClock();
	    assertEquals(now.toLocalDate(), timeMachineService.getToday());
	}
	
	@Test
	public void testUseRunningClockAt() throws InterruptedException {
	    TimeMachineService timeMachineService = new TimeMachineServiceImpl();
	    timeMachineService.useRunningClockAt(twoWeeksAgo);
	    TimeUnit.MILLISECONDS.sleep(10);
	    assertNotEquals(0, timeMachineService.now().until( twoWeeksAgo, ChronoUnit.MILLIS));
	}

}