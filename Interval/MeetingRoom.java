package Interval;

import java.util.List;
import java.util.PriorityQueue;

public class MeetingRoom {

    public boolean canAttendMeetings(List<Interval> intervals) {
        intervals.sort((a, b) -> a.start - b.start);
        for (int i = 0; i < intervals.size() - 1; i++) {
            if (intervals.get(i).end > intervals.get(i + 1).start) {
                return false;
            }
        }
        return true;
    }

    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0)
            return 0;

        // Sort by start time
        intervals.sort((a, b) -> a.start - b.start);

        // Min-heap based on meeting end times
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (Interval interval : intervals) {
            // If room is free, remove it from the heap
            if (!minHeap.isEmpty() && interval.start >= minHeap.peek()) {
                minHeap.poll(); // reuse the room
            }
            // Add the current meeting's end time to the heap
            minHeap.offer(interval.end);
        }

        // The heap size is the number of rooms used
        return minHeap.size();
    }

     public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0, twenty = 0;
        for (int bill : bills) {
            if (bill == 5) five++;
            else if (bill == 10) {
                five--; 
                ten++;
            }
            else if (ten > 0) {
                ten--; 
                five--;
            }
            else five -= 3;
            if (five < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MeetingRoom meetingRoom = new MeetingRoom();
        List<Interval> intervals = List.of(new Interval(0, 40), new Interval(5, 10), new Interval(15, 20));
        // System.out.println(meetingRoom.canAttendMeetings(intervals));
        System.out.println(meetingRoom.minMeetingRooms(intervals));
    }
}
