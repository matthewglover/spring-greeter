package com.matthewglover.greeter

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient

internal class RouteConfigurationTest {

    @Test
    internal fun `GET greet returns 200 OK`() {
        val client = WebTestClient
                .bindToController(RouteConfiguration().routes())
                .build()

        client.get()
                .uri("/greet")
                .exchange()
                .expectStatus().isOk
                .expectBody().consumeWith { response ->
                    val bodyContent = String(response.responseBodyContent!!)
                    Assertions.assertEquals("Hello, World!", bodyContent)
                }
    }
}
