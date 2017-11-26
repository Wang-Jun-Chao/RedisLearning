# DECRBY key decrement

    起始版本：1.0.0
    时间复杂度：O(1)

将key对应的数字减decrement。如果key不存在，操作之前，key就会被置为0。如果key的value类型错误或者是个不能表示成数字的字符串，就返回错误。这个操作最多支持64位有符号的正型数字。

查看命令INCR了解关于增减操作的额外信息。似。

**返回值**

    返回一个数字：减少之后的value值。

**例子**

```
redis> SET mykey "10"
OK
redis> DECRBY mykey 5
(integer) 5
redis>
```
 