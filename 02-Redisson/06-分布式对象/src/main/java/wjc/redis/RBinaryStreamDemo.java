package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RBinaryStream;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Author: 王俊超
 * Date: 2017-11-28 20:56
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RBinaryStreamDemo {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RBinaryStream stream = redisson.getBinaryStream("anyStream");
        byte[] content = "Hello World".getBytes();
        stream.set(content);

        getBinaryStream(stream);
        writeBinaryStream(stream);
        getBinaryStream(stream);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }

    private static void writeBinaryStream(RBinaryStream stream) throws IOException {
        OutputStream os = stream.getOutputStream();
        byte[] contentToWrite = "--->Hello Java".getBytes();
        os.write(contentToWrite);
        os.flush();
        os.close();
    }

    private static void getBinaryStream(RBinaryStream stream) throws IOException {



        InputStream is = stream.getInputStream();

        byte[] readBuffer = new byte[512];
        int len = is.read(readBuffer);
        System.out.println(new String(readBuffer, 0, len));
        is.close();
    }
}
