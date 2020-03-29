package com.bjfu.ecloud;

import com.bjfu.ecloud.util.HadoopUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() throws IOException {
//		HadoopUtils.create("/home/alyssa/books/test2.txt", 1);
		HadoopUtils.write("/home/alyssa/books/test3.png", new FileInputStream(new File("/home/alyssa/Downloads/unknown.png")));
		HadoopUtils.downloadToLocal("/home/alyssa/books/test3.png","/home/alyssa/Downloads/test001.png");
	}

}
