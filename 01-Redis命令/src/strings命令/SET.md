# SET key value [EX seconds] [PX milliseconds] [NX|XX]

    起始版本：1.0.0
    时间复杂度：O(1)

Set key to hold the string value. If key already holds a value, it is overwritten, regardless of its type. Any previous time to live associated with the key is discarded on successful SET operation.

将键key设定为指定的“字符串”值。

如果	key	已经保存了一个值，那么这个操作会直接覆盖原来的值，并且忽略原始类型。

当set命令执行成功之后，之前设置的过期时间都将失效

**选项**

从2.6.12版本开始，redis为SET命令增加了一系列选项:

- EX seconds – 设置键key的过期时间，单位时秒

- PX milliseconds – 设置键key的过期时间，单位时毫秒

- NX – 只有键key不存在的时候才会设置key的值

- XX – 只有键key存在的时候才会设置key的值

注意: 由于SET命令加上选项已经可以完全取代SETNX, SETEX, PSETEX的功能，所以在将来的版本中，redis可能会不推荐使用并且最终抛弃这几个命令。

**返回值**

    simple-string-reply:如果SET命令正常执行那么回返回OK，否则如果加了NX 或者 XX选项，但是没有设置条件。那么会返回nil。

**例子**

```
redis> SET mykey "Hello"
OK
redis> GET mykey
"Hello"
redis>
```
 
**设计模式**

注意: 下面这种设计模式并不推荐用来实现redis分布式锁。应该参考the Redlock algorithm的实现，因为这个方法只是复杂一点，但是却能保证更好的使用效果。

命令 SET resource-name anystring NX EX max-lock-time 是一种用 Redis 来实现锁机制的简单方法。

如果上述命令返回OK，那么客户端就可以获得锁（如果上述命令返回Nil，那么客户端可以在一段时间之后重新尝试），并且可以通过DEL命令来释放锁。

客户端加锁之后，如果没有主动释放，会在过期时间之后自动释放。

可以通过如下优化使得上面的锁系统变得更加鲁棒：

不要设置固定的字符串，而是设置为随机的大字符串，可以称为token。

通过脚步删除指定锁的key，而不是DEL命令。

上述优化方法会避免下述场景：a客户端获得的锁（键key）已经由于过期时间到了被redis服务器删除，但是这个时候a客户端还去执行DEL命令。而b客户端已经在a设置的过期时间之后重新获取了这个同样key的锁，那么a执行DEL就会释放了b客户端加好的锁。

解锁脚本的一个例子将类似于以下：

```
if redis.call("get",KEYS[1]) == ARGV[1]
then
    return redis.call("del",KEYS[1])
else
    return 0
end
```

这个脚本执行方式如下：

```
EVAL …script… 1 resource-name token-value
```
