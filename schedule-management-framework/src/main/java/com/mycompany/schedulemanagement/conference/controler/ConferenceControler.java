package com.mycompany.schedulemanagement.conference.controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.mycompany.schedulemanagement.conference.model.ConferenceManagerImpl;
import com.mycompany.schedulemanagement.exception.InvalidTaskException;
import com.mycompany.schedulemanagement.model.ScheduleManager;
import com.mycompany.schedulemanagement.model.dto.Schedule;
import com.mycompany.schedulemanagement.model.util.ScheduleUtil;

/**
 * ConferenceControler is the controller to load the schedule details with
 * provided text input or provided tasks list. This class provides the functions
 * to schedule conferences.
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 * @since 2014-12-19
 */
public class ConferenceControler {

	/**
	 * Schedule manager instance. Kept static as it is initialized inside
	 * main().
	 */
	static ScheduleManager conferenceManager;

	/**
	 * main method implementation as this example is to print the schedule
	 * details through cmd.
	 * 
	 * @param args
	 *            String array.
	 */
	public static void main(String[] args) {
		String fileName;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				System.in))) {
			System.out.println(ScheduleUtil
					.getProperty("conference.filepath.message"));
			// read the file name from cmd input.
			fileName = br.readLine();
			conferenceManager = new ConferenceManagerImpl();
			List<Schedule> schedules;

			schedules = conferenceManager.schedule(fileName);

			for (Schedule schedule : schedules) {
				// Call toString method of Schedule DTO print the details.
				System.out.println(schedule.toString());
			}
		} catch (InvalidTaskException | IOException ite) {
			ite.printStackTrace();
		}
	}

}
