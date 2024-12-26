package com.faculty.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.faculty.dto.CourseRequestDTO;
import com.faculty.dto.CourseResponseDTO;
import com.faculty.dto.ServiceResponse;
import com.faculty.service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyContoller {
	
	@Autowired
	private FacultyService facultyService;

	
	@PostMapping("/addNewCourse")
	public ServiceResponse<CourseResponseDTO> addNewCourse(@RequestBody CourseRequestDTO courseRequestDTO){
		return facultyService.addNewCourseToDashBoard(courseRequestDTO);
	}
	
	@GetMapping("/allcourses")
	public ServiceResponse<List<CourseResponseDTO>> addNewCourse(){
		return facultyService.fetchAllCourseToDashBoard();
	}
		
	@GetMapping("/Course/{courseId}")
	public ServiceResponse<CourseResponseDTO> getCourseinCr(@PathVariable Integer courseId){
		return facultyService.fetchCourseById(courseId);
	}
	
	@GetMapping("/Course")//http://localhost:8088/faculty/Course?courseId=2 using request param
	public ServiceResponse<CourseResponseDTO> getCourseinCrRequest(@RequestParam Integer courseId){
		return facultyService.fetchCourseTodRequestParam(courseId);
	}
	
	@PutMapping("/updateCourse/{courseId}")
	public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable Integer courseId,@RequestBody CourseRequestDTO courseRequestDTO){
		facultyService.updateCourse(courseId, courseRequestDTO);
		return facultyService.fetchCourseById(courseId);
	}
	
	@DeleteMapping("/delete/{courseId}")
	public String deleteCouse(@PathVariable int courseId) {
		facultyService.deleteCourseFromDashBoard(courseId);
		return "Course Deleted with id: "+courseId;
	}
}
