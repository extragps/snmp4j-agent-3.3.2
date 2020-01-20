module org.snmp4j.agent {
	exports org.snmp4j.agent.io;
	exports org.snmp4j.agent.mo.ext;
	exports org.snmp4j.agent.mo.snmp4j.example;
	exports org.snmp4j.agent.mo;
	exports org.snmp4j.agent.mo.snmp.dh;
	exports org.snmp4j.agent.test;
	exports org.snmp4j.agent.security;
	exports org.snmp4j.agent.mo.snmp.tc;
	exports org.snmp4j.agent;
	exports org.snmp4j.agent.example;
	exports org.snmp4j.agent.version;
	exports org.snmp4j.agent.example.ntcip;
	exports org.snmp4j.agent.util;
	exports org.snmp4j.agent.mo.snmp4j;
	exports org.snmp4j.agent.mo.snmp.smi;
	exports org.snmp4j.agent.mo.lock;
	exports org.snmp4j.agent.mo.snmp;
	exports org.snmp4j.agent.cfg;
	exports org.snmp4j.agent.io.prop;
	exports org.snmp4j.agent.mo.util;
	exports org.snmp4j.agent.request;

	requires java.desktop;
	requires org.snmp4j;
}