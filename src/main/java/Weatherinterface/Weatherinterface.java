package Weatherinterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Weatherinterface {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://t.weather.itboy.net/api/weather/city/101071001");
            InputStreamReader isReade = new InputStreamReader(url.openStream(), "UTF-8");
            BufferedReader br = new BufferedReader(isReade);
            String str;
            while ((str = br.readLine()) != null) {
                String regex = "\\p{Punct}+";
                String digit[] = str.split(regex);
                System.out.println('\n'+"城市:"+digit[22]+digit[18]);
                System.out.println('\n'+"时间:"+digit[51]+"年"+digit[52]+"月"+digit[53]+"日"+digit[55]);
                System.out.println('\n'+"温度:"+digit[48]+"~"+digit[46]);
                System.out.println('\n'+"天气:"+digit[69]+" "+digit[65]+digit[67]);
                System.out.println('\n'+digit[70]);
            }
            br.close();
            isReade.close();
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }
}
