package studentcapture.datalayer.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by c13gan on 2016-04-26.
 */
@Repository
public class Course {


    // This template should be used to send queries to the database
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    /**

     * Attempts to add a course to the database.

     *

     * @param courseCode	the courses 6 character identification (ex. 5DV151)

     * @param year		the year the course takes place

     * @param term		the term the course takes palce (ex. VT)

     * @return			true if successful, else false

     */
    private static final String addCourseStatement = "INSERT INTO "
    		+ "Course VALUES (?,?,?,?,?)";
    public boolean addCourse(String courseID, String courseCode, String year,
    		String term, String courseName) {
    	boolean result;
    	int years = Integer.parseInt(year);
        try {
            int rowsAffected = jdbcTemplate.update(addCourseStatement,
            		new Object[] {courseID, years, term, courseCode, courseName});
            if(rowsAffected == 1) {
            	result = true;
            } else {
            	result = false;
            }
        }catch (IncorrectResultSizeDataAccessException e){
            result = false;
        }catch (DataAccessException e1){
            result = false;
        }

        return result;
    }

    
    public String getCourseID(String year,String term,String courseCode){
    	String sql = "SELECT courseID from course WHERE CourseCode = ? AND Year = ? AND Term = ?";
    	return jdbcTemplate.queryForObject(sql, new Object[]{courseCode,year,term},String.class);
    }

    
    /**

     * Attempts to retrieve all data regarding a course from the database.

     *

     * @param courseID	target courses database identification

     * @return			list of course data in order:<ul>

     * 					<li>the courses database identification</li>

     * 					<li>the courses 6 character identification</li>

     * 					<li>the year the course takes place</li>

     * 					<li>the term the course takes place</li>

     * 					<li>the courses name</li></ul>

     * 					If unsuccessful, an empty list is returned.

     */

    public List<Object> getCourse(String courseID) {

        return null; //TODO

    }



    /**

     * Attempts to remove a course from the database.

     *

     * @param courseID	courses database identification

     * @return			true if successful, else false

     */

    private static final String removeCourseStatement = "DELETE FROM "
    		+ "Course WHERE CourseID=?";
    public boolean removeCourse(String courseID) {
    	boolean result;
        try {
            int rowsAffected = jdbcTemplate.update(removeCourseStatement,
            		new Object[] {courseID});
            if(rowsAffected == 1) {
            	result = true;
            } else {
            	result = false;
            }
        }catch (IncorrectResultSizeDataAccessException e){
            result = false;
        }catch (DataAccessException e1){
            result = false;
        }
        return result;
    }

    /**
     * Fetches the code for a course.
     * Useful when constructing a file path.
     * @param courseId Unique identifier for the course
     * @return course code
     */
    public String getCourseCodeFromId(String courseId){
        String query = "SELECT coursecode FROM Course WHERE courseid = '?';";
        String courseCode;

        try {
            courseCode = jdbcTemplate.queryForObject(query, new Object[]{courseId}, String.class);

            if (courseCode == null) {
                courseCode = "Missing value";
            } else {
                courseCode = courseCode.trim();
            }
        } catch (IncorrectResultSizeDataAccessException up) {
            throw up;
        } catch (DataAccessException down) {
            throw down;
        }

        return courseCode;
    }


}
