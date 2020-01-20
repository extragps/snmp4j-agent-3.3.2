package org.snmp4j.agent.example;

import org.snmp4j.agent.MOAccess;
import org.snmp4j.agent.mo.MOColumn;
import org.snmp4j.agent.mo.MOMutableColumn;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.agent.mo.snmp.smi.Constraint;
import org.snmp4j.agent.mo.snmp.smi.ConstraintsImpl;
import org.snmp4j.agent.mo.snmp.smi.ValueConstraintValidator;
import org.snmp4j.agent.mo.snmp.tc.TextualConvention;
import org.snmp4j.smi.AssignableFromLong;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

public class SparseTableColumn implements TextualConvention<Variable> {

	/**
	 * 
	 */
	private final Snmp4jDemoMib snmp4jDemoMib;

	public SparseTableColumn(Snmp4jDemoMib snmp4jDemoMib) {
		this.snmp4jDemoMib = snmp4jDemoMib;
	}

	public String getModuleName() {
		return Snmp4jDemoMib.TC_MODULE_SNMP4J_DEMO_MIB;
	}

	public String getName() {
		return Snmp4jDemoMib.TC_SPARSETABLECOLUMN;
	}

	public Variable createInitialValue() {
		Variable v = new OctetString();
		if (v instanceof AssignableFromLong) {
			((AssignableFromLong) v).setValue(0L);
		}
		// further modify value to comply with TC constraints here:
		// --AgentGen BEGIN=SparseTableColumn::createInitialValue
		// --AgentGen END
		return v;
	}

	public MOScalar<Variable> createScalar(OID oid, MOAccess access, Variable value) {
		MOScalar<Variable> scalar = this.snmp4jDemoMib.moFactory.createScalar(oid, access, value);
		ConstraintsImpl vc = new ConstraintsImpl();
		vc.add(new Constraint(0L, 10L));
		scalar.addMOValueValidationListener(new ValueConstraintValidator(vc));
		// --AgentGen BEGIN=SparseTableColumn::createScalar
		// --AgentGen END
		return scalar;
	}

	public MOColumn<Variable> createColumn(int columnID, int syntax, MOAccess access, Variable defaultValue,
			boolean mutableInService) {
		MOColumn<Variable> col = this.snmp4jDemoMib.moFactory.createColumn(columnID, syntax, access, defaultValue, mutableInService);
		if (col instanceof MOMutableColumn) {
			MOMutableColumn<Variable> mcol = (MOMutableColumn<Variable>) col;
			ConstraintsImpl vc = new ConstraintsImpl();
			vc.add(new Constraint(0L, 10L));
			mcol.addMOValueValidationListener(new ValueConstraintValidator(vc));
		}
		// --AgentGen BEGIN=SparseTableColumn::createColumn
		// --AgentGen END
		return col;
	}
}