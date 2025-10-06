package GoogleClassroom.ReferenceClass;

import GoogleClassroom.LIST.DoublyLinkedList;

public class CourseSched<Professor> extends DoublyLinkedList<Professor> {
    private String schedule;
    private String room;
    private String timeSlot;

    public CourseSched() {
        this.schedule = null;
        this.room = null;
        this.timeSlot = null;
    }

    public CourseSched(String schedule, String room, String timeSlot) {
        this.schedule = schedule;
        this.room = room;
        this.timeSlot = timeSlot;
    }

    // Getters
    public String getSchedule() { return schedule; }
    public String getRoom() { return room; }
    public String getTimeSlot() { return timeSlot; }

    // Setters
    public void setSchedule(String schedule) { this.schedule = schedule; }
    public void setRoom(String room) { this.room = room; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    @Override
    public String toString() {
        return schedule + " - " + room + " (" + timeSlot + ")";
    }
}
