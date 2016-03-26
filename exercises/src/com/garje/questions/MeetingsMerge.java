/**
 * Your company built an in-house calendar tool called HiCal. 
 * You want to add a feature to see the times in a day when everyone is available. 
 */
package com.garje.questions;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator; 

/**
 * @author sanjay.garje
 *
 */
public class MeetingsMerge {

	public static List<Meeting> mergeRanges(List<Meeting> meetings) {

		// sort by start times, breaking ties with end times
		List<Meeting> sortedMeetings = new ArrayList<Meeting>(meetings);
		Collections.sort(sortedMeetings, new Comparator<Meeting>() {
			public int compare(Meeting m1, Meeting m2) {
				return m1.startTime - m2.startTime;
			}
		});
		
		System.out.println("The sorted input is: " + sortedMeetings.toString());
		// meetings only go in mergedMeetings when we're sure they can't be
		// merged further
		List<Meeting> mergedMeetings = new ArrayList<Meeting>();

		Meeting previousMeeting = sortedMeetings.get(0);

		for (Meeting currentMeeting : sortedMeetings) {

			// if the previous meeting can be merged with the current one
			// that is, if current meeting starts before previous meeting ends:
			if (currentMeeting.startTime <= previousMeeting.endTime) {

				// merge the currentMeeting back into the previousMeeting
				// and keep the resulting meeting as the previousMeeting
				// because this newly-merged meeting might still
				// need to be merged with the next meeting
				previousMeeting.endTime = Math.max(currentMeeting.endTime,
						previousMeeting.endTime);

				// else the previous meeting can't be merged with anything else
			} else {

				// put it in mergedMeetings
				// and move on to trying to merge the current meeting into
				// subsequent meetings
				mergedMeetings.add(new Meeting(previousMeeting.startTime,
						previousMeeting.endTime));

				previousMeeting = currentMeeting;
			}
		}

		// put last meeting we were trying to merge in our final set
		mergedMeetings.add(new Meeting(previousMeeting.startTime,
				previousMeeting.endTime));

		return mergedMeetings;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Meeting> testMeetings = new ArrayList<Meeting>();
		testMeetings.add(new Meeting(4, 8));
		testMeetings.add(new Meeting(0, 1));
		testMeetings.add(new Meeting(3, 5));
		testMeetings.add(new Meeting(10, 12));
		testMeetings.add(new Meeting(9, 10));
		System.out.println("The input is: " + testMeetings.toString());
		System.out.println("The output is: " + mergeRanges(testMeetings).toString());
		
	}

}
