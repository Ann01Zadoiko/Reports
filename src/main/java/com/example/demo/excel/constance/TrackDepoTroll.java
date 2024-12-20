package com.example.demo.excel.constance;

public enum TrackDepoTroll {
    TR2("2"),
    TR3("3"),
    TR7("7"),
    TR8("8"),
    TR8H("8Н"),
    TR9("9"),
    TR10("10"),
    TR12("12");

    private String track;

    TrackDepoTroll(String track){
        this.track = track;
    }

    public String getTrack(){
        return track;
    }
}
