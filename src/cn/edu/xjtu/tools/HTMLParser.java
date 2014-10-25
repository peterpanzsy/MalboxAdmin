package cn.edu.xjtu.tools;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.xjtu.constant.Regulation;

public class HTMLParser {
	
	public static String parse(String filename) throws IOException {
		return parse(new File(filename));
	}
	/**
	 * 完成分析报告，所有行为报告的分析
	 * @param filename
	 * @return 报告的内容
	 * @throws IOException
	 */
	public static String parse(File filename) throws IOException {
		StringBuffer result = new StringBuffer();
		Document doc = Jsoup.parse(filename, "UTF-8");

		// 分析文件的标题内容，文件名称、文件大小、MD5、SHA1
		Elements titledetDiv = doc.select("div.titledet");
		for (Element e : titledetDiv) {
			result.append(e.text());
			result.append("\n");
		}
		result.append("\n");
		// 开始分析文件的主体内容
		// 得到class为dt的div标签
		Elements dtDivs = doc.select("div.dt");

		for (Element e : dtDivs) {
			result.append(parse(e));
		}
		return result.toString();
	}
	
	public static String parse(String filename,String regulation) throws IOException {
		return parse(new File(filename), regulation);
	}
	/**
	 * 分析具体的行为生成报告
	 * @param filename 文件的地址信息，其实应该写成filepath的，原谅我吧
	 * @param regulation 具体的规则
	 * @return
	 * @throws IOException
	 */
	public static String parse(File filename,String regulation) throws IOException {
		String report= null;
		Document doc = Jsoup.parse(filename, "UTF-8");
		Elements dtDivs = doc.select("div.dt");
		Element element = null;
		switch (Regulation.getRegulation(regulation)) {
		case NET:
			element = dtDivs.get(4); 
			report = parse(element);
			break;
		case REGISTER:
			element = dtDivs.get(3); 
			report = parse(element);
			break;
		case FILE:
			element = dtDivs.get(2); 
			report = parse(element);
			break;
		case PROCESS:
			element = dtDivs.get(1); 
			report = parse(element);
			break;
		case ALL:
			report = parse(filename);
			break;
		default:
			break;
		}
		return report;
	}
	
	/**
	 * 根据具体的规则分析节点信息
	 * @param e
	 * @return
	 */
	private static String parse(Element e){
		StringBuffer result = new StringBuffer();
		result.append(e.text());
		result.append("\n");
		// 得到ul标签
		Element ul = e.nextElementSibling().getElementsByTag("ul").get(0);
		Elements lis = ul.getElementsByTag("li");
		for (Element li : lis) {
			Elements divs = li.getElementsByTag("div");
			if (!divs.isEmpty()) {
				result.append("\t"+divs.get(0).text());
				result.append("\t"+divs.get(1).child(0).text());
				result.append("\n");
				for (Element div : divs) {
					Elements ps = div.getElementsByTag("p");
					if (!ps.isEmpty()) {
						for (Element p : ps) {
							result.append("\t\t" + p.text());
							result.append("\n");
						}
					}
				}
			} else {
				result.append("\t" + li.text());
				result.append("\n");
			}
		}
		return result.toString();
	}
}

