package com.tsmc.gateway.modbusRTU;

import java.util.Arrays;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import com.serotonin.modbus4j.serial.SerialPortWrapper;


public class CollectionMain {
    

    public static double readHoldingRegisters(ModbusMaster master, int slaveId, int start, int len) {
    	double res = -1;
        try {
            ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, start, len);
            ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse)master.send(request);
            if (response.isException()) {
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            } else {
            	res = (double)(response.getShortData()[0]);

            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
        System.out.println(res);
        return res;
    }
}
