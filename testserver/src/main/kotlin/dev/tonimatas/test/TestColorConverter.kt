package dev.tonimatas.test

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase

class TestColorConverter : ForegroundCompositeConverterBase<ILoggingEvent>() {
    override fun getForegroundColorCode(event: ILoggingEvent): String {
        return when (event.level.toInt()) {
            20000 -> "34"
            30000 -> "33"
            40000 -> "31"
            else -> "39"
        }
    }

    override fun transform(event: ILoggingEvent, input: String): String {
        if (input == "INFO" || input == "WARN" || input == "ERROR") {
            return super.transform(event, input)
        }

        return "$input§r"
            .replace("§0".toRegex(), "\u001B[30m")
            .replace("§1".toRegex(), "\u001B[34m")
            .replace("§2".toRegex(), "\u001B[32m")
            .replace("§3".toRegex(), "\u001B[36m")
            .replace("§4".toRegex(), "\u001B[31m")
            .replace("§5".toRegex(), "\u001B[35m")
            .replace("§6".toRegex(), "\u001B[33m")
            .replace("§7".toRegex(), "\u001B[37m")
            .replace("§8".toRegex(), "\u001B[90m")
            .replace("§9".toRegex(), "\u001B[94m")
            .replace("§a".toRegex(), "\u001B[92m")
            .replace("§b".toRegex(), "\u001B[96m")
            .replace("§c".toRegex(), "\u001B[91m")
            .replace("§d".toRegex(), "\u001B[95m")
            .replace("§e".toRegex(), "\u001B[93m")
            .replace("§f".toRegex(), "\u001B[97m")
            .replace("§r".toRegex(), "\u001B[0m")
    }
}