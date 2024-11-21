package br.com.coutomariel.pedidosapi.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Configuration

@Configuration
class SpringDocOpenApiConfig {

    fun openApi(): OpenAPI {
        return OpenAPI().info(
            Info()
                .title("Api restfull")
                .description("Api para realização de pedidos")
                .license(License().name("Apache 2.0").url("www.site.com.br"))
                .contact(Contact().name("Mariel Vieira Couto").email("coutomariel@gmail.com"))
        )
    }

}