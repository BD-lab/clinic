package bd.clinic.modules.restTemplate

import bd.clinic.modules.infrastructure.exceptions.LabServiceClientException
import bd.clinic.modules.order.examinationResult.ExaminationResultDTO
import bd.clinic.modules.order.OrderDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
        private val gson: Gson,
        @Value("\${self.services.first-lab.protocol}") private val labServiceProtocol: String,
        @Value("\${self.services.first-lab.uris.examinations}") private val examinationsUri: String,
        @Value("\${self.services.first-lab.uris.order}") private val orderUri: String,
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
            restTemplate.exchange(getUrl(prepareUrlToSaveOrder(port)), HttpMethod.POST, HttpEntity(orderDTO), Unit::class.java)
        }
    }

    fun sendRequest(orderNumber: String, port: Int): List<ExaminationResultDTO>? {
        val response = call {
            restTemplate.exchange(getUrl(prepareUrlToGetResults(port)), HttpMethod.POST, HttpEntity(orderNumber), String::class.java)
        }
        val type = object : TypeToken<List<ExaminationResultDTO>>() {}

        return gson.fromJson<List<ExaminationResultDTO>>(response, type.type)
    }

    fun getUrl(preparedUrl: String): URI {
        return UriComponentsBuilder.fromHttpUrl(preparedUrl).build().toUri()
    }

    private fun prepareUrlToSaveOrder(port: Int): String {
        return "$labServiceProtocol://$ipAddress:$port$examinationsUri"
    }

    private fun prepareUrlToGetResults(port: Int): String {
        return "$labServiceProtocol://$ipAddress:$port$examinationsUri$orderUri"
    }

    private fun messageOf(e: Exception): String {
        return e.message ?: "Unknown message"
    }
}