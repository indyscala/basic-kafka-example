package com.example.simple

import scala.concurrent.ExecutionException


trait ProducerTrait {

  /**
    * create configuration for the producer
    * consult Kafka documentation for exact meaning of each configuration parameter
    */
  def configure(brokerList: String, sync: String): Unit

  /**
    * start the producer
    */
  def start():Unit

  /**
    * create record and Send it to Kafka
    * because the key is null, data will sent to a random partition
    * exact behavior will be different depending on the producer implementation
    */
  @throws[ExecutionException]("usually thrown if there is a timeout")
  @throws[InterruptedException]("another possible Kafka exeption")
  def produce(s: String): Unit

  def close():Unit

}
