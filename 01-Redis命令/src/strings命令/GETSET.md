# GETSET key value

    起始版本：1.0.0
    时间复杂度：O(1)

自动将key对应到value并且返回原来key对应的value。如果key存在但是对应的value不是字符串，就返回错误。

**设计模式**

GETSET可以和INCR一起使用实现支持重置的计数功能。举个例子：每当有事件发生的时候，一段程序都会调用INCR给key mycounter加1，但是有时我们需要获取计数器的值，并且自动将其重置为0。这可以通过GETSET mycounter “0”来实现：

```
INCR mycounter
GETSET mycounter "0"
GET mycounter
```

**返回值**

    bulk-string-reply: 返回之前的旧值，如果之前Key不存在将返回nil。

**例子**

```
redis> INCR mycounter
(integer) 1
redis> GETSET mycounter "0"
"1"
redis> GET mycounter
"0"
redis> 
```
