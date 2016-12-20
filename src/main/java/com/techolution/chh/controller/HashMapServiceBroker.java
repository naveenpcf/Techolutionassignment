package com.techolution.chh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techolution.chh.service.HashMapService;


/**
 * 
 * 
 * @author naveenkumar300@gmail.com
 *
 */
@RestController
public class HashMapServiceBroker {


	 @Autowired
	    HashMapService hashMapservice;


	    @RequestMapping(value = "/HashMapService/{instance_Id}/{key}", method = RequestMethod.PUT)
	    public ResponseEntity<String> put(@PathVariable("instance_Id") String instanceId,
	                                      @PathVariable("key") String key,
	                                      @RequestBody String value) {
	    	hashMapservice.put(instanceId, key, value);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }

	    @RequestMapping(value = "/HashMapService/{instance_Id}/{key}", method = RequestMethod.GET)
	    public ResponseEntity<Object> get(@PathVariable("instanceId") String instanceId,
	                                      @PathVariable("key") String key) {
	        Object objVal = hashMapservice.get(instanceId, key);
	        if (objVal != null) {
	            return new ResponseEntity<>(objVal, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @RequestMapping(value = "/HaaSh/{instanceId}/{key}", method = RequestMethod.DELETE)
	    public ResponseEntity<String> delete(@PathVariable("instanceId") String instanceId,
	                                         @PathVariable("key") String key) {
	        Object objVal = hashMapservice.get(instanceId, key);
	        if (objVal != null) {
	        	hashMapservice.delete(instanceId, key);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
}
