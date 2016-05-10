package studentcapture.datalayer.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by c13gan on 2016-04-26.
 */
public class Participant {

    // This template should be used to send queries to the database
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final String addParticipantStatement = "INSERT INTO Participant VALUES (?,?,?)";

    /**
     * Add a new participant to a course by connecting the tables "User" and "Course" in the database.
     *
     * @param userID        unique identifier for a person
     * @param courseId      unique identifier, registration code
     * @param function      student, teacher, ....
     * @return              true if insertion worked, else false
     */
    public boolean addParticipant(String userID, String courseId, String function) {
        boolean result;
        int userId = Integer.parseInt(userID);

        try {
            int rowsAffected = jdbcTemplate.update(addParticipantStatement,
                    userId, courseId, function);
            result = rowsAffected == 1;
        } catch (IncorrectResultSizeDataAccessException e){
            result = false;
        } catch (DataAccessException e1){
            result = false;
        }

        return result;
    }


    /**
     * Get the role for a participant in a selected course
     *
     * @param userID        unique identifier for a person
     * @param courseId      unique identifier, registration code
     * @return              role of a person
     */
    public Optional<String> getFunctionForParticipant(String userID, String courseId) {
    	String result = null;
        int userId = Integer.parseInt(userID);

        String getFunctionForParticipantStatement =
                "SELECT Function FROM Participant WHERE (UserId=? AND CourseId=?)";

        try {
    		result = jdbcTemplate.queryForObject(
    				getFunctionForParticipantStatement, new Object[]
    						{userId, courseId},
                    String.class);
    	} catch (IncorrectResultSizeDataAccessException e){
            //TODO
        } catch (DataAccessException e1){
        	//TODO
        }

        return Optional.of(result);
    }


    /**
     * Returns a list of all participants of a course including their function
     *
     * @param courseId      unique identifier, registration code
     * @return              List of tuples: CAS_ID - function
     */
    public Optional<List<ParticipantWrapper>> getAllParticipantsFromCourse(String courseId){
    	List<ParticipantWrapper> participants = new ArrayList<>();

        String getAllParticipantFromCourseStatement =
                "SELECT * FROM Participant WHERE (CourseId=?)";

		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(
					getAllParticipantFromCourseStatement, courseId);
			for (Map<String, Object> row : rows) {
				ParticipantWrapper participant = new ParticipantWrapper();
				participant.userId = (int) row.get("UserId");
				participant.courseId = (String) row.get("CourseId");
				participant.function = (String) row.get("Function");
				participants.add(participant);
			}

		} catch (IncorrectResultSizeDataAccessException e){
			//TODO
		    return Optional.empty();
		} catch (DataAccessException e1){
			//TODO
			return Optional.empty();
		}

        return Optional.of(participants);
    }

    /**
     * Returns a list of all courses a person is registered for, including their function
     *
     * @param userID        unique identifier for a person
     * @return              List of tuples: CourseID - function
     */
    public Optional<List<ParticipantWrapper>> getAllCoursesForParticipant(String userID) {
    	List<ParticipantWrapper> participants = new ArrayList<>();
    	int userId = Integer.parseInt(userID);

        String getAllCoursesForParticipantStatement =
                "SELECT CourseId,Function FROM Participant WHERE (UserId=?)";

    	try {
	    	List<Map<String, Object>> rows = jdbcTemplate.queryForList(
	    			getAllCoursesForParticipantStatement, userId);
	    	for (Map<String, Object> row : rows) {
	    		ParticipantWrapper participant = new ParticipantWrapper();
	    		participant.courseId = (String) row.get("CourseId");
	    		participant.function = (String) row.get("Function");
	    		participants.add(participant);
	    	}

	    } catch (IncorrectResultSizeDataAccessException e){
			//TODO
		    return Optional.empty();
		} catch (DataAccessException e1){
			//TODO
			return Optional.empty();
		}

        return Optional.of(participants);
    }


    /**
     * Removes the connection between a person and a course from the database
     *
     * @param userID        unique identifier for a person
     * @param courseId      unique identifier, registration code
     * @return              true if removal worked, else false
     */
    public boolean removeParticipant(String userID, String courseId){
    	boolean result;
    	int userId = Integer.parseInt(userID);

        String removeParticipantStatement =
                "DELETE FROM Participant WHERE (UserId=? AND CourseId=?)";

        try {
            int rowsAffected = jdbcTemplate.update(removeParticipantStatement,
                    userId, courseId);
            result = rowsAffected == 1;
        } catch (IncorrectResultSizeDataAccessException e){
            result = false;
        } catch (DataAccessException e1){
            result = false;
        }
        return result;
    }

    public class ParticipantWrapper {
    	public int userId;
    	public String courseId;
    	public String function;
    }
}
