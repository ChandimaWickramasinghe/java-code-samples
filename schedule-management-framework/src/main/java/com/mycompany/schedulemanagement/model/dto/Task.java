package com.mycompany.schedulemanagement.model.dto;

/**
 * Task class holds the information related to task.
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 * @since 2014-12-19
 */
public class Task {
	// Title of the task
	String title;
	// Time duration of the task
	int timeDuration;
	// if task is already scheduled set to true
	boolean scheduled;
	// Scheduled time of the task
	String scheduledTime;

	/**
	 * Constructor for Task.
	 * 
	 * @param title
	 *            title of the task
	 * @param timeDuration
	 *            time duration
	 */
	public Task(String title, int timeDuration) {
		this.title = title;
		this.timeDuration = timeDuration;
	}

	/**
	 * Constructor for Task. Used to create tasks which are not having time
	 * duration.
	 * 
	 * @param title
	 *            title of the task
	 */
	public Task(String title) {
		this.title = title;
	}

	/**
	 * Getter for time duration
	 * 
	 * @return time duration
	 */
	public int getTimeDuration() {
		return timeDuration;
	}

	/**
	 * Getter for title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter to set the task value to scheduled
	 * 
	 * @param scheduled
	 *            true if scheduled
	 */
	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	/**
	 * Getter to get the task status of scheduled
	 * 
	 * @return true if scheduled
	 */
	public boolean isScheduled() {
		return scheduled;
	}

	/**
	 * Setter for scheduled time.
	 * 
	 * @param scheduledTime
	 *            schedule time. Format is "HH:mm aa:
	 */
	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	/**
	 * Getter for scheduled time.
	 * 
	 * @return schedule time. Format is "HH:mm aa:
	 */
	public String getScheduledTime() {
		return scheduledTime;
	}

}
