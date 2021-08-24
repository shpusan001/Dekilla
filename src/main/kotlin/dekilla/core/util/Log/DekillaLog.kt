package dekilla.core.util.Log

import java.time.LocalDateTime

class DekillaLog {
    companion object {

        var isActivate: Boolean = true

        fun log(log: String) {
            if (isActivate) {
                val localDateTime: LocalDateTime = LocalDateTime.now()
                println("[${localDateTime}] " + log)
            }
        }
    }
}