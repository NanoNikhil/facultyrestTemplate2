package com.faculty.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.faculty.dto.CourseRequestDTO;
import com.faculty.dto.CourseResponseDTO;
import com.faculty.dto.ServiceResponse;

@Service
public class FacultyService {
	
	private final static String BASE_URL="http://localhost:8087/course/";
	
	@Autowired
	private RestTemplate restTemplate;
	

	public ServiceResponse<CourseResponseDTO> addNewCourseToDashBoard(CourseRequestDTO courseRequestDTO){
		return restTemplate.postForObject(BASE_URL+"create",courseRequestDTO , ServiceResponse.class);//postForObject-->for Object//postForEntity-->EntityObject
	}
	
	public ServiceResponse<List<CourseResponseDTO>> fetchAllCourseToDashBoard(){
		return restTemplate.getForObject(BASE_URL+"all", ServiceResponse.class);
	}
	
	
	public ServiceResponse<CourseResponseDTO> fetchCourseById(Integer courseId){
		/*
		 * Map<String,Integer> requestMap=new HashMap<>();// use this in when RequestParam
		 * requestMap.put("courseId",courseId);
		 */
		return restTemplate.getForObject(BASE_URL+"{courseId}", ServiceResponse.class,courseId );//postForObject-->for Object//postForEntity-->EntityObject
	}
	
	
	public ServiceResponse<CourseResponseDTO> fetchCourseTodRequestParam(Integer courseId){
		  Map<String,Integer> requestMap=new HashMap<>();// use this in when RequestParam
		  requestMap.put("courseId",courseId); 
		return restTemplate.getForObject(BASE_URL+"{courseId}", ServiceResponse.class,requestMap );//postForObject-->for Object//postForEntity-->EntityObject
	}
	
	
	public void updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {
			restTemplate.put(BASE_URL +courseId, courseRequestDTO);
	}
	
	
	public void deleteCourseFromDashBoard(int courseId) {
		restTemplate.delete(BASE_URL+courseId);
	}
}
