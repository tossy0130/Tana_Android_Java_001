package spiner;

// ===================================================
// ============ SQL のデータ抽出して、Spinnerを作成する  Android
// ===================================================

class A01 {

    private ArrayList<String> Spinner_Item_Souko = new ArrayList<>();
    private HashMap<String, String> spinner_item_Souko = new HashMap<>();
    private Spinner spinner_Souko;

    public static void main(String args[]) {

        /**
         * // === 倉庫 スピナー値セット
         */
        // === Spinner_Item_Souko へ SQLから挿入
        GET_Spinner_Souko();

        spinner_Souko = findViewById(R.id.spinner_Souko);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_spinner,
                Spinner_Item_Souko);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinner_Souko.setAdapter(adapter);

    }

    /**
     * スピナー用 NP_data_table ＊＊＊ アイテム A 取得
     */
    private void GET_Spinner_A() {

        TestOpenHelper helper_sppiner = new TestOpenHelper(getApplicationContext());
        SQLiteDatabase db = helper_sppiner.getReadableDatabase();

        String[] arr_item = new String[2];

        int num = 0;

        // ---- リストを空にする
        spinner_item_A.clear();

        // ------------- スピナー アイテム取得
        try {

            Cursor cursor = db.rawQuery("select * from NP_data_table" +
                    " where NP_data_c_02 " +
                    "like 'A%' order by NP_data_c_02 ASC;", null);

            while (cursor.moveToNext()) {

                // ------- 9
                int idx = cursor.getColumnIndex("NP_data_c_01");
                Hinmoku_C_str = cursor.getString(idx);

                // ------- 朝礼・ミーティング・課内打合せ
                idx = cursor.getColumnIndex("NP_data_c_03");
                arr_item[0] = cursor.getString(idx);

                // ------- A1
                idx = cursor.getColumnIndex("NP_data_c_02");
                arr_item[1] = cursor.getString(idx);

                // ArrayList に 挿入
                spinner_item_A.add(arr_item[0]);

                // 比較用にハッシュマップに挿入
                spinner_item_A_Hash.put(arr_item[0], arr_item[1]);

                num++;

            } // -------- while END

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (db != null) {
                db.close();
            }
        }

        spinner_item_A.add(0, "リスト（ A1 ～ A10 ）");

    } // ------------ GET_Spinner_A END ---------------->

}
