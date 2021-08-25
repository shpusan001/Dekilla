package dekilla.core.domain

import java.io.Serializable

data class SockDto(
    val command: String,
    val seperator: String,
    val data: String,
    val obj: Any
) : Serializable