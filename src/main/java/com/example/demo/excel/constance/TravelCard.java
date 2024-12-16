package com.example.demo.excel.constance;

public enum TravelCard {
    TRAVEL_CARD_1("Громадянський(загальнi мiсячний)"),
    TRAVEL_CARD_2("Громадянський(трамвай мiсячний)"),
    TRAVEL_CARD_3("Громадянський(тролейбус мiсячний)"),
    TRAVEL_CARD_4("Проїзний (загальний 7 дiб)"),
    TRAVEL_CARD_5("Проїзний (загальний 10 дiб)"),
    TRAVEL_CARD_6("Проїзний (загальний 15 дiб)"),
    TRAVEL_CARD_7("Проїзний (трамвай 7 дiб)"),
    TRAVEL_CARD_8("Проїзний (трамвай 10 дiб)"),
    TRAVEL_CARD_9("Проїзний (трамвай 15 дiб)"),
    TRAVEL_CARD_10("Проїзний (тролейбус 7 дiб)"),
    TRAVEL_CARD_11("Проїзний (тролейбус 10 дiб)"),
    TRAVEL_CARD_12("Проїзний (тролейбус 15 дiб)"),
    TRAVEL_CARD_13("Студентський (загальний мiсячний)"),
    TRAVEL_CARD_14("Студентський(трамвай мiсячний)"),
    TRAVEL_CARD_15("Студентський(тролейбус мiсячний)"),
    TRAVEL_CARD_16("Учнiвський (загальний мiсячний)"),
    TRAVEL_CARD_17("Учнiвський (трамвай мiсячний)"),
    TRAVEL_CARD_18("Учнiвський(тролейбус мiсячний)"),
    TRAVEL_CARD_19("Проїзний (загальний 90 дiб)"),
    TRAVEL_CARD_20("Проїзний (трамвай 90 дiб)"),
    TRAVEL_CARD_21("Проїзний (тролейбус 90 дiб)");

    private String fullName;

    TravelCard(String fullName){
        this.fullName = fullName;
    }

    public String getFullName(){
        return fullName;
    }
}
