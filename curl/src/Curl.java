
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Curl {

    //curl https://example.com 相当のことができる機能
    public void CurlHttp(){
        System.out.println("curl https://example.com 相当のことができる機能");
        String strUrl = "https://example.com";
//		String strUrl = "http://httpbin.org/post";
        HttpURLConnection  urlConn = null;
        InputStream in = null;                      //結果を格納する
        BufferedReader reader = null;               //結果の読み込みを格納する

        try {
            URL url = new URL(strUrl);                          //接続するURLを指定する
            urlConn = (HttpURLConnection) url.openConnection(); //コネクションを取得する
            urlConn.setRequestMethod("GET");                    //GETメソッドをセット
            urlConn.connect();                                  //コネクト
            int status = urlConn.getResponseCode();             //応答されたコードを取得
            System.out.println("HTTPステータス:" + status);       //応答された状態を表示、200なら成功

            if (status == HttpURLConnection.HTTP_OK) {          //接続が成功している場合結果を読み込む
                in = urlConn.getInputStream();                  //ストリームを取得
                reader = new BufferedReader(new InputStreamReader(in)); //ストリームから文字列を読み取る
                String line;                                    //readLine()の時の読み込んだ行を格納する
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);                   //読み込んだ行を表示する
                }
            }
        } catch (IOException e) {
            e.printStackTrace();                                //エラー箇所の表示
        } finally {
            try {
                if (reader != null) {
                    reader.close();                             //リーダーを閉じる
                }
                if (urlConn != null) {
                    urlConn.disconnect();                       //接続を切る
                }
            } catch (IOException e) {
                e.printStackTrace();                            //エラー箇所を表示
            }
        }
    }

    //curl -o file https://example.com 相当のことができる機能
    public void CurlOFile(){
        System.out.println("curl -o file https://example.com 相当のことができる機能");
    }

    //curl -v  https://example.com 相当のことができる機能
    public  void CurlVHttp(){
        System.out.println("curl -v  https://example.com 相当のことができる機能");
    }

    //curl -X POST https://example.com 相当のことができる機能
    public  void CurlXPost(){
        System.out.println("url -X POST https://example.com 相当のことができる機能");
    }

    //curl -X POST -d "key=value" https://example.com 相当のことができる機能
    public  void  CurlXPostD(){
        System.out.println("curl -X POST -d \"key=value\" https://example.com 相当のことができる機能");
    }

}
