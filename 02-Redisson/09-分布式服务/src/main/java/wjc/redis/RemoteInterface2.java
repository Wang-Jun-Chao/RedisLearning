package wjc.redis;

/**
 * Author: 王俊超
 * Date: 2017-12-05 07-25
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
// 远程接口
public interface RemoteInterface2 {
    Long someMethod1(Long param1, String param2);

    void someMethod2(MyObject param);

    MyObject someMethod3();
}
