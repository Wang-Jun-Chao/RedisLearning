# BITPOS key bit [start] [end]

    起始版本：2.8.7
    时间复杂度：O(N)

返回字符串里面第一个被设置为1或者0的bit位。

返回一个位置，把字符串当做一个从左到右的字节数组，第一个符合条件的在位置0，其次在位置8，等等。

GETBIT 和 SETBIT 相似的也是操作字节位的命令。

默认情况下整个字符串都会被检索一次，只有在指定start和end参数(指定start和end位是可行的)，该范围被解释为一个字节的范围，而不是一系列的位。所以start=0 并且 end=2是指前三个字节范围内查找。

注意，返回的位的位置始终是从0开始的，即使使用了start来指定了一个开始字节也是这样。

和GETRANGE命令一样，start和end也可以包含负值，负值将从字符串的末尾开始计算，-1是字符串的最后一个字节，-2是倒数第二个，等等。

不存在的key将会被当做空字符串来处理。

**返回值**
    Integer reply
    命令返回字符串里面第一个被设置为1或者0的bit位。

如果我们在空字符串或者0字节的字符串里面查找bit为1的内容，那么结果将返回-1。

如果我们在字符串里面查找bit为0而且字符串只包含1的值时，将返回字符串最右边的第一个空位。如果有一个字符串是三个字节的值为0xff的字符串，那么命令BITPOS key 0将会返回24，因为0-23位都是1。

基本上，我们可以把字符串看成右边有无数个0。

然而，如果你用指定start和end范围进行查找指定值时，如果该范围内没有对应值，结果将返回-1。

**例子**

```
redis> SET mykey "\xff\xf0\x00"
OK
redis> BITPOS mykey 0 # 查找字符串里面bit值为0的位置
(integer) 12
redis> SET mykey "\x00\xff\xf0"
OK
redis> BITPOS mykey 1 0 # 查找字符串里面bit值为1从第0个字节开始的位置
(integer) 8
redis> BITPOS mykey 1 2 # 查找字符串里面bit值为1从第2个字节(12)开始的位置
(integer) 16
redis> set mykey "\x00\x00\x00"
OK
redis> BITPOS mykey 1 # 查找字符串里面bit值为1的位置
(integer) -1
redis>
```
