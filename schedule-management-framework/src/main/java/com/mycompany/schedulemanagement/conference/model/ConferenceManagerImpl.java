package com.mycompany.schedulemanagement.conference.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mycompany.schedulemanagement.exception.InvalidTaskException;
import com.mycompany.schedulemanagement.model.ScheduleConstant;
import com.mycompany.schedulemanagement.model.ScheduleManager;
import com.mycompany.schedulemanagement.model.dto.Schedule;
import com.mycompany.schedulemanagement.model.dto.Task;
import com.mycompany.schedulemanagement.model.util.ScheduleUtil;

/**
 * ConferenceManagerImpl is the implementation of ScheduleManager interface.
 * This class provides the functions to schedule conferences.
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 * @since 2014-12-19
 */
public class ConferenceManagerImpl implements ScheduleManager {

	/*
	 * @see
	 * com.mycompany.schedulemanagement.model.ScheduleManager#schedule(java.
	 * lang.String)
	 */
	public List<Schedule> schedule(String fileName) throws InvalidTaskException {
		// Create task list using file content
		List<Task> tasksList = validateAndCreateTasks(ScheduleUtil
				.getTaskListFromFile(fileName));
		// Return list of schedule using the task list.
		return getConferenceSchedule(tasksList);
	}

	/*
	 * @see
	 * com.mycompany.schedulemanagement.model.ScheduleManager#schedule(java.
	 * util.List)
	 */
	public List<Schedule> schedule(List<String> taskList)
			throws InvalidTaskException {
		// Create task list using string array
		List<Task> tasksList = validateAndCreateTasks(taskList);
		// Return list of schedule using the task list.
		return getConferenceSchedule(tasksList);
	}

	/**
	 * Check the validity of each task and create a list
	 * 
	 * @param taskList
	 *            task list as array of string.
	 * @throws InvalidTaskException
	 *             when ever exception occurred during the process.
	 * @return List<Task> list of task objects
	 */
	private List<Task> validateAndCreateTasks(List<String> taskList)
			throws InvalidTaskException {

		// If input list is null throw an exception
		if (taskList == null)
			throw new InvalidTaskException(
					ScheduleUtil
							.getProperty("exception.message.null.task.list"));

		String title;
		String duration;
		List<Task> validTasksList = new ArrayList<Task>();

		for (String task : taskList) {

			Matcher m = Pattern.compile("[^0-9]*([0-9]+).*").matcher(task);
			if (task.endsWith(ScheduleUtil.getProperty("time.min.suffix"))
					&& m.matches()) {
				duration = m.group(1);
				title = task.substring(0, task.indexOf(m.group(1)));
			} else if (task.endsWith(ScheduleUtil
					.getProperty("time.lighting.suffix")) && m.matches()) {
				duration = Integer.toString(Integer.parseInt(m.group(1)) 
						* Integer.parseInt((ScheduleUtil
										.getProperty("time.lighting.duration"))));
				title = task.substring(0, task.indexOf(m.group(1)));
			} else if (task.endsWith(ScheduleUtil
					.getProperty("time.lighting.suffix")) && !m.matches()) {
				duration = ScheduleUtil.getProperty("time.lighting.duration");
				title = task.substring(0, task.indexOf(ScheduleUtil
						.getProperty("time.lighting.suffix")));
			} else {
				throw new InvalidTaskException(
						ScheduleUtil
								.getProperty("exception.message.invalid.format")
								+ task);
			}

			if (ScheduleConstant.EMPTY_STRING.equals(title.trim())) {
				throw new InvalidTaskException(
						ScheduleUtil
								.getProperty("exception.message.invalid.name")
								+ task);
			} else if (Integer.parseInt(duration) > Integer.parseInt(
					ScheduleUtil
							.getProperty("conference.max.session.time.limit"))) {
				throw new InvalidTaskException(
						ScheduleUtil
								.getProperty("exception.message.invalid.time.duration")
								+ task);

			} else {
				// Add task to the valid task List.
				validTasksList.add(new Task(title, Integer.parseInt(duration)));
			}

		}

		return validTasksList;
	}

