package dekilla.core.util.generator

import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.util.*

@Service
class NumberIdGenerator : IdGenerator {
    override fun generate(): String {
        val symbols: String = "0123456789"
        val random: Random = Random()
        val randomString: RandomString = RandomString(9, SecureRandom(), symbols)

        return randomString.nextString()
    }
}