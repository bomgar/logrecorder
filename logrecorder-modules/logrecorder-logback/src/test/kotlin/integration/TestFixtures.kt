package integration

import org.slf4j.LoggerFactory
import org.slf4j.helpers.BasicMarkerFactory
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class TestServiceA {
    private val log = LoggerFactory.getLogger(javaClass)
    private val marker = BasicMarkerFactory().getMarker("marker a")

    fun logSomething() {
        log.trace(marker, "trace message a")
        log.debug(marker, "debug message a")
        log.info(marker, "info message a")
        log.warn(marker, "warn message a")
        log.error(marker, "error message a")
    }

    fun logSingleInfo() {
        log.info(marker, "info message a")
    }

    fun logError(exception: Throwable) {
        log.error("error message a", exception)
    }

    fun logInfoWithKeyValue() {
        log
            .atInfo()
            .setMessage("Payload message")
            .addKeyValue("key", "value")
            .addKeyValue("keyInt", 1)
            .addKeyValue("keyNull", null as Any?)
            .log()
    }
}

class TestServiceB {
    private val log = LoggerFactory.getLogger(javaClass)
    private val marker = BasicMarkerFactory().getMarker("marker b")

    fun logSomething() {
        log.trace(marker, "trace message b")
        log.debug(marker, "debug message b")
        log.info(marker, "info message b")
        log.warn(marker, "warn message b")
        log.error(marker, "error message b")
    }
}
