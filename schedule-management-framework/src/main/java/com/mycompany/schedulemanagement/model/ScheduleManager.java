package com.mycompany.schedulemanagement.model;

import java.util.List;

import com.mycompany.schedulemanagement.exception.InvalidTaskException;
import com.mycompany.schedulemanagement.model.dto.Schedule;

/**
 * ScheduleManager interface provide the method to schedule tasks. Any class can
 * implement this interface to facilitate schedules.
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 * @since 2014-12-19
 */
public interface ScheduleManager {

	/**
	 * Method to create the list of schedule objects based on tasks available in
	 * given file.
	 * 
	 * @param fileName
	 *            Input file name which contains the task details.
	 * @return List of schedule objects.
	 * @throws InvalidTaskException
	 *             when there is an error occurred during process.
	 */
	List<Schedule> schedule(String fileName) throws InvalidTaskException;

	/**
	 * Method to create the list of schedule objects based on list of tasks.
	 * 
	 * @param taskList
	 *            List of tasks in string format.
	 * @return List of schedule objects.
	 * @throws InvalidTaskException
	 *             when there is an error occurred during process.
	 */
	List<Schedule> schedule(List<String> taskList)
			throws InvalidTaskException;
}
