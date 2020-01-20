/*_############################################################################
  _## 
  _##  SNMP4J-Agent 3 - StorageType.java  
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


package org.snmp4j.agent.mo.snmp;

import org.snmp4j.agent.io.ImportMode;
import org.snmp4j.agent.mo.MOMutableColumn;
import org.snmp4j.agent.MOAccess;
import org.snmp4j.agent.mo.MOMutableTableRow;
import org.snmp4j.agent.mo.RowModificationControlColumn;
import org.snmp4j.agent.request.SubRequest;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.SMIConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.agent.mo.MOTableRow;

public class StorageType extends MOMutableColumn<Integer32> implements RowModificationControlColumn {

    public static final int other = 1;
    public static final int volatile_ = 2;
    public static final int nonVolatile = 3;
    public static final int permanent = 4;
    public static final int readOnly = 5;

    /**
     * The {@code StorageTypeEnum} as a enumerated representastion of the StorageType SMI values.
     *
     * @version 3.0
     * @since 3.0
     */
    public enum StorageTypeEnum {
        other,
        volatile_,
        nonVolatile,
        permanent,
        readOnly;

        /**
         * Creates a StorageTypeEnum from its SMI value integer representation.
         *
         * @param storageTypeValue
         *         a storage type value from 1 to 5.
         *
         * @return the enum representation or {@code null} if the {@code storageTypeValue} integer does not represent a
         * valid storage type.
         */
        public static StorageTypeEnum fromValue(int storageTypeValue) {
            switch (storageTypeValue) {
                case StorageType.other:
                    return StorageTypeEnum.other;
                case StorageType.volatile_:
                    return StorageTypeEnum.volatile_;
                case StorageType.nonVolatile:
                    return StorageTypeEnum.nonVolatile;
                case StorageType.permanent:
                    return StorageTypeEnum.permanent;
                case StorageType.readOnly:
                    return StorageTypeEnum.readOnly;
            }
            return null;
        }

        /**
         * Gets the SMI representation (integer value) of this storage type.
         *
         * @return {@code StorageTypeEnum.ordinal() + 1}
         */
        public int getValue() {
            return ordinal() + 1;
        }
    }

    ;


    public StorageType(int columnID, MOAccess access,
                       Integer32 defaultValue, boolean mutableInService) {
        super(columnID, SMIConstants.SYNTAX_INTEGER,
                access, defaultValue, mutableInService);
    }

    public StorageType(int columnID, MOAccess access,
                       Integer32 defaultValue) {
        super(columnID, SMIConstants.SYNTAX_INTEGER,
                access, defaultValue);
    }


    public synchronized int validate(Integer32 newValue, Integer32 oldValue) {
        int v = (newValue.getValue());
        if ((v < 1) || (v > 5)) {
            return SnmpConstants.SNMP_ERROR_WRONG_VALUE;
        }
        if (oldValue != null) {
            int ov = (oldValue.getValue());
            if ((ov < 4) && (v >= 4)) {
                return SnmpConstants.SNMP_ERROR_WRONG_VALUE;
            }
            if (ov >= 4) {
                return SnmpConstants.SNMP_ERROR_WRONG_VALUE;
            }
        }
        return super.validate(newValue, oldValue);
    }

    /**
     * Prepares a row for changes described by the supplied change set. If the modification cannot be successfully
     * prepared, the error status of the supplied <code>subRequest</code> should be set to the appropriate error status
     * value.
     * <p>
     * This method is called only once per modified row.
     *
     * @param subRequest
     *         the sub-request that triggered the row change and that can be used to deny the commit phase by setting
     *         its error status.
     * @param currentRow
     *         the current row (yet unmodified).
     * @param changeSet
     *         a MOTableRow instance that represents the state of the row if all changes have been applied
     *         successfully.
     */
    @Override
    public void prepareRow(SubRequest<?> subRequest, MOMutableTableRow currentRow, MOTableRow changeSet) {
        Integer32 currentValue = (Integer32) currentRow.getValue(getTable().getColumnIndex(getColumnID()));
        switch (currentValue.getValue()) {
            case permanent: {
                break;
            }
            case readOnly: {
                subRequest.setErrorStatus(SnmpConstants.SNMP_ERROR_NOT_WRITEABLE);
            }
        }
    }


    /**
     * Checks if the row is volatile (i.e. must not be stored in stable storage) or not. Note: In SNMP4J-Agent before
     * 3.0, this method returned also {@code true} for rows with storage type {@link #readOnly} which did not follow the
     * SMI definition of {@link StorageType}. See also {@link ImportMode} for details about restore data from stable
     * storage.
     *
     * @param row
     *         a row of the table where this column is part of.
     * @param column
     *         the column index of this column in {@code row}.
     *
     * @return {@code true} if the storage type of this row is {@link #other} or {@link #volatile_}.
     */
    public boolean isVolatile(MOTableRow row, int column) {
        Integer32 value = (Integer32) row.getValue(column);
        if (value != null) {
            int storageType = value.getValue();
            switch (storageType) {
                case other:
                case volatile_: {
                    return true;
                }
                default:
                    return false;
            }
        }
        return false;
    }

}
