
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Curl {

    //curl https://example.com 相当のことができる機能
    public void CurlHttp(String requestUrl){
        System.out.println("curl https://example.com 相当のことができる機能");
        HttpURLConnection  urlConn = null;
        InputStream in;                             //結果を格納する
        BufferedReader reader = null;               //結果の読み込みを格納する

        try {
            URL url = new URL(requestUrl);                          //接続するURLを指定する
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
    public void CurlOFile(String fileName,String requestUrl){
        System.out.println("curl -o file https://example.com 相当のことができる機能");
        HttpURLConnection  urlConn = null;
        InputStream in;                      //結果を格納する
        BufferedReader reader = null;               //結果の読み込みを格納する
        List<String> text = new ArrayList<>();

        try {
            URL url = new URL(requestUrl);                          //接続するURLを指定する
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
                    text.add(line);
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
        PrintTextFile print = new PrintTextFile();
        print.PrintFile(text,fileName);
    }

    //curl -v  https://example.com 相当のことができる機能
    public  void CurlVHttp(String requestUrl){
        System.out.println("curl -v  https://example.com 相当のことができる機能");
        HttpURLConnection  urlConn = null;
        BufferedReader reader = null;               //結果の読み込みを格納する

        try {
            URL url = new URL(requestUrl);                          //接続するURLを指定する
            urlConn = (HttpURLConnection) url.openConnection(); //コネクションを取得する
            urlConn.setRequestMethod("GET");                    //GETメソッドをセット
            urlConn.connect();                                  //コネクト
            int status = urlConn.getResponseCode();             //応答されたコードを取得
            System.out.println("HTTPステータス:" + status);       //応答された状態を表示、200なら成功

            var headers = urlConn.getHeaderFields();
            List<String> keyKist = new ArrayList<>(headers.keySet());               //ヘッダーのキーのリスト
            for(int i=0;i < keyKist.size();i++){
                System.out.print(keyKist.get(i) + ":");                             //ヘッダーのキーを表示
                System.out.println(urlConn.getHeaderFields().get(keyKist.get(i)));  //ヘッダーの値を表示
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

    //curl -X POST https://example.com 相当のことができる機能
    public  void CurlXPost(String requestUrl){
        System.out.println("url -X POST https://example.com 相当のことができる機能");
        HttpURLConnection  urlConn = null;
        InputStream in;                      //結果を格納する
        BufferedReader reader = null;               //結果の読み込みを格納する

        try {
            URL url = new URL(requestUrl);                          //接続するURLを指定する
            urlConn = (HttpURLConnection) url.openConnection(); //コネクションを取得する
            urlConn.setRequestMethod("POST");                    //GETメソッドをセット
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

    //curl -X POST -d "key=value" https://example.com 相当のことができる機能
    public  void  CurlXPostD(String requestUrl,String value){
        System.out.println("curl -X POST -d \"key=value\" https://example.com 相当のことができる機能");

        HttpURLConnection  urlConn = null;
        InputStream in;                      //結果を格納する
        BufferedReader reader = null;               //結果の読み込みを格納する

        try {
            URL url = new URL(requestUrl);                          //接続するURLを指定する
            urlConn = (HttpURLConnection) url.openConnection(); //コネクションを取得する
            urlConn.setDoOutput(true);
            urlConn.setRequestMethod("POST");                    //GETメソッドをセット

            OutputStream  out = urlConn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            writer.write(value);        //書き込み
            writer.flush();
            writer.close();             //書き込み完了

            out.close();

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

}
