# DISCARD 

    起始版本：2.0.0

刷新一个事务中所有在排队等待的指令，并且将连接状态恢复到正常。

如果已使用WATCH，DISCARD将释放所有被WATCH的key。

**返回值**

    status-reply：所有返回都是 OK