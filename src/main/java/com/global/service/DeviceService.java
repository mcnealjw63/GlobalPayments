package com.global.service;

import com.global.api.Device;
import com.global.exception.DataValidationException;

public interface DeviceService {
public Device addDevice(Device device) throws DataValidationException;
public Device updateDevice(Device device) throws DataValidationException;
public Device findDevice(String serialNumber,String deviceCode) throws DataValidationException;
}
