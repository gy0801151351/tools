import java.io.IOException;
import java.io.StringReader;

import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.ParserDelegator;

public class 测试Html转Text {

	public static void main(String[] args) throws IOException {
		String html = "<li>权利人有房产，产权状态为：<span style=\"font-size:10.5pt;color:red\">抵押</span><br>&nbsp;&nbsp;抵押权人：中国建设银行股份有限公司深圳市分行<br>&nbsp;&nbsp;抵押日期：2015-11-20</li><li>房产详细资料：类别：住宅&nbsp;&nbsp;面积：166.50&nbsp;&nbsp;登记价：RMB1573317&nbsp;&nbsp;竣工时间：2005/10/28</li>";
		ParserDelegator delegator = new ParserDelegator();
		StringReader sr = new StringReader(html);
		StringBuffer text = new StringBuffer();
		ParserCallback callback = new ParserCallback() {
			@Override
			public void handleText(char[] data, int pos) {
				text.append(data).append("\r\n");
			}
			
		};
		delegator.parse(sr, callback, Boolean.TRUE);
		System.out.println(text.toString().trim());
	}
}
