package bd.clinic.modules.restTemplate

import bd.clinic.modules.infrastructure.exceptions.LabServiceClientException
import bd.clinic.modules.order.ExaminationResultDTO
import bd.clinic.modules.order.OrderDTO
import bd.clinic.modules.order.OrderResultDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI


@Service
class LabServiceClient @Autowired internal constructor(
        private val restTemplate: RestTemplate,
        @Value("\${self.services.first-lab.protocol}") private val labServiceProtocol: String,
        @Value("\${self.services.first-lab.uris.examinations}") private val examinationsUri: String,
        @Value("\${self.services.first-lab.ip-addr}") private val ipAddress: String
) {

    companion object {

        val infrastructurePortMap = mapOf(1 to 8081)

    }

    private fun <T> call(action: () -> ResponseEntity<T>): T? {
        try {
            val response = action.invoke()
            return response.body
        } catch (ex: HttpClientErrorException) {
            throw LabServiceClientException(messageOf(ex), ex)
        } catch (ex: HttpServerErrorException) {
            throw LabServiceClientException(messageOf(ex), ex)
        }
    }

    fun sendRequest(orderDTO: OrderDTO, port: Int): Unit? {
        return call {
            restTemplate.exchange(getUrl(prepareUrl(port)), HttpMethod.POST, HttpEntity(orderDTO), Unit::class.java)
        }
    }

/*    fun sendRequest(orderNumber: String, port: Int): List<ExaminationResultDTO>? {
        val response = call {
            restTemplate.exchange(getUrl(prepareUrl(port)), HttpMethod.GET, HttpEntity(orderNumber), List::class.java)
        }
        return response as? List<ExaminationResultDTO>
    }*/

/*    fun sendRequest(orderNumber: String, port: Int): OrderResultDTO? {
        return call {
            restTemplate.exchange(getUrl(prepareUrl(port)), HttpMethod.GET, HttpEntity(orderNumber), OrderResultDTO::class.java)
        }
    }*/

    fun sendRequest(orderNumber: String, port: Int): List<ExaminationResultDTO>? {
        val response = call {
            restTemplate.exchange(getUrl(prepareUrl(port)), HttpMethod.GET, HttpEntity(orderNumber), List::class.java)
        }
        return response?.filterIsInstance<ExaminationResultDTO>()
    }

    fun getUrl(preparedUrl: String): URI {
        return UriComponentsBuilder.fromHttpUrl(preparedUrl).build().toUri()
    }

    private fun prepareUrl(port: Int): String {
        return "$labServiceProtocol://$ipAddress:$port$examinationsUri"
    }

    private fun messageOf(e: Exception): String {
        return e.message ?: "Unknown message"
    }
}