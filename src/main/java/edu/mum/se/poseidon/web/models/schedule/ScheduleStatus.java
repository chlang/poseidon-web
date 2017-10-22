package edu.mum.se.poseidon.web.models.schedule;

import java.util.HashMap;
import java.util.Map;

// TODO: Add display name
public enum ScheduleStatus {
    DRAFT(0),
    ACTIVE(1),
    REVIEW(2);

    private final int value;
    private static Map<Integer, ScheduleStatus> map = new HashMap<Integer, ScheduleStatus>();

    static {
        for (ScheduleStatus status : ScheduleStatus.values())
            map.put(status.value, status);
    }

    ScheduleStatus(int val){
        this.value = val;
    }

    public static ScheduleStatus valueOf(int v){
        return map.get(v);
    }

    public int getValue() {
        return value;
    }
}
