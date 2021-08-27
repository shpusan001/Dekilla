package dekilla.core.client.view

import dekilla.core.AppConfig
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import javax.swing.JOptionPane

class ClientViewTest {

    @Test
    fun clientViewTest() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        val mainView = ac.getBean(MainView::class.java)
        mainView.create()

        Thread.sleep(1000)
    }

    @Test
    fun popUpTest() {
        JOptionPane.showConfirmDialog(null, "확인하시겠습니까?")

        Thread.sleep(1000)
    }
}