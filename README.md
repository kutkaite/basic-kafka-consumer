#### Consumers explained:
1. `KafkaConsumer.java` - simply consumes a `String` from topic `t_test`
2. `IncrementalConsumer.java` - simply consumes a `String` from topic `t_incremental`
3. `KafkaKeyConsumer.java` - consumes a `ConsumerRecord<String, String>` from topic `t_multiple_partitions` 
with `concurrency` equal to the amount of partitions
4. `EmployeeJsonConsumer.java` - consumes a `JSON` from topic `t_employee` and maps it to an `Employee` object.
5a. `CommodityDashboardConsumer.java` - consumes a `JSON` message from a topic `t_commodity` and groupId = `cg-dashboard`
5b. `CommodityNotificationConsumer.java` - consumes a `JSON` message from a topic `t_commodity` and groupId = `cg-notification`
6. `CarLocationConsumer.java` - has 2 methods one to consume all messages from topic `t_location` 
and another method to consume messages only from group id `cg-filtered-location` 
which is filtered by using `filteredLocationContainerFactory` container factory
7. `FoodOrderConsumer.java` - consumes a message when it satisfies all the conditions, 
if not conditions are met then an exception is thrown which is handled by `FoodOrderErrorHandler`.
8. `SimpleNumberConsumer.java` - consumes a message if a number is not odd. If the number is odd then an exception is thrown
which is handled by `GlobalErrorHandler`.