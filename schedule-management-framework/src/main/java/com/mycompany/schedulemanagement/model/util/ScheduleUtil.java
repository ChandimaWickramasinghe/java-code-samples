package com.mycompany.schedulemanagement.model.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.mycompany.schedulemanagement.exception.InvalidTaskException;
import com.mycompany.schedulemanagement.model.ScheduleConstant;
import com.mycompany.schedulemanagement.model.dto.Task;

/**
 * ScheduleUtil class implements common methods to facilitate scheduling.
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 * @since 2014-12-19
 */
public class ScheduleUtil {

	// Holds property file name
	private static final String BUNDLE_NAME = "schedule-properties";
	// resource bundle to read the property file
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	/**
	 * Load the property for the given key
	 * 
	 * @param key
	 *            property key
	 * @return property value
	 */
	public static String getProperty(String key) {

		return RESOURCE_BUNDLE.getString(key);
	}

	/**
	 * Load task list from input file.
	 * 
	 * @param filePath
	 *            path of the input file
	 * @return list of tasks
	 * @throws InvalidTaskException
	 *             if exception occurred during the process
	 */
	public static List<String> getTaskListFromFile(String filePath)
			throws InvalidTaskException {
		List<String> taskList = new ArrayList<String>();
		try (// Open input file.
			FileInputStream fstream = new FileInputStream(filePath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));) {

			String strLine = br.readLine();
			// Read the file
			while (strLine != null
					&& !strLine.trim().equals(ScheduleConstant.EMPTY_STRING)) {
				taskList.add(strLine);
				strLine = br.readLine();
			}
		} catch (IOException e) {
			throw new InvalidTaskException(
					ScheduleUtil.getProperty("exception.message.invalid.file")
							+ filePath);
		}

		return taskList;
	}

	/**
	 * Calculate total time for tasks
	 * 
	 * @param tasksList
	 *            list of tasks
	 * @return total time
	 */
	public static int getTotalTasksTime(List<Task> tasksList) {
		if (tasksList == null || tasksList.isEmpty()) {
			return 0;
		} else {
			return tasksList.stream().mapToInt(p -> p.getTimeDuration()).sum();
		}
	}

	/**
	 * Calculate number of tracks for tasks
	 * 
	 * @param tasksList
	 *            list of tasks
	 * @return number of tracks
	 */
	public static int getNumberOfPossibleTracks(List<Task> tasksList) {
		// Per day maximum number of hours * 60 mins is the available time for
		// sessions.
		return (int) Math.ceil((float) getTotalTasksTime(tasksList)
				/ (Integer.parseInt((ScheduleUtil
						.getProperty("conference.max.hours.per.day"))) * 60));
	}

	/**
	 * To get next scheduled time in form of String.
	 * 
	 * @param time
	 *            Expected format : HH:mm aa
	 * @param timeDuration
	 *            in minutes
	 * @return String of scheduled time format : HH:mm aa
	 * @throws InvalidTaskException
	 *             if exception occurred during the process
	 */
	public static String getNextScheduledTime(String time, int timeDuration)
			throws InvalidTaskException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
		Date date;
		try {
			date = dateFormat.parse(time);
			long timeInLong = date.getTime();

			long timeDurationInLong = 1000L * 60 * timeDuration;
			long newTimeInLong = timeInLong + timeDurationInLong;

			date.setTime(newTimeInLong);

		} catch (ParseException e) {
			throw new InvalidTaskException(
					ScheduleUtil.getProperty("exception.message.invalid.time")
							+ time);
		}

		return dateFormat.format(date);

	}

}
