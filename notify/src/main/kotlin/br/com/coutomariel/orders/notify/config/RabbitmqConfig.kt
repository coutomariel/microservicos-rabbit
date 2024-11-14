package br.com.coutomariel.orders.notify.config

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
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

    @Value("\${rabbitmq.queue.name}")
    private lateinit var queueName: String

    @Bean
    fun ordersExchange() = FanoutExchange(exchangeName)

    @Bean
    fun nofifyQueue() = Queue(queueName)

    @Bean
    fun binding() = BindingBuilder.bind(nofifyQueue()).to(ordersExchange())

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