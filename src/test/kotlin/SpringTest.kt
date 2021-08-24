import dekilla.core.AppConfig
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import dekilla.core.server.ServerManager
import org.junit.jupiter.api.Assertions

class SpringTest {

    @Test
    fun springTest() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        val serverManager1: ServerManager = ac.getBean("serverManager") as ServerManager
        println(serverManager1)
        val serverManager2: ServerManager = ac.getBean("serverManager") as ServerManager
        println(serverManager2)
        Assertions.assertEquals(serverManager1, serverManager2)
    }
}