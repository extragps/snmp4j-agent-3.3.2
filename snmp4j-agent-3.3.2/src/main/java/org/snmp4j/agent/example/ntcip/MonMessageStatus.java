package org.snmp4j.agent.example.ntcip;

import java.awt.Color;

import javax.swing.JLabel;

public class MonMessageStatus extends JLabel implements MonIntegerSetter {
	private Integer maValeur=Integer.valueOf(1);
	/*
	dmsMessageStatus OBJECT-TYPE SYNTAX INTEGER {
		notUsed (1),
		modifying (2),
		validating (3),
		valid (4),
		error (5),
		modifyReq (6),
		validateReq (7),
		notUsedReq (8) }
		*/
	public MonMessageStatus() {
		this.setText("Not used");
		this.setOpaque(true);
	}

	@Override
	public Integer getValue() {
		return maValeur;
	}

	@Override
	public void setValue(Integer maValeur) {
			switch(maValeur) {
			case 6:
				this.maValeur=2;
				this.setBackground(Color.orange);
				this.setText("modifying");
				break;
			case 7:
				this.maValeur=4;
				this.setBackground(Color.green);
				this.setText("valid");
				break;
			}
	}
	
}
