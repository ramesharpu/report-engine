package com.redhat.reportengine.agent.api;

import java.util.Date;
import org.apache.log4j.Logger;
import org.hyperic.sigar.SigarException;

import com.redhat.reportengine.agent.rest.mapper.AgentDetails;

/**
 * @author jkandasa@redhat.com (Jeeva Kandasamy)
 * Jun 27, 2013
 */
public class AgentInfo {
	private static final Logger _logger = Logger.getLogger(AgentInfo.class.getName());
	
	public static AgentDetails getAgentDetails(){
		AgentDetails agent = new AgentDetails();
		agent.setDate(new Date());
		agent.setIp(Network.getNetworkInfo().getNetInterfaceConfig().getAddress());
		try {
			agent.setHostName(SigarUtils.getSigar().getNetInfo().getHostName());
		} catch (SigarException ex) {
			_logger.warn("Exception, ", ex);
		}
		agent.setName(Network.getNetworkInfo().getNetInfo().getHostName());
		agent.setCpu(Cpu.getCpuInfo());
		agent.setSigarDetail(SigarUtils.getSigarDetail());
		return agent;
	}
}