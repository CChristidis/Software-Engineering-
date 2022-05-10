package myy803.courses_mgt_app_skeleton.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.courses_mgt_app_skeleton.dao.CourseDAO;
import myy803.courses_mgt_app_skeleton.entity.Course;
import myy803.courses_mgt_app_skeleton.entity.StudentRegistration;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDAO courseRepository;
	
	public CourseServiceImpl() {
		super();
	}

	@Autowired
	public CourseServiceImpl(CourseDAO theCourseRepository) {
		courseRepository = theCourseRepository;
	}
	
	@Override
	@Transactional
	public List<Course> findCourseByInstructorLogin(String theLogin) throws RuntimeException {
		List<Course> result = courseRepository.findCourseByInstructorLogin(theLogin);
		
		if (result != null )
			return result;
		else
			throw new RuntimeException("Did not find course - " + theLogin);
	}
	
	@Override
	@Transactional
	public void save(Course theCourse) {
		courseRepository.save(theCourse);
	}
	

	@Override
	@Transactional
	public void delete(int theId) {
		courseRepository.deleteById(theId);
	}

	// US7
	public void addStudentRegistrationToCourseList(Course theCourse, StudentRegistration theStudentRegistration) {
		theCourse.getStudentsRegistrations().add(theStudentRegistration);
	}

	@Override
	public Course findById(int theId) {
		Course result = courseRepository.findById(theId);
		
		if (result != null )
			return result;
		else
			throw new RuntimeException("Did not find course with id - " + theId);
	}
	
}
