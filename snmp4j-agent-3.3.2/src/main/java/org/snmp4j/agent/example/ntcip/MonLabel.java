package org.snmp4j.agent.example.ntcip;

import javax.swing.JLabel;

public class MonLabel extends JLabel implements MonIntegerSetter {
	private Integer maValeur=Integer.valueOf(10);

	@Override
	public Integer getValue() {
		return maValeur;
	}

	@Override
	public void setValue(Integer maValeur) {
		if(maValeur!=this.maValeur) {
			this.setText("Hello xavier "+maValeur);
		}
		this.maValeur = maValeur;
	}
	
}
