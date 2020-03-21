package com.colivery.engine

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EngineApplication

fun main(args: Array<String>) {
	runApplication<EngineApplication>(*args){
		setBannerMode(Banner.Mode.OFF)
	}
}
