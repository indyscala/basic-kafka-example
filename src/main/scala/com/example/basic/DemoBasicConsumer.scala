package com.example.basic

import org.apache.kafka.clients.consumer.ConsumerRecords

object DemoBasicConsumer {

  def main(args: Array[String]): Unit = {

    if (args.length != 3) {
      println("DemoBasicConsumer {bootstrapServers} {groupId} {topic}")
      return;
    }

    val bootstrapServers = args(0)
    val groupId = args(1)
    val topic = args(2)

    val myConsumer = BasicConsumer(bootstrapServers, groupId)
    myConsumer.subscribe(topic)


    while(true)
      {
        val records = myConsumer.poll(100)
        records map {v => println(v)}
      }

  }

}
