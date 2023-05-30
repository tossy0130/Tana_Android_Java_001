class A01 {

    /**
     * ================================================
     * ============ Android 10以上 Downloadフォルダ内の Toyama_Touhoku_Tana_App.apkファイルを削除する
     * ================================================
     */

    // === Android 10 以上 .apk 削除用
    private static final int REQUEST_CODE = 1;

    public static void main(String args[]) {

        /**
         * ================== パーミッションリクエスト Android 10以上 ====================
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
            System.out.println("========== ビルドバージョン変えて～ ===========");

            requestWriteExternalStoragePermission();
        }

    }

    /**
     * Android 10 以上用 内部ストレージ ダウンロードフォルダ 内 ファイル削除
     */
    private void deleteFile() {

        // === 使用
        String downloadFolderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .getAbsolutePath();
        File downloadFolder = new File(downloadFolderPath);
        File[] files = downloadFolder.listFiles();

        for (File file : files) {
            String fileName = file.getName(); // ファイル名取得
            String filepaht = file.getPath(); // パス取得

            System.out.println("fileName:::" + fileName);
            System.out.println("filepaht:::" + filepaht);
        }

        // 内部ストレージパス
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                + File.separator + "Toyama_Touhoku_Tana_App.apk";

        String downloadDir_tmp = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                + File.separator + "Toyama_Touhoku_Tana_App.apk";
        System.out.println("downloadDir_tmp:::" + downloadDir_tmp);
        String downloadDir_001 = File.separator + "ome_2021-1.jpeg";
        System.out.println("downloadDir_001:::" + downloadDir_001);

        // 外部ストレージパス
        // String filePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) +
        // File.separator + "Toyama_Touhoku_Tana_App.apk";
        System.out.println("filePath:::" + filePath);

        String fileName = "";

        /**
         * === 削除処理 === Files.delete(path);
         */
        try {

            File[] filess = downloadFolder.listFiles();

            for (File file : filess) {
                fileName = file.getName(); // ファイル名取得
                System.out.println("fileName for:::" + fileName);

                if (fileName.equals("tanaoroshi.apk")) {

                    System.out.println(" =========== Files.delete(path) 削除処理　開始 ===========");

                    Path path = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        path = Paths.get(file.getPath());
                    }
                    System.out.println("file.getPath():::" + file.getPath());

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Files.delete(path);
                    }
                    System.out.println(" Files.delete(path) ::: ファイルが削除されました。");
                    break;
                }

                String filepaht = file.getPath(); // パス取得

                System.out.println("fileName:::" + fileName);
                System.out.println("filepaht:::" + filepaht);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ファイルの削除中にエラーが発生しました。");
        }

    } // ============= END deleteFile

}