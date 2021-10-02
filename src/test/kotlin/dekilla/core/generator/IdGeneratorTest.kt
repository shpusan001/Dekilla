package dekilla.core.generator

import dekilla.core.util.generator.IdGenerator
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class IdGeneratorTest {

    @Test
    fun NumberIdGenerator() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        val idGenerator: IdGenerator = ac.getBean("numberIdGenerator") as IdGenerator
        println(idGenerator.generate())
    }
}