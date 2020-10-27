# Apache Kafka & Java Spring Boot - Consumer
## Running simple message flow with docker

Start zookeeper and kafka:

`docker-compose up -d`

Get container list and retrieve the name of kafka container:

`docker ps`

### Consumer side setup
Get inside kafka container (on another terminal window):

`docker exec -it kafka_1 bash`

```
# Start a consumer which feeds on test topic
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic t_test
```

Producer side [setup](https://github.com/kutkaite/basic-kafka-producer#producer-side-setup)

## Consumers explained:
1. `KafkaConsumer.java` - simply consumes a `String` from topic `t_test`
2. `IncrementalConsumer.java` - simply consumes a `String` from topic `t_incremental`
3. `KafkaKeyConsumer.java` - consumes a `ConsumerRecord<String, String>` from topic `t_multiple_partitions` 
with `concurrency` equal to the amount of partitions
4. `EmployeeJsonConsumer.java` - consumes a `JSON` from topic `t_employee` and maps it to an `Employee` object.
5. `CommodityDashboardConsumer.java` - consumes a `JSON` message from a topic `t_commodity` and groupId = `cg-dashboard`
6. `CommodityNotificationConsumer.java` - consumes a `JSON` message from a topic `t_commodity` and groupId = `cg-notification`
7. `CarLocationConsumer.java` - has 2 methods one to consume all messages from topic `t_location` 
and another method to consume messages only from group id `cg-filtered-location` 
which is filtered by using `filteredLocationContainerFactory` container factory
8. `FoodOrderConsumer.java` - consumes a message when it satisfies all the conditions, 
if not conditions are met then an exception is thrown which is handled by `FoodOrderErrorHandler`.
9. `SimpleNumberConsumer.java` - consumes a message if a number is not odd. If the number is odd then an exception is thrown
which is handled by `GlobalErrorHandler`.
10. `ImageConsumer.java` - consumes a message and has a custom retry mechanism `imageRetryContainerFactory`
11. `InvoiceConsumer.java` - consumes a message from `t_invoice` and writes to Dead Letter topic on failure `t_invoice_dlt`