
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * ネットワークにアクセスし処理を実行するクラス
 */
public class Curl {
    public requestMethod methodType = requestMethod.GET;    //リクエストする際のメソッド
    public boolean isOutPutFile = false;    //ファイルに結果を出力するかどうか（trueが出力する）
    public boolean isShowVersion = false;  //versionを表示するかどうか（trueが表示する）
    public boolean isOutPutPost = false;   //データを送るかどうか（trueが送るする）

    public String postText = "none";        //postで送るデータ
    public String outPutFileName = "none";  //fileにアウトプットする際のファイル名

    public void CurlHttp(String requestUrl){

        Curl _curl = new Curl();

        HttpURLConnection  urlConnection = null;
        InputStream inputStream;                      //結果を格納する
        BufferedReader bufferedReader = null;         //結果の読み込みを格納する

        List<String> text = new ArrayList<>();

        try {
            URL _url = new URL(requestUrl);                             //接続するURLを指定する
            urlConnection = (HttpURLConnection) _url.openConnection();  //コネクションを取得する
            urlConnection.setRequestMethod(methodType.toString());      //GETメソッドをセット

            if(isOutPutPost){//データをポストする処理
                urlConnection.setDoOutput(true);
                OutputStream  out = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
                writer.write(postText);         //書き込み
                writer.flush();
                writer.close();                 //書き込み完了

                out.close();
            }

            urlConnection.connect();                                  //コネクト
            int status = urlConnection.getResponseCode();             //応答されたコードを取得

            if(isShowVersion){//versionを表示するための処理
                var headers = urlConnection.getHeaderFields();
                List<String> _keyList = new ArrayList<>(headers.keySet());               //ヘッダーのキーのリスト
                for(int i=0;i < _keyList.size();i++){
                    System.out.print(_keyList.get(i) + ":");                             //ヘッダーのキーを表示
                    text.add(String.valueOf(urlConnection.getHeaderFields().get(_keyList.get(i))));//読み込んだリストに格納する
                }
            }

            if (status == HttpURLConnection.HTTP_OK) {                                    //接続が成功している場合結果を読み込む
                inputStream = urlConnection.getInputStream();                             //ストリームを取得
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  //ストリームから文字列を読み取る
                String line;                                                              //readLine()の時の読み込んだ行を格納する
                while ((line = bufferedReader.readLine()) != null) {
                    text.add(line);                                                       //読み込んだリストに格納する
                }
            }
            else {
                System.out.println("接続に失敗しました");
            }
        } catch (IOException e) {
            e.printStackTrace();                                //エラー箇所の表示
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();                     //リーダーを閉じる
                }
                if (urlConnection != null) {
                    urlConnection.disconnect();                 //接続を切る
                }
            } catch (IOException e) {
                e.printStackTrace();                            //エラー箇所を表示
            }
        }
        _curl.printText(text);                                  //リストデータを表示する
        if(isOutPutFile){
            PrintTextFile print = new PrintTextFile();
            print.PrintFile(text,outPutFileName);
        }
    }

    /**
     * テキストを表示する処理
     */
    public void printText(List<String> textList){
        for (int i = 0;i < textList.size();i++){
            System.out.println(textList.get(i));
        }
    }

}
