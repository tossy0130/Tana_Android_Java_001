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

     //------------------------------------ Send_db  個数　取得して　ID　に使用する ------------
    private void ReadMax_num() {

        /* ヘルパークラス　の準備 */
        TestOpenHelper helper = new TestOpenHelper(getApplicationContext());
        SQLiteDatabase Read_num_db = helper.getReadableDatabase();
        /* ヘルパークラス　の準備 END */

        int [] arr_item = new int[1];

        try {
            Cursor cursor = Read_num_db.rawQuery("select count(" + TestOpenHelper.SEND_DB_C_01 + ")" + "from Send_db", null);

            if(cursor != null) {
                do {
                    // Send_db の id 用　カラム　取得

                        // count(数　取得)
                        int idx = cursor.getColumnIndex("send_db_01");
                        arr_item[0] = cursor.getInt(idx);

                        max_data_view.setText(String.valueOf(arr_item[0]));

                } while (cursor.moveToNext()); // ----------- while

            } else {
                int i = 1;
                max_data_view.setText(String.valueOf(i));
            }

        } finally {
            // データベース　オブジェクト
            Read_num_db.close();
        }

    }
    //------------------------------------ Send_db  個数　取得して　ID　に使用する END ------------>

}
