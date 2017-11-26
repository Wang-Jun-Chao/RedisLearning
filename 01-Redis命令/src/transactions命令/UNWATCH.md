# UNWATCH 

    起始版本：2.2.0
    时间复杂度：O(1)

刷新一个事务中已被监视的所有key。

如果执行EXEC 或者DISCARD， 则不需要手动执行UNWATCH 。

**返回值**

    simple-string-reply: 总是 OK。