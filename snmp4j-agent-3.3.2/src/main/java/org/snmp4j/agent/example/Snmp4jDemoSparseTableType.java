package org.snmp4j.agent.example;

import java.util.Random;

import org.snmp4j.agent.MOAccess;
import org.snmp4j.agent.example.Snmp4jDemoMib.Snmp4jDemoSparseEntryRow;
import org.snmp4j.agent.example.Snmp4jDemoMib.Snmp4jDemoSparseTableTypeEnum;
import org.snmp4j.agent.mo.snmp.EnumeratedScalar;
import org.snmp4j.agent.request.SubRequest;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

public class Snmp4jDemoSparseTableType extends EnumeratedScalar<Integer32> {
		/**
		 * 
		 */
		private final Snmp4jDemoMib snmp4jDemoMib;

		Snmp4jDemoSparseTableType(Snmp4jDemoMib snmp4jDemoMib, OID oid, MOAccess access) {
			super(oid, access, new Integer32(),
					new int[] { Snmp4jDemoSparseTableTypeEnum.cross, Snmp4jDemoSparseTableTypeEnum.square,
							Snmp4jDemoSparseTableTypeEnum.stairsDown, Snmp4jDemoSparseTableTypeEnum.stairsUp,
							Snmp4jDemoSparseTableTypeEnum.rain, Snmp4jDemoSparseTableTypeEnum.random });
//--AgentGen BEGIN=snmp4jDemoSparseTableType
//--AgentGen END
			this.snmp4jDemoMib = snmp4jDemoMib;
		}

		public int isValueOK(SubRequest<?> request) {
			Variable newValue = request.getVariableBinding().getVariable();
			int valueOK = super.isValueOK(request);
			if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
				return valueOK;
			}
			// --AgentGen BEGIN=snmp4jDemoSparseTableType::isValueOK
			// --AgentGen END
			return valueOK;
		}

		public Integer32 getValue() {
			// --AgentGen BEGIN=snmp4jDemoSparseTableType::getValue
			// --AgentGen END
			return super.getValue();
		}

		public int setValue(Integer32 newValue) {
			// --AgentGen BEGIN=snmp4jDemoSparseTableType::setValue
			char[][] pattern = Snmp4jDemoMib.SPARSE_PATTERNS[newValue.toInt() - 1];
			this.snmp4jDemoMib.snmp4jDemoSparseEntryModel.clear();
			Random random = new Random();
			for (int r = 1; r < 101; r++) {
				Variable[] values = new Variable[this.snmp4jDemoMib.snmp4jDemoSparseEntry.getColumnCount()];
				values[0] = new Integer32(1);
				int colCount = this.snmp4jDemoMib.snmp4jDemoSparseEntry.getColumnCount();
				for (int i = 1; i < colCount; i++) {
					char c = pattern[(r - 1) % pattern.length][(i - 1) % pattern[0].length];
					switch (c) {
					case 'x':
						values[i] = new OctetString("" + r + ":" + i);
						break;
					case 'r':
						if (random.nextBoolean()) {
							values[i] = new OctetString("" + r + ":" + i);
						}
						break;
					}
				}
				Snmp4jDemoSparseEntryRow sparseEntryRow = this.snmp4jDemoMib.new Snmp4jDemoSparseEntryRow(new OID(new int[] { r }),
						values);
				this.snmp4jDemoMib.snmp4jDemoSparseEntryModel.addRow(sparseEntryRow);
			}
			// --AgentGen END
			return super.setValue(newValue);
		}

		// --AgentGen BEGIN=snmp4jDemoSparseTableType::_METHODS
		// --AgentGen END

	}