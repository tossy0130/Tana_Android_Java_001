import java.util.*;

public class A02 {

    public static void main(String[] args) throws Exception {
        // Your code here!

        String str = "11:預かり売上";
        int index = str.indexOf(":");
        String result = str.substring(index + 1);

        System.out.println(result);

        String tmp = Str_Cut("11:預かり売上", ":");
        System.out.println(tmp);

    }

    /*
     * Str_Cut(String str, String target)
     * 
     * str => 切り出したい文字列
     * 
     * target => 指定した文字以降を切り出し
     */
    private static String Str_Cut(String str, String target) {

        int index = str.indexOf(target);
        String result = str.substring(index + 1);

        return result;
    }

    /**
     *  コードを取得
     */
    private static String Str_Cut_Code(String str, String target) {
        int index = str.indexOf(target);
        String result = str.substring(0, index);

        return result;
    }
    
    /**
     * ハッシュマップからキーを取得する
     */
    public static <K, V> K getKey(Map<K, V> map, V value) {
        return map.entrySet().stream()
                .filter(entry -> value.equals(entry.getValue()))
                .findFirst().map(Map.Entry::getKey)
                .orElse(null);
    }

}
