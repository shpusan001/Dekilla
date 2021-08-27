package dekilla

import dekilla.core.AppConfig
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class MyApplicationContext {
    companion object {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    }
}