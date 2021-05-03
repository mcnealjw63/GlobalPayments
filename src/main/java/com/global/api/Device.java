package com.global.api;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device {
	public Device(String serialNumber,String machineCode,String deviceName) {
		this.serialNumber= serialNumber;
		this.machineCode = machineCode;
		this.deviceName = deviceName;
	}
	@JsonProperty("Serial number")
	private String serialNumber;
	@JsonProperty("Machine code")
	private String machineCode;
	@JsonProperty("Device name")
	private String deviceName;
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

}
