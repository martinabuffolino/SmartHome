package com.gruppo1.smarthome.controller;

import com.gruppo1.smarthome.model.Device;
import com.gruppo1.smarthome.service.DeviceService;
import com.sun.istack.NotNull;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//http://localhost:8080/smarthome/device

@RestController
@Api(value = "Device", description = "Rest API for Device", tags = {"Device"})
@RequestMapping("/device")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "List all devices installed", tags = {"Device"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "return devices"),
        @ApiResponse(code = 404, message = "Not Found - returned on resource not found")})
    public ResponseEntity<List<Device>> getAllDevices(){
        List<Device> devices = deviceService.findAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add new device", tags = {"Device"})
    @ApiResponse(code = 201, message = "deviced added")
    public ResponseEntity<Device> addDevice(@ApiParam(value = "Device to add", required = true) @NotNull @RequestBody Device device){
        Device newDevice = deviceService.addDevice(device);
        return new ResponseEntity<>(newDevice, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Device>> getDeviceById(@PathVariable("id") String id){
        Optional<Device> device = deviceService.findDeviceByID(id);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable("id") String id){
        deviceService.deleteDevice(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<List<Device>> updateDevice(@RequestBody Device device){
        Device updatedDevice = deviceService.updateDevice(device);
        return new ResponseEntity(updatedDevice, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<List<Device>> countDevices(){
        long devices = deviceService.countDevices();
        return new ResponseEntity(devices, HttpStatus.OK);
    }
}
