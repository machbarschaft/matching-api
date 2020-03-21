package com.colivery.engine.service

import com.colivery.engine.model.Coordinate
import com.colivery.engine.model.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

enum class PoIType {
    Supermarket, Pharmacy, grocery
}

data class PoI(val type: PoIType, val location: Coordinate, val address: String, val name: String)

@Service
class PoIService {

    @Autowired
    lateinit var distanceService: DistanceService

    fun findBestPoI(start: Coordinate, dropOff: Coordinate, type: PoIType, allPoIs: List<PoI>): PoI {
        var pois = allPoIs.filter { poi -> poi.type == type }

        val minBy = pois
                .minBy { poi ->
                    distanceService.calculateDistance(start, poi.location) + distanceService.calculateDistance(poi.location, dropOff)
                }
        return minBy as PoI
    }

    fun extractPoIs(orders: List<Order>): Array<PoI> {
        return orders
                .filter { order -> order.pickupLocation != null }
                .map { order ->
                    PoI(order.shopType, order.pickupLocation as Coordinate,
                            order.pickupAddress ?: "", order.shopName ?: "")
                }
                .toTypedArray();
    }
}