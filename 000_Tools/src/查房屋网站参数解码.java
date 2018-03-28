import java.net.URLDecoder;
import java.util.Base64;

public class 查房屋网站参数解码 {

	public static String decodeUnicode(String theString) {
		char aChar;
		char pChar;
		char tChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '%') {
				pChar = aChar;
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					tChar = aChar;
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					if (tChar == aChar) {
						outBuffer.append(pChar);
					}
					outBuffer.append(aChar);
				} 
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	public static String decode(String value) {
		try {
			return URLDecoder.decode(decodeUnicode(URLDecoder.decode(new String(Base64.getDecoder().decode(value)), "UTF-8")), "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		String value = "JTI1NUIlMjUyMiUyNTdCJTI1NUMlMjUyMnBhcmFtZXRlcnMlMjU1QyUyNTIyJTI1M0ElMjU1QiUyNTdCJTI1NUMlMjUyMm5hbWUlMjU1QyUyNTIyJTI1M0ElMjU1QyUyNTIybmFtZSUyNTVDJTI1MjIlMjUyQyUyNTVDJTI1MjJ2YWx1ZSUyNTVDJTI1MjIlMjUzQSUyNTVDJTI1MjIlMjV1NjcyQyUyNXU1MTQzJTI1NUMlMjUyMiUyNTdEJTI1MkMlMjU3QiUyNTVDJTI1MjJuYW1lJTI1NUMlMjUyMiUyNTNBJTI1NUMlMjUyMmFyZWFpZCUyNTVDJTI1MjIlMjUyQyUyNTVDJTI1MjJ2YWx1ZSUyNTVDJTI1MjIlMjUzQSUyNTVDJTI1MjI0NDAzMDQwMDAwMDAwMDAwMDAlMjU1QyUyNTIyJTI1MkMlMjU1QyUyNTIyZmxhZyUyNTVDJTI1MjIlMjUzQTE2JTI1MkMlMjU1QyUyNTIyZ3JvdXAlMjU1QyUyNTIyJTI1M0ElMjU1QyUyNTIyc3lzdGVtLmFyZWFpZC5jbGllbnQlMjU1QyUyNTIyJTI1N0QlMjUyQyUyNTdCJTI1NUMlMjUyMm5hbWUlMjU1QyUyNTIyJTI1M0ElMjU1QyUyNTIyYXJlYWlkJTI1NUMlMjUyMiUyNTJDJTI1NUMlMjUyMnZhbHVlJTI1NUMlMjUyMiUyNTNBJTI1NUMlMjUyMjQ0MDMwNDk5OTk5OTk5OTk5OSUyNTVDJTI1MjIlMjUyQyUyNTVDJTI1MjJmbGFnJTI1NUMlMjUyMiUyNTNBMTclMjUyQyUyNTVDJTI1MjJncm91cCUyNTVDJTI1MjIlMjUzQSUyNTVDJTI1MjJzeXN0ZW0uYXJlYWlkLmNsaWVudCUyNTVDJTI1MjIlMjU3RCUyNTVEJTI1MkMlMjU1QyUyNTIycGFnZU51bSUyNTVDJTI1MjIlMjUzQTElMjUyQyUyNTVDJTI1MjJwYWdlU2l6ZSUyNTVDJTI1MjIlMjUzQTEyJTI1MkMlMjU1QyUyNTIybmFtZSUyNTVDJTI1MjIlMjUzQSUyNTVDJTI1MjJ2aWV3Y29kZWJ1aWxkaW5nd2ViJTI1NUMlMjUyMiUyNTJDJTI1NUMlMjUyMmdldENvZGVWYWx1ZSUyNTVDJTI1MjIlMjUzQXRydWUlMjUyQyUyNTVDJTI1MjJ0b0NvdW50JTI1NUMlMjUyMiUyNTNBZmFsc2UlMjUyQyUyNTVDJTI1MjJpc2dpcyUyNTVDJTI1MjIlMjUzQWZhbHNlJTI1MkMlMjU1QyUyNTIyamF2YUNsYXNzJTI1NUMlMjUyMiUyNTNBJTI1NUMlMjUyMmNvbS5sb25ncmlzZS5MRUFQLkJMTC5DYWNoZS5TZWFyY2hCdWlsZGVyJTI1NUMlMjUyMiUyNTdEJTI1MjIlMjU1RA==";
		System.out.println(decode(value));
	}
}
