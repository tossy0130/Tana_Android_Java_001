import java.util.*;

public class A01 {

    public static void main(String args[]) {

        /**
         * ログイン ボタンを押した ログイン判定処理
         */

        // ---------- アカウント作成ボタン イベント処理 Start -----------------
        account_put_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // -------- 棚卸し画面へ csv TNMF.csv １つめの番号を渡す

                g_account = user_input.getText().toString();

                // ******************** エラー処理 ************************

                // ------------- ユーザー番号が空だった場合の 処理
                if (g_account.length() == 0) {

                    // ソフトキーボードを隠す
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(v.getWindowToken(), 0);

                    Snackbar.make(v, "入力欄が空白です。「担当コード」を入力してください。", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                /**
                 *** //----------- 担当者コード 入力欄が 空じゃなかった 時の判定
                 */
                if (g_account.length() != 0) {

                    /**
                     * 「確定」 ボタン 処理 ------------------
                     *
                     ** ユーザーが 入力した文字が、 配列 arr_col に 担当者コードが存在していた場合の処理
                     **
                     */
                    if (arr_col.contains(g_account)) {

                        // ソフトキーボードを隠す
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(v.getWindowToken(), 0);

                        // ************ SQL アカウント SELECT Start ************* //

                        TestOpenHelper helper = new TestOpenHelper(getApplicationContext());

                        SQLiteDatabase Ac_db = helper.getReadableDatabase();

                        String[] arr_item = new String[3];

                        try {

                            // -------------- トランザクション 開始 -------------------//
                            Ac_db.beginTransaction();

                            // g_account = エディットテキスト 上段 データ
                            Cursor cursor = Ac_db.rawQuery("SELECT TNMF_c_01, TNMF_c_02, TNMF_c_03 " +
                                    "FROM TNMF_table WHERE TNMF_c_03 = " + g_account + " LIMIT 1", null);

                            if (cursor.moveToNext()) {

                                int idx = cursor.getColumnIndex("TNMF_c_01"); // id
                                arr_item[0] = cursor.getString(idx);

                                idx = cursor.getColumnIndex("TNMF_c_02"); // アカウント名
                                arr_item[1] = cursor.getString(idx);

                                // 部署 連携用 カラム 04 取得
                                idx = cursor.getColumnIndex("TNMF_c_03"); // ログイン ID
                                arr_item[2] = cursor.getString(idx);

                                employee_tb_c_04_num = arr_item[2];

                                // ----------- アカウント情報 取得 ------------
                                g_account = arr_item[2]; // アカウント番号 取得
                                user_view.setText(arr_item[1]);
                                g_account_02 = arr_item[1]; // アカウント 名 取得

                                // --------------------- トランザクション コミット --------------------//
                                Ac_db.setTransactionSuccessful();

                            }

                        } finally {
                            // ------------------------ トランザクション 終了 -----------------------//
                            Ac_db.endTransaction();
                            Ac_db.close();

                        }

                        // putExtra で データを渡す ---------------------------------
                        Intent intent = new Intent(getApplication(), TopMenu.class);
                        intent.putExtra("account_id", g_account); // アカウント id
                        intent.putExtra("account_name", g_account_02); // アカウント名
                        intent.putExtra("acount_code", employee_tb_c_04_num); // カラム 04 : B0882
                        startActivity(intent);

                        // ************* ログイン OK
                        finish();

                    } else {

                        // ソフトキーボードを隠す
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(v.getWindowToken(), 0);

                        // toastMake("入力された「担当者コード」は存在しません。確認してください。",0,-200);

                        // エラー用 スナックバー

                        Snackbar.make(v, "「ログイン エラー」 " + "\n" + "入力された「担当コード」は存在しません。", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }

                }

            }
        });

    }

    /**
     * SQL から ユーザー 一覧を取得する
     */
    private void User_Conf() {

        helper = new TestOpenHelper(getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] arr_item = new String[1];

        try {

            Cursor cursor = db.rawQuery("SELECT TNMF_c_03 FROM TNMF_table;", null);

            if (cursor.moveToFirst()) {

                do {

                    int idx = cursor.getColumnIndex("TNMF_c_03");
                    arr_item[0] = cursor.getString(idx);

                    arr_col.add(arr_item[0]);

                } while (cursor.moveToNext()); // ------ END while

            } // ------ END if

            // --- 出力テスト ---
            for (String a : arr_col) {
                System.out.println(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            if (db != null) {
                db.close();
            }

        }

    }

}
