package com.global.controller;

import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.global.api.Device;
import com.global.service.DeviceService;


@RestController
@RequestMapping(value = "/device/v1")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;
	
	@PostMapping(path = "/device", produces = {"application/json"}, consumes = {"application/json"})
	public ResponseEntity<Device> createDevice(@RequestBody Device device)
			throws Exception {
		return new ResponseEntity<>(deviceService.addDevice(device), HttpStatus.CREATED);
	}
	@PutMapping(path = "/device", produces = {"application/json"}, consumes = {"application/json"})
	public ResponseEntity<Device> updateDevice(@RequestBody Device device)
			throws Exception {
		return new ResponseEntity<>(deviceService.updateDevice(device), HttpStatus.OK);
	}
	@GetMapping(path = "/device", produces = {"application/json"})
	public ResponseEntity<Device> getDevice(@QueryParam("serialNumber") String serialNumber, @QueryParam("deviceCode")  String machineCode) throws Exception{
		return new ResponseEntity<>(deviceService.findDevice(serialNumber,machineCode), HttpStatus.OK);
	}
}
