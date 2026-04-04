package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Station;

import java.util.List;

public interface IStationService {
    Station createStation(Station station);

    List<Station> getAllStations();
}
