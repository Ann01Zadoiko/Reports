package com.example.demo.excel.constance;

public enum TrackDepo2 {
    TR1("1"),
    TR1K("1К"),
    TR6("6"),
    TR7("7"),
    TR7H("7Н"),
    TR12("12"),
    TR15("15"),
    TR20("20");

    private String track;

    TrackDepo2(String track){
        this.track = track;
    }

    public String getTrack(){
        return track;
    }
}
