# Redis 键(key) 命令

| 命令 | 描述 |
|-----|-----|
|Redis DEL 命令|该命令用于在 key 存在是删除 key。|
|Redis Dump 命令|序列化给定 key ，并返回被序列化的值。|
|Redis EXISTS 命令|检查给定 key 是否存在。|
|Redis Expire 命令|seconds 为给定 key 设置过期时间。|
|Redis Expireat 命令|EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。|
|Redis PEXPIREAT 命令|设置 key 的过期时间亿以毫秒计。|
|Redis PEXPIREAT 命令|设置 key 过期时间的时间戳(unix timestamp) 以毫秒计|
|Redis Keys 命令|查找所有符合给定模式( pattern)的 key 。|
|Redis Move 命令|将当前数据库的 key 移动到给定的数据库 db 当中。|
|Redis PERSIST 命令|移除 key 的过期时间，key 将持久保持。|
|Redis Pttl 命令|以毫秒为单位返回 key 的剩余的过期时间。|
|Redis TTL 命令|以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。|
|Redis RANDOMKEY 命令|从当前数据库中随机返回一个 key 。|
|Redis Rename 命令|修改 key 的名称|
|Redis Renamenx 命令|仅当 newkey 不存在时，将 key 改名为 newkey 。|
|Redis Type 命令|返回 key 所储存的值的类型。|

# Redis 字符串(String) 命令

| 命令 | 描述 |
|-----|-----|
|Redis SET 命令|设置指定 key 的值|
|Redis Get 命令|获取指定 key 的值。|
|Redis Getrange 命令|返回 key 中字符串值的子字符|
|Redis Getset 命令|将给定 key 的值设为 value ，并返回 key 的旧值(old value)。|
|Redis Getbit 命令|对 key 所储存的字符串值，获取指定偏移量上的位(bit)。|
|Redis Mget 命令|获取所有(一个或多个)给定 key 的值。|
|Redis Setbit 命令|对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。|
|Redis Setex 命令|将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。|
|Redis Setnx 命令|只有在 key 不存在时设置 key 的值。|
|Redis Setrange 命令|用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。|
|Redis Strlen 命令|返回 key 所储存的字符串值的长度。|
|Redis Mset 命令|同时设置一个或多个 key-value 对。|
|Redis Msetnx 命令|同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。|
|Redis Psetex 命令|这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位。|
|Redis Incr 命令|将 key 中储存的数字值增一。|
|Redis Incrby 命令|将 key 所储存的值加上给定的增量值（increment） 。|
|Redis Incrbyfloat 命令|将 key 所储存的值加上给定的浮点增量值（increment） 。|
|Redis Decr 命令|将 key 中储存的数字值减一。|
|Redis Decrby 命令|key 所储存的值减去给定的减量值（decrement） 。|
|Redis Append 命令|如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。|

# Redis 哈希(Hash) 命令

| 命令 | 描述 |
|-----|-----|
|Redis Hdel 命令|删除一个或多个哈希表字段|
|Redis Hexists 命令|查看哈希表 key 中，指定的字段是否存在。|
|Redis Hget 命令|获取存储在哈希表中指定字段的值|
|Redis Hgetall 命令|获取在哈希表中指定 key 的所有字段和值|
|Redis Hincrby 命令|为哈希表 key 中的指定字段的整数值加上增量 increment 。|
|Redis Hincrbyfloat 命令|为哈希表 key 中的指定字段的浮点数值加上增量 increment 。|
|Redis Hkeys 命令|获取所有哈希表中的字段|
|Redis Hlen 命令|获取哈希表中字段的数量|
|Redis Hmget 命令|获取所有给定字段的值|
|Redis Hmset 命令|同时将多个 field-value (域-值)对设置到哈希表 key 中。|
|Redis Hset 命令|将哈希表 key 中的字段 field 的值设为 value 。|
|Redis Hsetnx 命令|只有在字段 field 不存在时，设置哈希表字段的值。|
|Redis Hvals 命令|获取哈希表中所有值|

# Redis 列表(List) 命令

