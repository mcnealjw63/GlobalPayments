package com.global.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.global.api.Device;
import com.global.exception.DataValidationException;

@SpringBootTest
public class DeviceServiceTest {
	@Autowired
	private DeviceService deviceService;

	/*
	 * They have changed several things between junit4 and junit5
	 * Having to quess some things here
	 */

	@Test
	public void createDeviceHappyPath() throws DataValidationException {
		Device sourceDevice = new Device("12-1222", "lastName4", "Bubba");
		Device target = deviceService.addDevice(sourceDevice);
		assertEquals(sourceDevice.getSerialNumber(), target.getSerialNumber());
		assertEquals(sourceDevice.getMachineCode(), target.getMachineCode());
		assertEquals(sourceDevice.getDeviceName(), target.getDeviceName());
	}

	@Test
	public void updateDeviceHappyPath() throws DataValidationException {
		Device sourceDevice = new Device("12-1222", "lastName4", "Bubba");
		Device target = deviceService.updateDevice(sourceDevice);
		assertEquals(sourceDevice.getSerialNumber(), target.getSerialNumber());
		assertEquals(sourceDevice.getMachineCode(), target.getMachineCode());
		assertEquals(sourceDevice.getDeviceName(), target.getDeviceName());
	}

	@Test
	public void createDeviceInvalidSerialNumber() {
		Device sourceDevice = new Device("121222", "lastName4", "Bubba");
		String exceptionCode = null;
		try {
			deviceService.addDevice(sourceDevice);
		}
		catch (DataValidationException e) {
			exceptionCode = e.getErrorResponse().getResourceKey();
		}
		assertEquals(exceptionCode, "serial.number.invalid");

	}

	@Test
	public void updateDeviceInvalidSerialNumber() {
		Device sourceDevice = new Device("121222", "lastName4", "Bubba");
		String exceptionCode = null;
		try {
			deviceService.updateDevice(sourceDevice);
		}
		catch (DataValidationException e) {
			exceptionCode = e.getErrorResponse().getResourceKey();
		}
		assertEquals(exceptionCode, "serial.number.invalid");

	}

	@Test
	public void createDeviceInvalidDeviceCode() {
		Device sourceDevice = new Device("121222", null, "Bubba");
		String exceptionCode = null;
		try {
			deviceService.addDevice(sourceDevice);
		}
		catch (DataValidationException e) {
			exceptionCode = e.getErrorResponse().getResourceKey();
		}
		assertEquals(exceptionCode, "machine.code.invalid");

	}

	@Test
	public void updateDeviceInvalidDeviceCode() {
		Device sourceDevice = new Device("121222", null, "Bubba");
		String exceptionCode = null;
		try {
			deviceService.updateDevice(sourceDevice);
		}
		catch (DataValidationException e) {
			exceptionCode = e.getErrorResponse().getResourceKey();
		}
		assertEquals(exceptionCode, "machine.code.invalid");

	}
}