	/**
	 * Add tasks for morning session and evening session.
	 * 
	 * @param tasksList
	 *            List of valid tasks
	 * @throws InvalidTaskException
	 *             if exception occurred during the process
	 */
	private List<Schedule> getConferenceSchedule(List<Task> tasksList)
			throws InvalidTaskException {
		// Find the number of possible tracks.
		int numberOfPossibleTracks = ScheduleUtil
				.getNumberOfPossibleTracks(tasksList);

		// Morning session max and min session time limits are same as
		// conference minimum session time limit.
		List<List<Task>> possibleTasksForMornSessions = findPossibleSessions(
				tasksList,
				numberOfPossibleTracks,
				true,
				Integer.parseInt(ScheduleUtil
						.getProperty("conference.min.session.time.limit")),
				Integer.parseInt(ScheduleUtil
						.getProperty("conference.min.session.time.limit")));

		// Remove all the scheduled tasks for morning session, from the
		// taskList.
		for (List<Task> taskList : possibleTasksForMornSessions) {
			tasksList.removeAll(taskList);
		}

		// Evening session max and min session time limits are different.
		List<List<Task>> possibleTasksForEveSessions = findPossibleSessions(
				tasksList,
				numberOfPossibleTracks,
				false,
				Integer.parseInt(ScheduleUtil
						.getProperty("conference.min.session.time.limit")),
				Integer.parseInt(ScheduleUtil
						.getProperty("conference.max.session.time.limit")));

		// Remove all the scheduled tasks for evening session, from the
		// taskList.
		for (List<Task> taskList : possibleTasksForEveSessions) {
			tasksList.removeAll(taskList);
		}

		// check if the task list is not empty, then try to fill all the
		// remaining tasks in evening session.
		if (!tasksList.isEmpty()) {
			List<Task> scheduledTaskList = new ArrayList<Task>();
			for (List<Task> taskList : possibleTasksForEveSessions) {
				int totalTime = ScheduleUtil.getTotalTasksTime(taskList);

				for (Task task : tasksList) {
					int taskTime = task.getTimeDuration();

					if (taskTime + totalTime <= Integer.parseInt(
							ScheduleUtil
									.getProperty("conference.max.session.time.limit"))) {
						taskList.add(task);
						task.setScheduled(true);
						scheduledTaskList.add(task);
						totalTime = totalTime + taskTime;
					}
				}

				tasksList.removeAll(scheduledTaskList);
				if (tasksList.isEmpty())
					break;
			}
		}

		// If task list is still not empty, its mean the conference can not
		// be scheduled with the provided data.
		if (!tasksList.isEmpty()) {
			throw new InvalidTaskException(
					ScheduleUtil
							.getProperty("exception.message.scheduling.not.succesful"));
		}

		// Schedule the track event from morning session and evening session.
		return getScheduledTasksList(possibleTasksForMornSessions,
				possibleTasksForEveSessions, numberOfPossibleTracks);
	}

