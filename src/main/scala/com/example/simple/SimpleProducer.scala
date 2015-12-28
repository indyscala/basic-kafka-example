package com.example.simple

import org.apache.kafka.clients.producer.KafkaProducer
import kafka.producer.Producer


case class SimpleProducer(topic: String) extends ProducerTrait {
  private lazy val producer = new KafkaProducer[]()


  def configure(brokerList: String, sync: String): Unit = ???
  def start():Unit = ???
  def produce(s: String): Unit = ???
  def close():Unit = ???
}
