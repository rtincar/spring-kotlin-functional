package functional

import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Shared
import spock.lang.Specification

class IntegrationSpec extends Specification {

    @Shared
    def app = new Application(8181)

    def client = WebTestClient.bindToServer().baseUrl('http://localhost:8181').build()

    def setupSpec() {
        app.start()
    }

    def cleanupSpec() {
        app.stop()
    }

    void "Should do get something"() {

        when: "Ask for clients"
        WebTestClient.ResponseSpec exchange = client.get().uri('/api/users').accept(MediaType.APPLICATION_JSON).exchange()

        then: "Body contains bla"

        exchange.expectStatus().isOk()
        exchange.expectBodyList(User.class)


    }
}
