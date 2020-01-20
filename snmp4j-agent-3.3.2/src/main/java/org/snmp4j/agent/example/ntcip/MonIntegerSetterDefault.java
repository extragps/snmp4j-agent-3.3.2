package org.snmp4j.agent.example.ntcip;

public class MonIntegerSetterDefault implements MonIntegerSetter {

	private Integer value;
	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void setValue(Integer value) {
		this.value=value;
	}

}
