
class 01 {
    public static void main(String args[]) {
       
        //------------------ 担当コード　判別用 SQL
        TestOpenHelper helper = new TestOpenHelper(getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        if (doesTableExist(db, "TNMF_table")) {
            // テーブルが存在する場合の処理
            User_Conf();
        } else {
            // テーブルが存在しない場合の処理
        }


    }

    /**
     *  テーブル存在　確認
     */
    private boolean doesTableExist(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("SELECT * FROM sqlite_master WHERE type='table' AND name=?", new String[] { tableName });
        boolean tableExists = (cursor != null) && (cursor.getCount() > 0);
        if (cursor != null) {
            cursor.close();
        }
        return tableExists;
    }


}
