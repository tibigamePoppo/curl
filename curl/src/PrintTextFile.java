import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PrintTextFile {
    public void PrintFile(List<String> Text , String fileName) {
        try {
            System.out.println("fileName = " + fileName);
            FileWriter file = new FileWriter(fileName);// FileWriterクラスのオブジェクトを生成する
            PrintWriter pw = new PrintWriter(new BufferedWriter(file)); // PrintWriterクラスのオブジェクトを生成する

            for (int i = 0;i<Text.size();i++){
                pw.println(Text.get(i));//ファイルに書き込む
            }

            pw.close();//ファイルを閉じる
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
