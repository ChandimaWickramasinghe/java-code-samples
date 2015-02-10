package com.mycompany.schedulemanagement.model.dto;

import java.util.List;

import com.mycompany.schedulemanagement.model.util.ScheduleUtil;

/**
 * Schedule class holds the information related to a particular track.
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 * @since 2014-12-19
 */
public class Schedule {

	/** Track id holds the track number. */
	private int trackId;

	/** taskList holds List of tasks in the schedule. */
	List<Task> taskList;

	/**
	 * Getter for taskList
	 * 
	 * @return List of tasks
	 */
	public List<Task> getTaskList() {
		return taskList;
	}

	/**
	 * Setter for taskList
	 * 
	 * @param taskList
	 *            List of tasks
	 */
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	/**
	 * Getter for track id
	 * 
	 * @return track id
	 */
	public int getTrackId() {
		return trackId;
	}

	/**
	 * Setter for track id
	 * 
	 * @param trackId
	 *            track id
	 */
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	/**
	 * Override toString method to display all element in the schedule object.
	 */
	@Override
	public String toString() {
		StringBuffer details = new StringBuffer();
		details.append("Track: ");
		details.append(trackId);
		details.append("\n");
		if (taskList != null) {
			for (Task task : taskList) {
				if (ScheduleUtil.getProperty("conference.networking.event.title")
						.equals(task.getTitle())) {
					details.append(task.getScheduledTime());
					details.append(" ");
					details.append(task.getTitle());
					details.append("\n\n");
				} else {
					details.append(task.getScheduledTime());
					details.append(" ");
					details.append(task.getTitle());
					details.append(" ");
					details.append(task.getTimeDuration());
					details.append("min\n");
				}
			}
		} else {
			details.append("Task list is null");
		}
		return details.toString();

	}

}
