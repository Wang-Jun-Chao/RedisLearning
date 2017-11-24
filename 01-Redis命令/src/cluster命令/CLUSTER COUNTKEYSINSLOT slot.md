##CLUSTER COUNT-FAILURE-REPORTS node-id

----------
    起始版本：3.0.0
    时间复杂度：O(1)

返回连接节点负责的指定hash slot的key的数量。该命令只查询连接节点的数据集，所以如果连接节点指派到该hash slot会返回0。

```
> CLUSTER COUNTKEYSINSLOT 7000
(integer) 50341
```

**返回值**

    Integer reply: 返回连接节点负责的指定hash slot的key的数量, 如果hash slot不合法则返回错误