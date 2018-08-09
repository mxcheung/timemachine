package com.maxcheung.timemachine.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StateMachineTest {

	private static final String ACK = "ACK";
	private static final String UNMATCHED = "UNMATCHED";
	private StateMachineService stateMachineService = new StateMachineServiceImpl();

	@Test
	public void testAckToUnMatch() {
	    assertEquals(UNMATCHED,stateMachineService.changeStatus(ACK, UNMATCHED));
	    assertEquals(UNMATCHED,stateMachineService.changeStatus(UNMATCHED,ACK));
	}
	
	@Test
	public void testCheckTransitions()  {
	    String transition1 = stateMachineService.changeStatus(ACK, UNMATCHED);
	    String transition2 = stateMachineService.changeStatus(UNMATCHED, ACK);
	    assertEquals(UNMATCHED,transition1);
	    assertEquals(UNMATCHED,transition2);
	}

}