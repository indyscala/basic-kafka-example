package com.example.basic

import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.util.Try

case class BasicConsumer(bootstrapServers: String, groupId: String) {
  val kafkaProps = new Properties()

  kafkaProps.put("bootstrap.servers", bootstrapServers);
  kafkaProps.put("group.id", groupId)
  kafkaProps.put("zookeeper.session.timeout.ms", "400")
  kafkaProps.put("zookeeper.sync.time.ms", "200")
  kafkaProps.put("auto.commit.interval.ms", "1000")
  // when in doubt, read everything
  kafkaProps.put("auto.offset.reset","earliest")
  kafkaProps.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
  kafkaProps.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
  kafkaProps.put("partition.assignment.strategy", "roundrobin")

  private val consumer = new KafkaConsumer[String, String](kafkaProps)

  def subscribe(topic: String): Unit = consumer.subscribe(topic)
  def unSubscribe(topic: String): Unit = consumer.unsubscribe(topic)
  def close(): Unit = consumer.close()

  def poll(timeout: Int): Seq[String] = {

   val pollingResults = consumer.poll(100)
    if(pollingResults.isEmpty) Seq.empty else {
      val allRecords = pollingResults.values().asScala.toSeq
      allRecords flatMap { byPartitionAndTopic => byPartitionAndTopic.records().asScala.toSeq map { byTopic => byTopic.value() } }
    }
  }

}


