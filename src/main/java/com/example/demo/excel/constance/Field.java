package com.example.demo.excel.constance;

public enum Field {
    TRAM("Вагон"),
    COUNT_OF_TICKETS("Кількість одноразових квитків"),
    AMOUNT("Сума"),
    TRACK("Маршрут"),
    DEPO("Депо"),
    TRAVEL_CARD("Вид проїздного");

    private String fullName;

    Field(String fullName){
        this.fullName = fullName;
    }

    public String getFullName(){
        return fullName;
    }
}
