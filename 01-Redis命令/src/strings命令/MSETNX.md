# MSETNX key value [key value ...]

    起始版本：1.0.1
    时间复杂度：O(N) where N is the number of keys to set.

对应给定的keys到他们相应的values上。只要有一个key已经存在，MSETNX一个操作都不会执行。 由于这种特性，MSETNX可以实现要么所有的操作都成功，要么一个都不执行，这样可以用来设置不同的key，来表示一个唯一的对象的不同字段。

MSETNX是原子的，所以所有给定的keys是一次性set的。客户端不可能看到这种一部分keys被更新而另外的没有改变的情况。

**返回值**

    integer-reply，只有以下两种值：
    1 如果所有的key被set
    0 如果没有key被set(至少其中有一个key是存在的)

**例子**

```
redis> MSETNX key1 "Hello" key2 "there"
(integer) 1
redis> MSETNX key2 "there" key3 "world"
(integer) 0
redis> MGET key1 key2 key3
1) "Hello"
2) "there"
3) (nil)
redis> 
```

