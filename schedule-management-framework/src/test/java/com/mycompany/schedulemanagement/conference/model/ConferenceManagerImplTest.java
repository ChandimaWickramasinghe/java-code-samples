package com.mycompany.schedulemanagement.conference.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mycompany.schedulemanagement.conference.model.ConferenceManagerImpl;
import com.mycompany.schedulemanagement.exception.InvalidTaskException;
import com.mycompany.schedulemanagement.model.util.ScheduleUtil;

/**
 * ConferenceManagerImplTest is the Test class for ConferenceManagerImpl
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 * @since 2014-12-19
 */
public class ConferenceManagerImplTest {

	ConferenceManagerImpl conferenceManager = new ConferenceManagerImpl();
	static String validFileName;
	static String invalidFileName;
	static String validTask;
	static String invalidFormatTask;
	static String invalidNameTask;
	static String invalidTimeSuffixTask;
	static String invalidTimeFormatTask;
	static String invalidTimeDurationTask;

	static {
		validFileName = ClassLoader.getSystemResource("TaskDetails.txt").getPath();
		invalidFileName = "InvalidPath";
		validTask = "Valid Task 30min";
		invalidFormatTask = "Invalidformattask40min";
		invalidNameTask = " 40min";
		invalidTimeSuffixTask = "Invalid time suffix task 1hour";
		invalidTimeFormatTask = "Invalid time format task XImin";
		invalidTimeDurationTask = "Invalid time duration task 300min";
	}

	@Test
	public void testScheduleStringValidFile() {

		try {
			assertNotNull(conferenceManager.schedule(validFileName));
		} catch (InvalidTaskException e) {
			fail("Unexpected exception occured. " + validFileName);
		}
	}

	@Test
	public void testScheduleStringInvalidFile() {

		try {
			conferenceManager.schedule(invalidFileName);
			fail("Expected exception didn't occure");
		} catch (InvalidTaskException e) {
			assertTrue((ScheduleUtil
					.getProperty("exception.message.invalid.file") + invalidFileName)
					.equals(e.getMessage()));
		}
	}

	@Test
	public void testScheduleListOfStringNullTaskList() {

		List<String> taskList = null;
		try {
			conferenceManager.schedule(taskList);
			fail("Expected exception didn't occure");
		} catch (InvalidTaskException e) {
			assertTrue(ScheduleUtil.getProperty(
					"exception.message.null.task.list").equals(e.getMessage()));
		}
	}

	@Test
	public void testScheduleListOfStringIncorrectFormatTaskList() {

		List<String> taskList = new ArrayList<String>();
		taskList.add(validTask);
		taskList.add(invalidFormatTask);
		try {
			conferenceManager.schedule(taskList);
			fail("Expected exception didn't occure");
		} catch (InvalidTaskException e) {
			assertTrue((ScheduleUtil
					.getProperty("exception.message.scheduling.not.succesful"))
					.equals(e.getMessage()));
		}
	}

	@Test
	public void testScheduleListOfStringIncorrectNameTaskList() {

		List<String> taskList = new ArrayList<String>();
		taskList.add(validTask);
		taskList.add(invalidNameTask);
		try {
			conferenceManager.schedule(taskList);
			fail("Expected exception didn't occure");
		} catch (InvalidTaskException e) {
			assertTrue((ScheduleUtil
					.getProperty("exception.message.invalid.name") + invalidNameTask)
					.equals(e.getMessage()));
		}
	}

	@Test
	public void testScheduleListOfStringInvalidTimeSuffix() {

		List<String> taskList = new ArrayList<String>();
		taskList.add(validTask);
		taskList.add(invalidTimeSuffixTask);
		try {
			conferenceManager.schedule(taskList);
			fail("Expected exception didn't occure");
		} catch (InvalidTaskException e) {
			assertTrue((ScheduleUtil
					.getProperty("exception.message.invalid.format") + invalidTimeSuffixTask)
					.equals(e.getMessage()));
		}
	}

	@Test
	public void testScheduleListOfStringInvalidTimeDuration() {

		List<String> taskList = new ArrayList<String>();
		taskList.add(validTask);
		taskList.add(invalidTimeFormatTask);
		try {
			conferenceManager.schedule(taskList);
			fail("Expected exception didn't occure");
		} catch (InvalidTaskException e) {
			assertTrue((ScheduleUtil
					.getProperty("exception.message.invalid.format") + invalidTimeFormatTask)
					.equals(e.getMessage()));
		}
	}

	@Test
	public void testScheduleListOfStringLongerTimeDuration() {

		List<String> taskList = new ArrayList<String>();
		taskList.add(validTask);
		taskList.add(invalidTimeDurationTask);
		try {
			conferenceManager.schedule(taskList);
			fail("Expected exception didn't occure");
		} catch (InvalidTaskException e) {
			assertTrue((ScheduleUtil
					.getProperty("exception.message.invalid.time.duration") + invalidTimeDurationTask)
					.equals(e.getMessage()));
		}
	}

}
