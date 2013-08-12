package com.solvians.activecheckalarmcallback.callback;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.graylog2.plugin.alarms.Alarm;
import org.graylog2.plugin.alarms.callbacks.AlarmCallbackException;

import com.solvians.activecheck.library.ActivecheckPacket;
import com.solvians.activecheck.library.ActivecheckSeverity;

/**
 * @author Frederik Happel <frederik.happel@solvians.com>
 */
public class ActivecheckTrigger {
	private final String senderHostName;
	private final String host;
	private final int port;

	public ActivecheckTrigger(String senderHostName, String host) {
		this.senderHostName = senderHostName;

		String[] nsca_host_parts = host.split(":");
		this.host = nsca_host_parts[0];
		this.port = nsca_host_parts.length > 1 ? Integer
				.parseInt(nsca_host_parts[0]) : 5623;
	}

	public void trigger(Alarm alarm) throws AlarmCallbackException {
		int limit = alarm.getStream().getAlarmMessageLimit();
		int current = alarm.getMessageCount();
		ActivecheckPacket packet = new ActivecheckPacket(
				limit * 2 > current ? ActivecheckSeverity.WARNING
						: ActivecheckSeverity.CRITICAL, senderHostName,
				"Stream: " + alarm.getStream().getTitle(),
				alarm.getDescription());
		try {
			Socket socket = new Socket(host, port);
			try {
				ObjectOutputStream objectOutput = new ObjectOutputStream(
						socket.getOutputStream());
				objectOutput.writeObject(packet);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				socket.close();
			}
		} catch (Exception e) {
			throw new AlarmCallbackException("Could not submit alert to "
					+ host + ":" + port + " . IOException");
		}
	}
}