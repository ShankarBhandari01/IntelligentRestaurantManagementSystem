package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;
import com.shankar.intelligentrestaurantmanagementsystem.entity.KotItem;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Station;

import java.util.List;

public interface KotPrinterService {
    void printKot(Kot kot);

    void printKotItems(List<KotItem> items, Station station);
}
