graylog2-plugin-alarmcallback-activecheck
=========================================

Activecheck Alarm Plugin for Graylog2

Alarm callback that is submitting check results to an Activecheck service.

Configuration needed:
- the Nagios host object for which the check is submitted
- the host:port combination of the activecheck service

The plugin will submit a result for the service "Stream: streamName" on configured host.

