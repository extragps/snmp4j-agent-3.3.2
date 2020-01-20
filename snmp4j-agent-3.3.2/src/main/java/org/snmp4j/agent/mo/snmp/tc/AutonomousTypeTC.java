/*_############################################################################
  _## 
  _##  SNMP4J-Agent 3 - AutonomousTypeTC.java  
  _## 
  _##  Copyright (C) 2005-2018  Frank Fock (SNMP4J.org)
  _##  
  _##  Licensed under the Apache License, Version 2.0 (the "License");
  _##  you may not use this file except in compliance with the License.
  _##  You may obtain a copy of the License at
  _##  
  _##      http://www.apache.org/licenses/LICENSE-2.0
  _##  
  _##  Unless required by applicable law or agreed to in writing, software
  _##  distributed under the License is distributed on an "AS IS" BASIS,
  _##  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  _##  See the License for the specific language governing permissions and
  _##  limitations under the License.
  _##  
  _##########################################################################*/

package org.snmp4j.agent.mo.snmp.tc;

import org.snmp4j.agent.*;
import org.snmp4j.agent.mo.*;
import org.snmp4j.smi.*;
import org.snmp4j.agent.mo.snmp.SNMPv2TC;
import org.snmp4j.agent.mo.snmp.AutonomousType;

public class AutonomousTypeTC implements TextualConvention<OID> {

  public AutonomousTypeTC() {
  }

  public MOColumn<OID> createColumn(int columnID, int syntax, MOAccess access,
                               OID defaultValue, boolean mutableInService) {
    return new AutonomousType(columnID, access,
                              defaultValue, mutableInService);
  }

  public MOScalar<OID> createScalar(OID oid, MOAccess access, OID value) {
    return new MOScalar<OID>(oid, access,
                        (value == null) ? createInitialValue() : value);
  }

  public String getModuleName() {
    return SNMPv2TC.MODULE_NAME;
  }

  public String getName() {
    return SNMPv2TC.AUTONOMOUSTYPE;
  }

  public OID createInitialValue() {
    return new OID();
  }
}
