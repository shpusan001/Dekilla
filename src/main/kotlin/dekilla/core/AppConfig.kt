package dekilla.core

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@Configuration
@ComponentScan(basePackages = ["dekilla.core"])
open class AppConfig {
}