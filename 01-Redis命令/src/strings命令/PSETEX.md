# PSETEX key milliseconds value

    起始版本：2.6.0
    时间复杂度：O(1)

PSETEX和SETEX一样，唯一的区别是到期时间以毫秒为单位,而不是秒。

**例子**

```
redis> PSETEX mykey 1000 "Hello"
OK
redis> PTTL mykey
(integer) 999
redis> GET mykey
"Hello"
redis> 
```
