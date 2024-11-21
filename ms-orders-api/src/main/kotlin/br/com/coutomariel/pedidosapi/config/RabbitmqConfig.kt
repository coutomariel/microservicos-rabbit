package br.com.coutomariel.pedidosapi.config

import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitmqConfig {

    @Value("\${rabbitmq.exchange.name}")
    private lateinit var exchangeName: String

    @Bean
    fun ordersExchange() = FanoutExchange(exchangeName)

    @Bean
    fun rabbitAdmin(connectionFactory: ConnectionFactory) = RabbitAdmin(connectionFactory)

    @Bean
    fun messageConverter() = Jackson2JsonMessageConverter()

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory, messageConverter: MessageConverter
    ): RabbitTemplate{
        val rabbitTemplate =  RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = messageConverter
        return rabbitTemplate
    }

    @Bean
    fun applicationReadyEventApplicationListener(rabbitAdmin: RabbitAdmin): ApplicationListener<ApplicationEvent> {
        return ApplicationListener { rabbitAdmin.initialize() }
    }
}