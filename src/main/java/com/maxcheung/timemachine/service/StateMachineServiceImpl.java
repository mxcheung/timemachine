package com.maxcheung.timemachine.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StateMachineServiceImpl implements StateMachineService {

	private static final Logger LOG = LoggerFactory.getLogger(StateMachineServiceImpl.class);

	private static final String ACK = "ACK";
	private static final String UNMATCHED = "UNMATCHED";
	
	private Map<String, Set <String>> invalidTransitions = getIllegalTransitions();

	@Override
	public String changeStatus(String from, String to) {
		Set<String> invalidStates = invalidTransitions.getOrDefault(from, new HashSet<String>());
		String newStatus = from;
		if (invalidStates.contains(to)) {
			String msg = String.format("Illegal transition detected from : %s to : %s", from, to);
			LOG.error(msg);
		//    throw new IllegalStateException(msg);
		} else {
			newStatus = to;
		}
		return newStatus;
		
	}

	private Map<String, Set <String>> getIllegalTransitions() {
		Map<String, Set <String>> invalidTransitions = new HashMap<String, Set <String>>();
		invalidTransitions.put(ACK, new HashSet<String>());
		invalidTransitions.put(UNMATCHED, new HashSet<String>(Arrays.asList(ACK)));
		return invalidTransitions;
	}

}
