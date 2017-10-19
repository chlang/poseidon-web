package edu.mum.se.poseidon.web.models.schedule;

public enum ScheduleStatus {
    DRAFT(0),
    ACTIVE(1);

    private final int value;
    ScheduleStatus(int val){
        this.value = val;
    }

    public int getValue(){
        return value;
    }
}
