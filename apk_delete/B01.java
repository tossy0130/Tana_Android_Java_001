public class B01 {

    /**
     * ================================================
     * ============ Android 9以下 Downloadフォルダ内の Toyama_Touhoku_Tana_App.apkファイルを削除する
     * ================================================
     */

    public static void main(String args[]) {

        /**
         * ================== パーミッションリクエスト ====================
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                // アクセス許可がすでに与えられている場合の処理
                System.out.println("========== パーミッション 許可済み ===========");

                String fileName = "Toyama_Touhoku_Tana_App.apk";
                // パーミッションが許可されている場合はファイルを削除

                /**
                 * 削除 function
                 */
                deleteFileFromDownloads(this, fileName);

                deleteFile();

            } else {
                System.out.println("========== パーミッション 許可を取る～ ===========");
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, REQUEST_CODE);
            }
        } else {

            /**
             * Android 9以下の処理
             */

            System.out.println("========== ビルドバージョン変えて～ ===========");

            requestWriteExternalStoragePermission();
        }

    } // === main END

    /**
     * Android 9以下 のパーミッション確認方法
     *
     */
    // パーミッションのリクエスト結果を受け取るコールバック
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // パーミッションが許可された場合はファイルの削除処理を実行
                deleteFileFromDownloadDirectory();
            } else {
                // パーミッションが拒否された場合はユーザーに通知などの処理を行う
                // ...
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // バックグラウンドからの復帰時の処理

        /**
         * APK 削除処理
         */
        String fileName = "Toyama_Touhoku_Tana_App.apk";
        // パーミッションが許可されている場合はファイルを削除
        deleteFileFromDownloads(this, fileName);

        // ＝＝＝ deleteFile 関数実行
        deleteFile();

        /**
         * APK android 9以下 削除処理
         */
        requestWriteExternalStoragePermission();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // バックグラウンドからの復帰時の処理
        user_input.setText("");
        user_view.setText("");

        /**
         * APK 削除処理
         */
        String fileName = "Toyama_Touhoku_Tana_App.apk";
        // パーミッションが許可されている場合はファイルを削除
        deleteFileFromDownloads(this, fileName);

        // ＝＝＝ deleteFile 関数実行
        deleteFile();

        /**
         * APK android 9以下 削除処理
         */
        requestWriteExternalStoragePermission();
    }

    // パーミッションのリクエスト
    private void requestWriteExternalStoragePermission() {
        // パーミッションが既に許可されているかチェック
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // パーミッションをリクエスト
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                    REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE);
        } else {
            // パーミッションがすでに許可されている場合はファイルの削除処理を実行
            deleteFileFromDownloadDirectory();
        }
    }

    // ダウンロードディレクトリ内のファイルを削除する処理
    private void deleteFileFromDownloadDirectory() {
        String fileName = "Toyama_Touhoku_Tana_App.apk"; // 削除するファイル名

        String downloadDirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .getAbsolutePath();
        String filePath = downloadDirPath + File.separator + fileName;

        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                // ファイルの削除成功
                System.out.println("Android 9 以下 ファイル削除 成功");
            } else {
                // ファイルの削除失敗
                System.out.println("Android 9 以下 ファイル削除 失敗");
            }
        } else {
            // ファイルが存在しない場合
            System.out.println("Android 9 以下 ファイル削除 ファイルがないよ");
        }
    } // === deleteFileFromDownloadDirectory END

} // ====== class B01 END
