

import com.dinpay.dpp.rcp.utils.EncryptUtil;

public class TestAES加密 {
    public static void main(String[] args) {
        try {
            String token16 = "HzrBmx0SZvBt68Lr";
            String token108 = "HzrBmx0SZvBt68LrwqjUxx43wUxYh+LSswxrfMQgCgwJUM0CmBYFpmE3eeaIR12hpaVb9N4QB3e08csJ3PveEmNbGg9gAaBDGo0VlKmQ97k=";
            System.out.println(token16.length());
            System.out.println(token108.length());
            String result = EncryptUtil.encryptAES("abc", token108);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
