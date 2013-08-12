package com.solvians.activecheckalarmcallback.callback;

import java.io.Serializable;

public class ActivecheckPacket implements Serializable {
	private static final long serialVersionUID = -4631772558439693705L;
	private final ActivecheckSeverity severity;
	private final String senderHost;
	private final String serviceName;
	private final String message;

	public ActivecheckPacket(ActivecheckSeverity severity, String senderHost,
			String serviceName, String message) {
		super();
		this.severity = severity;
		this.senderHost = senderHost;
		this.serviceName = serviceName;
		this.message = message;
	}

	public ActivecheckSeverity getSeverity() {
		return severity;
	}

	public String getSenderHost() {
		return senderHost;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getMessage() {
		return message;
	}
}
