package com.maxcheung.timemachine.service;

import java.time.LocalDateTime;

public interface TimeMachineService {

	LocalDateTime now();

	void useFixedClockAt(LocalDateTime date);

	void useSystemDefaultZoneClock();

}
