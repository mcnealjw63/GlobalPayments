package com.global.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.api.Device;
import com.global.service.DeviceService;
/*
 * Note.  In Junit5 some things have changed that I have not figured out yet. 
 * Still used to using junit4.  Created this project using the lates springboot 
 * Version
 */
@AutoConfigureMockMvc
@ContextConfiguration(classes = {DeviceController.class, DeviceService.class,GlobalErrorAdvise.class})
@WebMvcTest
public class DeviceControllerTests {
	@MockBean
	private DeviceService deviceService;
	@Autowired
	private MockMvc mvc;
	@Test
	public void testCreatDeviceHappyPath() throws Exception{
		String request = asJsonString(new Device("12-1222", "lastName4", null));
		 mvc.perform( MockMvcRequestBuilders
				      .post("/device/v1/device")
				      .content(asJsonString(new Device("12-1222", "lastName4", null)))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isCreated());
				     
		
	}
	@Test
	public void testCreatDeviceinvalidSerialNumber() throws Exception {
				mvc.perform( MockMvcRequestBuilders
					      .post("/device/v1/device")
					      .content(asJsonString(new Device("121222", "lastName4", null)))
					      .contentType(MediaType.APPLICATION_JSON)
					      .accept(MediaType.APPLICATION_JSON))
					      .andExpect(status().isBadRequest())
					      .andExpect(MockMvcResultMatchers.jsonPath("$.resourceKey").value("serial.number.invalid"));
	}
	@Test
	public void testCreatDeviceinvalidMachineCode() throws Exception{
			mvc.perform( MockMvcRequestBuilders
				      .post("/device/v1/device")
				      .content(asJsonString(new Device("121222", null, null)))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isBadRequest())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.resourceKey").value("machine.code.invalid"));
		
	}
	@Test
	public void testupdateDeviceHappyPath() throws Exception{
		String request = asJsonString(new Device("12-1222", "lastName4", null));
		 mvc.perform( MockMvcRequestBuilders
				      .post("/device/v1/device")
				      .content(asJsonString(new Device("12-1222", "lastName4", null)))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isOk());
				     
		
	}
	@Test
	public void testUpdateDeviceinvalidSerialNumber() throws Exception {
				mvc.perform( MockMvcRequestBuilders
					      .put("/device/v1/device")
					      .content(asJsonString(new Device("121222", "lastName4", null)))
					      .contentType(MediaType.APPLICATION_JSON)
					      .accept(MediaType.APPLICATION_JSON))
					      .andExpect(status().isBadRequest())
					      .andExpect(MockMvcResultMatchers.jsonPath("$.resourceKey").value("serial.number.invalid"));
	}
	@Test
	public void testUpdateDeviceinvalidMachineCode() throws Exception{
			mvc.perform( MockMvcRequestBuilders
				      .put("/device/v1/device")
				      .content(asJsonString(new Device("121222", null, null)))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isBadRequest())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.resourceKey").value("machine.code.invalid"));
		
	}
	@Test
	public void testDeviceSerailSearchHappyPath() throws Exception{
			mvc.perform( MockMvcRequestBuilders
				      .get("/device/v1/device?serialnumber=12-1222")
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isOk());
	}
	@Test
	public void testDeviceSerailinvalidSearch() throws Exception{
			mvc.perform( MockMvcRequestBuilders
				      .get("/device/v1/device?serialnumber=12-1222")
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isBadRequest())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.resourceKey").value("machine.code.not.found"));
	}
	@Test
	public void testDeviceMachinecodeSearchHappyPath() throws Exception{
			mvc.perform( MockMvcRequestBuilders
				      .get("/device/v1/device?deviceCode=8972934")
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isOk());
	}
	@Test
	public void testDeviceMachinecodeinvalidSearch() throws Exception{
			mvc.perform( MockMvcRequestBuilders
				      .get("/device/v1/device?deviceCode=8972934")
				      .accept(MediaType.APPLICATION_JSON))
					  .andExpect(status().isBadRequest())
					  .andExpect(MockMvcResultMatchers.jsonPath("$.resourceKey").value("serial.number.not.found"));
	}
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
