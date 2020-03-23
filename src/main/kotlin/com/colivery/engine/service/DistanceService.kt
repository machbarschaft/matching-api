package com.colivery.engine.service

import com.colivery.engine.model.Coordinate
import org.springframework.stereotype.Service

@Service
class DistanceService {
    val earthRadiusKm: Double = 6372.8

    fun haversine(position: Coordinate, position2: Coordinate): Double {
        val dLat = Math.toRadians(position2.latitude - position.latitude);
        val dLon = Math.toRadians(position2.longitude - position.longitude);
        val originLat = Math.toRadians(position.latitude);
        val destinationLat = Math.toRadians(position2.latitude);

        val a = Math.pow(Math.sin(dLat / 2), 2.toDouble()) + Math.pow(Math.sin(dLon / 2), 2.toDouble()) * Math.cos(originLat) * Math.cos(destinationLat);
        val c = 2 * Math.asin(Math.sqrt(a));
        return earthRadiusKm * c;
    }
}