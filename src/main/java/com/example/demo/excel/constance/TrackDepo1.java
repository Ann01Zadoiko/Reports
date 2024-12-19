package com.example.demo.excel.constance;

public enum TrackDepo1 {
    TR3("3"),
    TR5("5"),
    TR10("10"),
    TR11("11"),
    TR13("13"),
    TR17("17"),
    TR26("26"),
    TR27("27"),
    TR28("28");

    private String track;

    TrackDepo1(String track){
        this.track = track;
    }

    public String getTrack(){
        return track;
    }
}
