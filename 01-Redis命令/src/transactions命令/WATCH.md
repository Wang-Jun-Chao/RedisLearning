# WATCH key [key ...]

    起始版本：2.2.0
    时间复杂度：O(1) for every key.

标记所有指定的key 被监视起来，在事务中有条件的执行（乐观锁）。

**返回值**

    simple-string-reply: 总是 OK。