	/**
	 * Find possible combination for the session.
	 * 
	 * @param taskList
	 *            List of valid tasks
	 * @param numberOfPossibleTracks
	 *            number of tracks
	 * @param isMorning
	 *            true if morning session
	 * @param minSessionTimeLimit
	 *            minimum time duration allowed for the session
	 * @param maxSessionTimeLimit
	 *            maximum time duration allowed for the session
	 * @return list of tasks lists
	 */
	private List<List<Task>> findPossibleSessions(List<Task> taskList,
			int numberOfPossibleTracks, boolean isMorning,
			int minSessionTimeLimit, int maxSessionTimeLimit) {

		int taskListSize = taskList.size();
		List<List<Task>> possibleCombinationsOfTasks = new ArrayList<List<Task>>();
		int possibleCombinationCount = 0;

		// Loop to get combination for total possible tracks.
		// Check one by one from each task to get possible combination.
		for (int count = 0; count < taskListSize; count++) {
			int startPoint = count;
			int totalTime = 0;
			List<Task> possibleCombinationList = new ArrayList<Task>();

			// Loop to get possible combination.
			while (startPoint != taskListSize) {
				int currentCount = startPoint;
				startPoint++;
				Task currentTask = taskList.get(currentCount);
				if (currentTask.isScheduled())
					continue;
				int taskTime = currentTask.getTimeDuration();
				// Check whether task is fitting to the remaining time.
				if (taskTime > maxSessionTimeLimit
						|| taskTime + totalTime > maxSessionTimeLimit) {
					continue;
				}

				possibleCombinationList.add(currentTask);
				totalTime += taskTime;

				// If session time is filled then break
				if (isMorning) {
					if (totalTime == maxSessionTimeLimit)
						break;
				} else if (totalTime >= minSessionTimeLimit)
					break;
			}

			// Validate session time.
			boolean validSession = false;
			if (isMorning)
				validSession = (totalTime == maxSessionTimeLimit);
			else
				validSession = (totalTime >= minSessionTimeLimit && totalTime <= maxSessionTimeLimit);

			// If session is valid add to the possible combination list
			if (validSession) {
				possibleCombinationsOfTasks.add(possibleCombinationList);
				for (Task task : possibleCombinationList) {
					task.setScheduled(true);
				}
				possibleCombinationCount++;
				if (possibleCombinationCount == numberOfPossibleTracks)
					break;
			}
		}

		return possibleCombinationsOfTasks;
	}

	/**
	 * Get the schedule list.
	 * 
	 * @param possibleTasksForMornSessions
	 *            possible tasks for morning session
	 * @param possibleTasksForEveSessions
	 *            possible tasks for evening session
	 * @param numberOfPossibleTracks
	 *            number of tracks
	 * @throws InvalidTaskException
	 *             when exception occurred during the process
	 */
	private List<Schedule> getScheduledTasksList(
			List<List<Task>> possibleTasksForMornSessions,
			List<List<Task>> possibleTasksForEveSessions,
			int numberOfPossibleTracks) throws InvalidTaskException {

		List<Schedule> scheduleList = new ArrayList<Schedule>();

		// Schedule tasks for each track
		for (int i = 0; i < numberOfPossibleTracks; i++) {
			List<Task> taskList = new ArrayList<Task>();

			int trackCount = i + 1;
			String scheduledTime = ScheduleUtil.getNextScheduledTime(
					ScheduleUtil.getProperty("conference.start.time"), 0);

			// Schedule morning session.
			List<Task> mornSessionTaskList = possibleTasksForMornSessions
					.get(i);
			for (Task task : mornSessionTaskList) {
				task.setScheduledTime(scheduledTime);
				scheduledTime = ScheduleUtil.getNextScheduledTime(
						scheduledTime, task.getTimeDuration());
				taskList.add(task);
			}

			// Check whether evening session is available
			if (possibleTasksForEveSessions.size() > i) {
				// Scheduled Lunch Time
				Task lunchTask = new Task(
						ScheduleUtil.getProperty("lunch.title"),
						Integer.parseInt(ScheduleUtil
								.getProperty("lunch.time.duration")));
				lunchTask.setScheduledTime(scheduledTime);
				taskList.add(lunchTask);

				// Evening Session - set the evening session start time.
				scheduledTime = ScheduleUtil
						.getNextScheduledTime(
								scheduledTime,
								Integer.parseInt(ScheduleUtil
										.getProperty("lunch.time.duration")));
				List<Task> eveSessionTaskList = possibleTasksForEveSessions
						.get(i);
				for (Task task : eveSessionTaskList) {
					task.setScheduledTime(scheduledTime);
					taskList.add(task);
					scheduledTime = ScheduleUtil.getNextScheduledTime(
							scheduledTime, task.getTimeDuration());
				}

				// Scheduled Networking Event at the end of session.
				Task netEventTask = new Task(
						ScheduleUtil
								.getProperty("conference.networking.event.title"));
				netEventTask.setScheduledTime(scheduledTime);
				taskList.add(netEventTask);
				Schedule schedule = new Schedule();
				schedule.setTaskList(taskList);
				schedule.setTrackId(trackCount);
				scheduleList.add(schedule);
			}
		}

		return scheduleList;
	}

}
