### 设计文档

* 主题架构和关键数据结构

~~~
主体架构：
    使用Dropwizard作为mvc框架用FortuneDB模拟数据库操作
设计方案：
    主要的数据结构是一个字典和列表，使用列表作为fortuneId的存储，用Map的key和列表关联，Value作为消息，假设我们的幸运ID是
Key，幸运消息是Value,可以根据Key实现对消息的增删查，由于字典使用的散列表，通过Key的操作使增查的时间复杂度都为O(1),删除的
时间复杂度是O(n)。

重要的限制：由于加了锁FortuneDB的性能比较低，查找的理想时间是O(1)。
    

~~~

* 测试方案

~~~
测试方案：
    1：分别对db，service单独测试
    2：单独测试，分别对三个接口进行单独的边界测试和异常数据测试
    3：复合测试，分别模拟操作流程，测试案例是新添加后删除，先删除后添加，
 在删除的同时进行查询操作，在新增的同时进行删除操作
    4：并发测试启动一百个线程对业务进行测试


~~~

* http://localhost:8082/fortune

~~~

response:

{
    fortuneId:1,
    message:""
}
~~~

* http://localhost:8082/fortunes

~~~
request:

{
    message:""
}

response:

{
    fortuneId:1
}
~~~

* http://localhost:8082/fortunes/{fortuneId}
~~~
request:

{
    fortuneId:1
}

response:

{
    isDelete:true //true 已经删除 false没有删除
}


~~~

# Running The Application

* package
~~~
mvn package
~~~~

* To run the server run
~~~
java -jar target/Fortune1.0.0.jar server fortune.yml
~~~~