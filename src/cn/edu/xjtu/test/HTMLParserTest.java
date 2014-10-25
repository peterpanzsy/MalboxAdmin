package cn.edu.xjtu.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import cn.edu.xjtu.tools.HTMLParser;

public class HTMLParserTest {

	@Test
	public void testAll() throws IOException {
		String filename = "d:/ec128a7d2fc1400c4e12506e520482e5.html";
		String str = HTMLParser.parse(filename,"all");
		System.out.println(str);
	}
	@Test
	public void testProcess() throws IOException {
		String filename = "d:/ec128a7d2fc1400c4e12506e520482e5.html";
		String str = HTMLParser.parse(filename,"process");
		System.out.println(str);
	}
	@Test
	public void testRegister() throws IOException {
		String filename = "d:/ec128a7d2fc1400c4e12506e520482e5.html";
		String str = HTMLParser.parse(filename,"register");
		System.out.println(str);
	}
	@Test
	public void testFile() throws IOException {
		String filename = "d:/ec128a7d2fc1400c4e12506e520482e5.html";
		String str = HTMLParser.parse(filename,"file");
		System.out.println(str);
	}
	@Test
	public void testNet() throws IOException {
		String filename = "d:/ec128a7d2fc1400c4e12506e520482e5.html";
		String str = HTMLParser.parse(filename,"net");
		System.out.println(str);
	}
	

}
