#<font size="6" face="黑体">**【02-Redis集群命令】**</font>#

----------
#<font size="6" face="黑体">**[【博文总目录>>>】](http://blog.csdn.net/derrantcm/article/details/73456550)[【工程下载>>>】](https://github.com/Wang-Jun-Chao/RedisLearning)**</font>#

----------
##<font size="5" face="黑体">**【02-Redis集群命令】**</font>##

----------
<font size="3" face="Consolas">
　　起始版本：3.0.0
　　时间复杂度：O(N) N是参数的哈希插槽总数
</font>

<font size="3" face="Consolas">
　　Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件. 它支持多种类型的数据结构，如 字符串（strings）， 散列（hashes）， 列表（lists）， 集合（sets）， 有序集合（sorted sets） 与范围查询， bitmaps， hyperloglogs 和 地理空间（geospatial） 索引半径查询. Redis 内置了 复制（replication）， LUA脚本（Lua scripting）， LRU驱动事件（LRU eviction）， 事务（transactions） 和不同级别的 磁盘持久化（persistence）， 并通过 Redis哨兵（Sentinel） 和自动 分区（Cluster）提供高可用性（high availability）.
　　为了实现其卓越的性能， Redis 采用运行在 内存中的数据集工作方式. 根据您的使用情况， 您可以每隔一定时间将 数据集导出到磁盘 ， 或者 追加到命令日志中. 您也可以关闭持久化功能，将Redis作为一个高效的网络的缓存数据功能使用.
　　Redis 同样支持 主从复制（能自动重连和网络断开时自动重新同步），并且第一次同步是快速的非阻塞式的同步.
　　其他功能包括:</font>

- <font size="3" face="Consolas">事务（Transactions）</font>
- <font size="3" face="Consolas">订阅分发（Pub/Sub）</font>
- <font size="3" face="Consolas">LUA脚本（Lua scripting）</font>
- <font size="3" face="Consolas">过期自动删除key</font>
- <font size="3" face="Consolas">内存回收</font>
- <font size="3" face="Consolas">自动故障转移</font>

<font size="3" face="Consolas">　　您可以使用 大多数的编程语言 来使用Redis.
　　Redis 使用 ANSI C 编写并且能在绝大Linux系统上运行，基于BSD协议，对OS X没有外部依赖. 我们支持Linux 和 OS X两种系统的开发和测试，我们推荐使用Linux部署. Redis 可以像SmartOS一样运行在Solaris系统中， 但是我们会最大力度的支持它. 官方不支持Windos版本的Redis,但微软开发和维护着支持win-64 的Redis版本.
　　Redis命令十分丰富，包括的命令组有Cluster、Connection、Geo、Hashes、HyperLogLog、Keys、Lists、Pub/Sub、Scripting、Server、Sets、Sorted Sets、Strings、Transactions一共14个redis命令组两百多个redis命令，下面我们一个一个来介绍如何使用Redis命令。</font>
