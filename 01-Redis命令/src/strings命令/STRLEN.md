# STRLEN key

    起始版本：2.2.0
    时间复杂度：O(1)

返回key的string类型value的长度。如果key对应的非string类型，就返回错误。

**返回值**

    integer-reply：key对应的字符串value的长度，或者0（key不存在）

**例子**

```
redis> SET mykey "Hello world"
OK
redis> STRLEN mykey
(integer) 11
redis> STRLEN nonexisting
(integer) 0
redis> 
```
