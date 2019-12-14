package bd.clinic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class ClinicApplication

fun main(args: Array<String>) {
	val builder = SpringApplicationBuilder(ClinicApplication::class.java)
	builder.headless(false).run(*args)
}