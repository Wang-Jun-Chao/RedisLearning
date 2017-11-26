# MGET key [key ...]

    起始版本：1.0.0
    时间复杂度：O(N) where N is the number of keys to retrieve.

返回所有指定的key的value。对于每个不对应string或者不存在的key，都返回特殊值nil。正因为此，这个操作从来不会失败。

**返回值**

    array-reply: 指定的key对应的values的list

**例子**

```
redis> SET key1 "Hello"
OK
redis> SET key2 "World"
OK
redis> MGET key1 key2 nonexisting
1) "Hello"
2) "World"
3) (nil)
redis> 
```
