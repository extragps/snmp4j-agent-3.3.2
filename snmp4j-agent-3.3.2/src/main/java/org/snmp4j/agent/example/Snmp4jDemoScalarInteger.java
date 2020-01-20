package org.snmp4j.agent.example;

import org.snmp4j.agent.MOAccess;
import org.snmp4j.agent.example.ntcip.MonIntegerSetter;
import org.snmp4j.agent.example.ntcip.MonIntegerSetterDefault;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.agent.request.SubRequest;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

public class Snmp4jDemoScalarInteger extends MOScalar<Integer32> {
	private MonIntegerSetter setter = null;

	public Snmp4jDemoScalarInteger(OID oid, MOAccess access) {
		this(oid, access, new MonIntegerSetterDefault());
	}

	public Snmp4jDemoScalarInteger(OID oid, MOAccess access, MonIntegerSetter set) {
		super(oid, access, new Integer32());
		setter = set;
	}


	public int isValueOK(SubRequest<?> request) {
		Variable newValue = request.getVariableBinding().getVariable();
		int valueOK = super.isValueOK(request);
		// --AgentGen BEGIN=snmp4jDemoScalar::isValueOK
		// --AgentGen END
		return valueOK;
	}

	public Integer32 getValue() {
		// --AgentGen BEGIN=snmp4jDemoScalar::getValue
		// --AgentGen END
		super.setValue(new Integer32(setter.getValue()));
		return super.getValue();
	}

	public int setValue(Integer32 newValue) {
		// --AgentGen BEGIN=snmp4jDemoScalar::setValue
		// --AgentGen END
		setter.setValue(newValue.toInt());
		return super.setValue(newValue);
	}

	// --AgentGen BEGIN=snmp4jDemoScalar::_METHODS
	// --AgentGen END

}