| 命令 | 描述 |
|-----|-----|
|Redis Blpop 命令|移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。|
|Redis Brpop 命令|移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。|
|Redis Brpoplpush 命令|从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。|
|Redis Lindex 命令|通过索引获取列表中的元素|
|Redis Linsert 命令|在列表的元素前或者后插入元素|
|Redis Llen 命令|获取列表长度|
|Redis Lpop 命令|移出并获取列表的第一个元素|
|Redis Lpush 命令|将一个或多个值插入到列表头部|
|Redis Lpushx 命令|将一个或多个值插入到已存在的列表头部|
|Redis Lrange 命令|获取列表指定范围内的元素|
|Redis Lrem 命令|移除列表元素|
|Redis Lset 命令|通过索引设置列表元素的值|
|Redis Ltrim 命令|对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。|
|Redis Rpop 命令|移除并获取列表最后一个元素|
|Redis Rpoplpush 命令|移除列表的最后一个元素，并将该元素添加到另一个列表并返回|
|Redis Rpush 命令|在列表中添加一个或多个值|
|Redis Rpushx 命令|为已存在的列表添加值|

# Redis 集合(Set) 命令

| 命令 | 描述 |
|-----|-----|
|Redis Sadd 命令|向集合添加一个或多个成员|
|Redis Scard 命令|获取集合的成员数|
|Redis Sdiff 命令|返回给定所有集合的差集|
|Redis Sdiffstore 命令|返回给定所有集合的差集并存储在 destination 中|
|Redis Sinter 命令|返回给定所有集合的交集|
|Redis Sinterstore 命令|返回给定所有集合的交集并存储在 destination 中|
|Redis Sismember 命令|判断 member 元素是否是集合 key 的成员|
|Redis Smembers 命令|返回集合中的所有成员|
|Redis Smove 命令|将 member 元素从 source 集合移动到 destination 集合|
|Redis Spop 命令|移除并返回集合中的一个随机元素|
|Redis Srandmember 命令|返回集合中一个或多个随机数|
|Redis Srem 命令|移除集合中一个或多个成员|
|Redis Sunion 命令|返回所有给定集合的并集|
|Redis Sunionstore 命令|所有给定集合的并集存储在 destination 集合中|
|Redis Sscan 命令|迭代集合中的元素|

# Redis 有序集合(sorted set) 命令

| 命令 | 描述 |
|-----|-----|
|Redis Zadd 命令|向有序集合添加一个或多个成员，或者更新已存在成员的分数|
|Redis Zcard 命令|获取有序集合的成员数|
|Redis Zcount 命令|计算在有序集合中指定区间分数的成员数|
|Redis Zincrby 命令|有序集合中对指定成员的分数加上增量 increment|
|Redis Zinterstore 命令|计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中|
|Redis Zlexcount 命令|在有序集合中计算指定字典区间内成员数量|
|Redis Zrange 命令|通过索引区间返回有序集合成指定区间内的成员|
|Redis Zrangebylex 命令|通过字典区间返回有序集合的成员|
|Redis Zrangebyscore 命令|通过分数返回有序集合指定区间内的成员|
|Redis Zrank 命令|返回有序集合中指定成员的索引|
|Redis Zrem 命令|移除有序集合中的一个或多个成员|
|Redis Zremrangebylex 命令|移除有序集合中给定的字典区间的所有成员|
|Redis Zremrangebyrank 命令|移除有序集合中给定的排名区间的所有成员|
|Redis Zremrangebyscore 命令|移除有序集合中给定的分数区间的所有成员|
|Redis Zrevrange 命令|返回有序集中指定区间内的成员，通过索引，分数从高到底|
|Redis Zrevrangebyscore 命令|返回有序集中指定分数区间内的成员，分数从高到低排序|
|Redis Zrevrank 命令|返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序|
|Redis Zscore 命令|返回有序集中，成员的分数值|
|Redis Zunionstore 命令|计算给定的一个或多个有序集的并集，并存储在新的 key 中|
|Redis Zscan 命令|迭代有序集合中的元素（包括元素成员和元素分值）|

# Redis HyperLogLog 命令

| 命令 | 描述 |
|-----|-----|
|Redis Pfadd 命令|添加指定元素到 HyperLogLog 中。|
|Redis Pfcount 命令|返回给定 HyperLogLog 的基数估算值。|
|Redis Pgmerge 命令|将多个 HyperLogLog 合并为一个 HyperLogLog|

