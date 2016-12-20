package com.techolution.chh.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * This below hashmap methods methods are used to get service instance id ,key and value  based on the input 
 * service stores the values and retrieved as  response
 * 
 * @author naveenkumar300@gmail.com
 *
 */
@Service
public class HashMapService  {

	 private Map<String, Map<Object, Object>> hashMap = new HashMap<>();

	    public void create(String id) {
	        hashMap.put(id, new HashMap<Object, Object>());
	    }

	    public void delete(String id) {
	        hashMap.remove(id);
	    }

	    public void put(String id, Object key, Object value) {
	        Map<Object, Object> mapInstance = hashMap.get(id);
	        mapInstance.put(key, value);
	    }

	    public Object get(String id, Object key) {
	        Map<Object, Object> mapInstance = hashMap.get(id);
	        return mapInstance.get(key);
	    }

	    public void delete(String id, Object key) {
	        Map<Object, Object> mapInstance = hashMap.get(id);
	        mapInstance.remove(key);
	    }

}