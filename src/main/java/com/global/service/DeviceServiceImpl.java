package com.global.service;

import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.global.api.Device;
import com.global.api.ErrorResponse;
import com.global.exception.DataValidationException;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	MessageSource messageSource;

	Pattern serialNoPattern = Pattern.compile("^([a-zA-Z0-9]*-[a-zA-Z0-9]*)+$");

	@Override
	public Device addDevice(Device device) throws DataValidationException {
		validateSerialNumber(device.getSerialNumber());
		validateMachinecode(device.getMachineCode());
		return new Device(device.getSerialNumber(), device.getMachineCode(), device.getDeviceName());
	}

	@Override
	public Device updateDevice(Device device) throws DataValidationException {
		validateSerialNumber(device.getSerialNumber());
		validateMachinecode(device.getMachineCode());
		return new Device(device.getSerialNumber(), device.getMachineCode(), device.getDeviceName());

	}

	@Override
	public Device findDevice(String serialNumber, String deviceCode) throws DataValidationException {
		Optional<Device> device = null;
		if (!StringUtils.hasLength(serialNumber) && !StringUtils.hasLength(deviceCode)) {
			throw new DataValidationException(
					new ErrorResponse("search.parameter.missing", "ER005", messageSource.getMessage("search.parameter.missing", null, Locale.US)));
		}
		if (serialNumber != null) {
			device = findDeviceBySerialNumber(serialNumber);
			if (!device.isPresent()) {
				throw new DataValidationException(
						new ErrorResponse("serial.number.not.found", "ER004", messageSource.getMessage("serial.number.not.found", null, Locale.US)));
			}
		}
		if (deviceCode != null) {
			device = findDeviceByMachineCode(deviceCode);
			if (!device.isPresent()) {
				throw new DataValidationException(
						new ErrorResponse("machine.code.not.found", "ER002", messageSource.getMessage("machine.code.not.found", null, Locale.US)));
			}
		}
		return device.get();
	}

	private Optional<Device> findDeviceBySerialNumber(String serialNumber) {
		return Optional.empty();

	}

	private Optional<Device> findDeviceByMachineCode(String machineCode) {
		return Optional.empty();

	}

	private void validateSerialNumber(String serialNumber) throws DataValidationException {
		Matcher m = serialNoPattern.matcher(serialNumber);
		if (!m.matches()) {
			throw new DataValidationException(
					new ErrorResponse("serial.number.invalid", "ER003", messageSource.getMessage("serial.number.invalid", null, Locale.US)));
		}
	}

	private void validateMachinecode(String machineCode) throws DataValidationException {
		if (!StringUtils.hasLength(machineCode)) {
			throw new DataValidationException(
					new ErrorResponse("machine.code.invalid", "ER001", messageSource.getMessage("machine.code.invalid", null, Locale.US)));
		}
	}
}