# Redis 发布订阅 命令

| 命令 | 描述 |
|-----|-----|
|Redis Psubscribe 命令|订阅一个或多个符合给定模式的频道。|
|Redis Pubsub 命令|查看订阅与发布系统状态。|
|Redis Publish 命令|将信息发送到指定的频道。|
|Redis Punsubscribe 命令|退订所有给定模式的频道。|
|Redis Subscribe 命令|订阅给定的一个或多个频道的信息。|
|Redis Unsubscribe 命令|指退订给定的频道。|

# Redis 事务 命令

| 命令 | 描述 |
|-----|-----|
|Redis Discard 命令|取消事务，放弃执行事务块内的所有命令。|
|Redis Exec 命令|执行所有事务块内的命令。|
|Redis Multi 命令|标记一个事务块的开始。|
|Redis Unwatch 命令|取消 WATCH 命令对所有 key 的监视。|
|Redis Watch 命令|监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。|

# Redis 脚本 命令

| 命令 | 描述 |
|-----|-----|
|Redis Eval 命令|执行 Lua 脚本。|
|Redis Evalsha 命令|执行 Lua 脚本。|
|Redis Script Exists 命令|查看指定的脚本是否已经被保存在缓存当中。|
|Redis Script Flush 命令|从脚本缓存中移除所有脚本。|
|Redis Script kill 命令|杀死当前正在运行的 Lua 脚本。|
|Redis Script Load 命令|将脚本 script 添加到脚本缓存中，但并不立即执行这个脚本。|

# Redis 连接 命令

| 命令 | 描述 |
|-----|-----|
|Redis Auth 命令|验证密码是否正确|
|Redis Echo 命令|打印字符串|
|Redis Ping 命令|查看服务是否运行|
|Redis Quit 命令|关闭当前连接|
|Redis Select 命令|切换到指定的数据库|

# Redis 服务器 命令

| 命令 | 描述 |
|-----|-----|
|Redis Bgrewriteaof 命令|异步执行一个 AOF（AppendOnly File） 文件重写操作|
|Redis Bgsave 命令|在后台异步保存当前数据库的数据到磁盘|
|Redis Client Kill 命令|关闭客户端连接|
|Redis Client List 命令|获取连接到服务器的客户端连接列表|
|Redis Client Getname 命令|获取连接的名称|
|Redis Client Pause 命令|在指定时间内终止运行来自客户端的命令|
|Redis Client Setname 命令|设置当前连接的名称|
|Redis Cluster Slots 命令|获取集群节点的映射数组|
|Redis Command 命令|获取 Redis 命令详情数组|
|Redis Command Count 命令获取 Redis 命令总数|
|Redis Command Getkeys 命令|获取给定命令的所有键|
|Redis Time 命令|返回当前服务器时间|
|Redis Command Info 命令|获取指定 Redis 命令描述的数组|
|Redis Config Get 命令|获取指定配置参数的值|
|Redis Config rewrite 命令|对启动 Redis 服务器时所指定的 Redis.conf 配置文件进行改写|
|Redis Config Set 命令|修改 Redis 配置参数，无需重启|
|Redis Config Resetstat 命令|重置 INFO 命令中的某些统计数据|
|Redis Dbsize 命令|返回当前数据库的 key 的数量|
|Redis Debug Object 命令|获取 key 的调试信息|
|Redis Debug Segfault 命令|让 Redis 服务崩溃|
|Redis Flushall 命令|删除所有数据库的所有key|
|Redis Flushdb 命令|删除当前数据库的所有key|
|Redis Info 命令|获取 Redis 服务器的各种信息和统计数值|
|Redis Monitor 命令|实时打印出 Redis 服务器接收到的命令，调试用|
|Redis Role 命令|返回主从实例所属的角色|
|Redis Save 命令|异步保存数据到硬盘|
|Redis Shutdown 命令|异步保存数据到硬盘，并关闭服务器|
|Redis Slaveof 命令|将当前服务器转变为指定服务器的从属服务器(slave server)|
|Redis Showlog 命令|管理 Redis 的慢日志|
|Redis Sync 命令|用于复制功能(replication)的内部命令|