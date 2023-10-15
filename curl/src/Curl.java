
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Curl {
    public requestMethod methodType = requestMethod.GET;
    public boolean isOutPutFile = false;    //ファイルに結果を出力するかどうか（trueが出力する）
    public  boolean isShowVersion = false;  //versionを表示するかどうか（trueが表示する）
    public  boolean isOutPutPost = false;   //データを送るかどうか（trueが送るする）

    public String postText = "none";
    public String outPutFileName = "none";
    //curl https://example.com 相当のことができる機能
    public void CurlHttp(String requestUrl){

        Curl curl = new Curl();

        HttpURLConnection  urlConn = null;
        InputStream in;                      //結果を格納する
        BufferedReader reader = null;               //結果の読み込みを格納する

        List<String> text = new ArrayList<>();

        try {
            URL url = new URL(requestUrl);                      //接続するURLを指定する
            urlConn = (HttpURLConnection) url.openConnection(); //コネクションを取得する
            urlConn.setRequestMethod(methodType.toString());    //GETメソッドをセット

            if(isOutPutPost){//データをポストする処理
                urlConn.setDoOutput(true);
                OutputStream  out = urlConn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
                writer.write(postText);        //書き込み
                writer.flush();
                writer.close();             //書き込み完了

                out.close();
            }

            urlConn.connect();                                  //コネクト
            int status = urlConn.getResponseCode();             //応答されたコードを取得

            if(isShowVersion){//versionを表示するための処理
                var headers = urlConn.getHeaderFields();
                List<String> keyKist = new ArrayList<>(headers.keySet());               //ヘッダーのキーのリスト
                for(int i=0;i < keyKist.size();i++){
                    System.out.print(keyKist.get(i) + ":");                             //ヘッダーのキーを表示
                    System.out.println(urlConn.getHeaderFields().get(keyKist.get(i)));  //ヘッダーの値を表示
                }
            }


            if (status == HttpURLConnection.HTTP_OK) {          //接続が成功している場合結果を読み込む
                in = urlConn.getInputStream();                  //ストリームを取得
                reader = new BufferedReader(new InputStreamReader(in)); //ストリームから文字列を読み取る
                String line;                                    //readLine()の時の読み込んだ行を格納する
                while ((line = reader.readLine()) != null) {
                    text.add(line);                             //読み込んだリストに格納する
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

        curl.printText(text);                                   //リストデータを表示する
        if(isOutPutFile){
            System.out.println("ファイルに出力");
            PrintTextFile print = new PrintTextFile();
            print.PrintFile(text,outPutFileName);
        }
    }


    public  void printText(List<String> textList){
        for (int i = 0;i < textList.size();i++){
            System.out.println(textList.get(i));
        }
    }

}
