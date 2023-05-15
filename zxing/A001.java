
class A001 {

      public static void main(String args[]) {

      }

      @Override
      protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            if (scanResult.getContents() == null) {
                  return;
            }

            /**
             * /* * QR データ 取得
             */
            if (scanResult != null) {

                  String Scan_Val = scanResult.getContents();
                  if (Scan_Val.length() == 7 || Scan_Val.length() != 13) {
                        System.out.println("QR------2次元バーコード--------");

                        Case_Bara_Num_Init(); // 棚卸し数関係 初期化（ケース数、バラ数、棚卸し合計数）

                        /***
                         * ＝＝＝＝＝＝＝＝＝ 商品コード 検索 ＝＝＝＝＝＝＝＝＝
                         */
                        SELECT_Shouhin_Code(Scan_Val);

                        System.out.println("*********************** ２次元バーコード　で　読み取り OK *********************");

                        case_num_edit.requestFocus(); // EditTextにフォーカスを移動
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        // ソフトキーボードを表示する

                  } else {
                        System.out.println(" *********************** バーコード 開始 ***********************");
                        System.out.println("バーコード Scan_Val:::" + Scan_Val);

                        Case_Bara_Num_Init(); // 棚卸し数関係 初期化（ケース数、バラ数、棚卸し合計数）

                        /**
                         * JANコード 重複チェック
                         */
                        TestOpenHelper j_helper1 = new TestOpenHelper(getApplicationContext());
                        SQLiteDatabase j_db = j_helper1.getReadableDatabase();
                        int T_NUM = 0;
                        int test_count = 0;

                        try {

                              /***
                               * ＝＝＝＝＝＝＝＝＝ JANコード 検索 ＝＝＝＝＝＝＝＝＝
                               */
                              // Cursor cursor = j_db.rawQuery("SELECT COUNT(*) FROM SHMF_table WHERE
                              // SHMF_c_03 = " + "\"" + Scan_Val + "\"" + ";", null);

                              // SHMF_c_01:商品C, SHMF_c_02:品名, SHMF_c_03:JANコード, SHMF_c_04:品番,
                              // SHMF_c_05:入数,RZMF_c_02:倉庫C、RZMF_c_03:棚番,SOMF_c_02:倉庫名、SOMF_c_03 text：棚卸日
                              Cursor cursor = j_db.rawQuery(
                                          "SELECT SHMF_c_01, SHMF_c_02, SHMF_c_03, SHMF_c_04,SHMF_c_05,RZMF_c_02,RZMF_c_03,SOMF_c_02,SOMF_c_03 FROM SHMF_table "
                                                      +
                                                      "left outer join RZMF_table on SHMF_table.SHMF_c_01 = RZMF_table.RZMF_c_01 "
                                                      +
                                                      "left outer join SOMF_table on SOMF_table.SOMF_c_01 = RZMF_table.RZMF_c_02 "
                                                      +
                                                      "WHERE SHMF_table.SHMF_c_03 = " + "\"" + Scan_Val + "\"" + ";",
                                          null);

                              // JAN_count_02
                              JAN_count_02 = cursor.getCount();
                              System.out.println("JAN_count_02:::" + JAN_count_02);

                              if (cursor != null && cursor.moveToFirst()) {

                                    do {
                                          T_NUM++;
                                          JAN_count = cursor.getCount();
                                          test_count++;
                                          System.out.println("test_count:::" + test_count);
                                    } while (cursor.moveToNext());

                              } // ======= END if

                              // === JAN コード検索数
                              System.out.println("cursor.getCount() 01;:::" + cursor.getCount());
                              System.out.println("T_NUM;:::" + T_NUM);

                              /**
                               * ＝＝＝ 取得結果の カラム数が １件の場合 ＝＝＝
                               */
                              if (JAN_count == 1) {

                                    String[] arr_item = new String[7];

                                    String edit_qr_num01 = "";
                                    String edit_qr_num02 = "";
                                    // String edit_qr_num03 = "";

                                    if (cursor != null && cursor.moveToFirst()) {

                                          do {

                                                // カラム 01
                                                int idx = cursor.getColumnIndex("SHMF_c_01"); // 商品C
                                                arr_item[0] = cursor.getString(idx);
                                                edit_qr_num01 = arr_item[0];

                                                Shouhin_Code_Insert = arr_item[0]; // *** 商品コード インサート用

                                                // === テキストビュー へ 商品コードを挿入
                                                h_moku_text.setText(edit_qr_num01);

                                                // カラム 02
                                                idx = cursor.getColumnIndex("SHMF_c_02"); // 品名
                                                arr_item[1] = cursor.getString(idx);
                                                edit_qr_num02 = arr_item[1];

                                                Shouhin_Name_Insert = arr_item[1]; // *** 商品名 インサート用

                                                // INSERT 用 品目コード
                                                SH_col_2 = arr_item[1];
                                                System.out.println(SH_col_2 + "SH_col_2　出力テスト");

                                                // カラム 03
                                                idx = cursor.getColumnIndex("SHMF_c_03"); // JANコード
                                                arr_item[2] = cursor.getString(idx);

                                                // === テキストビュー へ 商品名 挿入
                                                h_moku_m_text.setText(arr_item[1]);

                                                // === jan コード表示用
                                                jancode_text.setText(arr_item[2]);

                                                // カラム 04
                                                idx = cursor.getColumnIndex("RZMF_c_02"); // 倉庫C
                                                arr_item[3] = cursor.getString(idx);

                                                Souko_Code_Insert = arr_item[3]; // *** 倉庫コード インサート用

                                                // カラム 05
                                                idx = cursor.getColumnIndex("SHMF_c_05"); // 入数
                                                arr_item[4] = cursor.getString(idx);

                                                // === 入数が０の場合、１を入れる
                                                if (arr_item[4].equals("0")) {
                                                      arr_item[4] = "1";
                                                }

                                                // === 入り数 表示用
                                                irisuu_text.setText(arr_item[4]);

                                                Iri_Num_Insert = arr_item[4]; // *** 入数 インサート用

                                                // カラム 06
                                                idx = cursor.getColumnIndex("RZMF_c_03"); // 棚番（ロケーション）
                                                arr_item[5] = cursor.getString(idx);

                                                /**
                                                 * ロケーション チェック
                                                 */
                                                Location_Num_Check(arr_item[5]);

                                                String Location_Code = arr_item[5];

                                                // ロケーションコード
                                                // location_text.setText(Location_Code);

                                                // カラム 07
                                                idx = cursor.getColumnIndex("SOMF_c_02"); // 倉庫名
                                                arr_item[6] = cursor.getString(idx);

                                                // === テキストビューへ 倉庫名をセット
                                                // h_moku_b_text.setText(arr_item[6]);

                                          } while (cursor.moveToNext());

                                    } // ======= END if

                                    System.out.println("IF １件分岐:::OK =======================");

                                    case_num_edit.requestFocus(); // EditTextにフォーカスを移動
                                    getWindow().setSoftInputMode(
                                                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                                    // ソフトキーボードを表示する

                                    System.out.println(" *********************** バーコード 終了 ***********************");

                              } else if (JAN_count >= 2 || JAN_count != 0) {
                                    /**
                                     * ＝＝＝ 取得結果の カラム数が 2件以上の場合 ＝＝＝
                                     */

                                    String[] arr_item = new String[7];

                                    String edit_qr_num01 = "";
                                    String edit_qr_num02 = "";
                                    // String edit_qr_num03 = "";

                                    int loop_count = 0;

                                    if (cursor != null && cursor.moveToFirst()) {

                                          do {

                                                // カラム 01
                                                int idx = cursor.getColumnIndex("SHMF_c_01"); // 商品C
                                                arr_item[0] = cursor.getString(idx);
                                                edit_qr_num01 = arr_item[0];

                                                // === テキストビュー へ 商品コードを挿入
                                                // h_moku_text.setText(edit_qr_num01);

                                                JAN_Shoushi_C_List.add(arr_item[0]); // 重複している JANコードの商品コード 取得

                                                // カラム 02
                                                idx = cursor.getColumnIndex("SHMF_c_02"); // 品名
                                                arr_item[1] = cursor.getString(idx);
                                                edit_qr_num02 = arr_item[1];

                                                JAN_Shoushi_Name_List.add(arr_item[1]); // 商品名 リスト挿入

                                                // INSERT 用 品目コード
                                                SH_col_2 = arr_item[1];

                                                System.out.println(SH_col_2 + "SH_col_2　出力テスト");

                                                // カラム 03
                                                idx = cursor.getColumnIndex("SHMF_c_03"); // JANコード
                                                arr_item[2] = cursor.getString(idx);

                                                GET_sql_Jan_Code = arr_item[2];

                                                // === テキストビュー へ 商品名 挿入
                                                // h_moku_m_text.setText(arr_item[1]);

                                                // === jan コード表示用
                                                // jancode_text.setText(arr_item[2]);

                                                // === 入り数 表示用
                                                // irisuu_text.setText(arr_item[4]);

                                                loop_count++;
                                          } while (cursor.moveToNext());

                                    }

                                    // ====== 配列の要素数を取得して、分岐する
                                    int GEt_Zyuufuku_NUM = JAN_Shoushi_C_List.size();
                                    System.out.println("GEt_Zyuufuku_NUM:::" + GEt_Zyuufuku_NUM);

                                    // --------------- アラートダイアログ の表示 開始 ---------------------
                                    AlertDialog.Builder bilder = new AlertDialog.Builder(ReadBerCode.this);

                                    // -------------- カスタムタイトル 作成
                                    TextView titleView;
                                    titleView = new TextView(ReadBerCode.this);
                                    titleView.setText("JANコード"
                                                + "【" + GET_sql_Jan_Code + "】" + "\n"
                                                + "\n\n" + "重複している商品がありますので、選択してください。");
                                    titleView.setTextSize(18);
                                    titleView.setTextColor(Color.WHITE);
                                    titleView.setBackgroundColor(getResources().getColor(R.color.back_color_01));
                                    titleView.setPadding(20, 30, 20, 30);
                                    titleView.setGravity(Gravity.CENTER);
                                    // -------------- カスタムタイトル 作成 END
                                    // ダイアログの項目
                                    bilder.setCustomTitle(titleView);

                                    switch (GEt_Zyuufuku_NUM) {
                                          // === JAN で重複レコードが２件だった場合
                                          case 2:
                                                Shouhin_C_01 = JAN_Shoushi_C_List.get(0);
                                                Shouhin_C_02 = JAN_Shoushi_C_List.get(1);

                                                Shouhin_Name_01 = JAN_Shoushi_Name_List.get(0);
                                                Shouhin_Name_02 = JAN_Shoushi_Name_List.get(1);

                                                // -------------- ダイアログ メッセージ内容
                                                /*
                                                 * String msg_bilder = "商品コード 01：" + Shouhin_C_01 + "\n" +
                                                 * "商品名 01：" + Shouhin_Name_01 + "\n\n" +
                                                 * "商品コード 02：" + Shouhin_C_02 + "\n" +
                                                 * "商品名 02：" + Shouhin_Name_02;
                                                 * bilder.setMessage(msg_bilder);
                                                 * 
                                                 */

                                                // set dialog message
                                                bilder.setItems(new CharSequence[] {
                                                            "【" + Shouhin_C_01 + "】" + ":" + Shouhin_Name_01 + "\n\n",
                                                            "【" + Shouhin_C_02 + "】" + ":" + Shouhin_Name_02 + "\n\n",
                                                            "キャンセル"
                                                }, new DialogInterface.OnClickListener() {
                                                      public void onClick(DialogInterface dialog, int which) {
                                                            // 選択されたアイテムに応じた処理を実行
                                                            switch (which) {
                                                                  case 0:
                                                                        // Item1 が選択された場合の処理
                                                                        SELECT_Shouhin_Code(Shouhin_C_01);

                                                                        JAN_Shoushi_C_List.clear();
                                                                        break;
                                                                  case 1:
                                                                        SELECT_Shouhin_Code(Shouhin_C_02);

                                                                        JAN_Shoushi_C_List.clear();
                                                                        break;
                                                                  case 2:
                                                                        // Item3 が選択された場合の処理
                                                                        break;
                                                            }
                                                      }
                                                });

                                                dialog = bilder.create();
                                                dialog.show();

                                                break;

                                          // === JAN で重複レコードが、３件だった場合
                                          case 3:
                                                Shouhin_C_01 = JAN_Shoushi_C_List.get(0);
                                                Shouhin_C_02 = JAN_Shoushi_C_List.get(1);
                                                Shouhin_C_03 = JAN_Shoushi_C_List.get(2);

                                                Shouhin_Name_01 = JAN_Shoushi_Name_List.get(0);
                                                Shouhin_Name_02 = JAN_Shoushi_Name_List.get(1);
                                                Shouhin_Name_03 = JAN_Shoushi_Name_List.get(2);

                                                // -------------- ダイアログ メッセージ内容
                                                msg_bilder = "商品コード 01：" + Shouhin_C_01 + "\n" +
                                                            "商品名 01：" + Shouhin_Name_01 + "\n\n" +
                                                            "商品コード 02：" + Shouhin_C_02 + "\n" +
                                                            "商品名 02：" + Shouhin_Name_02 + "\n\n" +
                                                            "商品名 03：" + Shouhin_Name_03 + "\n" +
                                                            "商品コード 03：" + Shouhin_C_03 + "\n";
                                                bilder.setMessage(msg_bilder);

                                                bilder.setPositiveButton(Shouhin_C_01,
                                                            new DialogInterface.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(DialogInterface dialog,
                                                                              int which) {
                                                                        return;
                                                                  }
                                                            });

                                                bilder.setNegativeButton(Shouhin_C_02,
                                                            new DialogInterface.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(DialogInterface dialog,
                                                                              int which) {
                                                                        return;
                                                                  }
                                                            });

                                                bilder.setNeutralButton(Shouhin_C_03,
                                                            new DialogInterface.OnClickListener() {
                                                                  public void onClick(DialogInterface dialog, int id) {
                                                                        // Button2がクリックされた場合の処理
                                                                  }
                                                            });

                                                dialog = bilder.create();
                                                dialog.show();

                                                break;
                                    }

                                    System.out.println("IF ２件分岐:::OK =======================");

                              } else {
                                    /**
                                     * ＝＝＝ ０件だった場合の処理 ＝＝＝
                                     */

                                    System.out.println("IF ０件分岐:::OK =======================");
                                    return;
                              }

                        } catch (SQLException e) {
                              e.printStackTrace();
                        } finally {
                              if (j_db != null) {
                                    j_db.close();
                              }
                        }

                  }

            }

      }// ----------------------- END

      /**
       * 商品コード から SELECT
       */
      private void SELECT_Shouhin_Code(String Shouhin_Code) {

            TestOpenHelper helper = new TestOpenHelper(getApplicationContext());
            SQLiteDatabase Sh_db = helper.getReadableDatabase();

            String[] arr_item = new String[7];

            String edit_qr_num01 = "";
            String edit_qr_num02 = "";
            // String edit_qr_num03 = "";

            try {

                  Cursor cursor = Sh_db.rawQuery(
                              "SELECT SHMF_c_01, SHMF_c_02, SHMF_c_03, SHMF_c_04,SHMF_c_05,RZMF_c_02,RZMF_c_03,SOMF_c_02,SOMF_c_03 FROM SHMF_table "
                                          +
                                          "left outer join RZMF_table on SHMF_table.SHMF_c_01 = RZMF_table.RZMF_c_01 " +
                                          "left outer join SOMF_table on SOMF_table.SOMF_c_01 = RZMF_table.RZMF_c_02 " +
                                          "WHERE SHMF_table.SHMF_c_01 = " + "\"" + Shouhin_Code + "\"" + ";",
                              null);

                  if (cursor.moveToNext()) {

                        // カラム 01
                        int idx = cursor.getColumnIndex("SHMF_c_01"); // 商品C
                        arr_item[0] = cursor.getString(idx);
                        edit_qr_num01 = arr_item[0];

                        // === テキストビュー へ 商品コードを挿入
                        h_moku_text.setText(edit_qr_num01);

                        // カラム 02
                        idx = cursor.getColumnIndex("SHMF_c_02"); // 品名
                        arr_item[1] = cursor.getString(idx);
                        edit_qr_num02 = arr_item[1];

                        // INSERT 用 品目コード
                        SH_col_2 = arr_item[1];

                        System.out.println(SH_col_2 + "SH_col_2　出力テスト");

                        // カラム 03
                        idx = cursor.getColumnIndex("SHMF_c_03"); // JANコード
                        arr_item[2] = cursor.getString(idx);

                        jancode_text.setText(arr_item[2]); // JANコード 表示用

                        // === テキストビュー へ 商品名 挿入
                        h_moku_m_text.setText(arr_item[1]);

                        // カラム 04
                        idx = cursor.getColumnIndex("SHMF_c_04"); // 倉庫C
                        arr_item[3] = cursor.getString(idx);

                        // カラム 05
                        idx = cursor.getColumnIndex("SHMF_c_05"); // 入数
                        arr_item[4] = cursor.getString(idx);

                        irisuu_text.setText(arr_item[4]); // 入り数 表示用

                        // カラム 06
                        idx = cursor.getColumnIndex("RZMF_c_03"); // 棚番（ロケーション）
                        arr_item[5] = cursor.getString(idx);

                        String Location_Code = arr_item[5];

                        // ロケーションコード
                        // location_text.setText(Location_Code);

                        // カラム 07
                        idx = cursor.getColumnIndex("SOMF_c_02"); // 倉庫名
                        arr_item[6] = cursor.getString(idx);

                        // === テキストビューへ 倉庫名をセット
                        // h_moku_b_text.setText(arr_item[6]);

                  }

                  case_num_edit.requestFocus(); // EditTextにフォーカスを移動
                  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                  dialog.dismiss(); // ダイアログを閉じる

            } catch (SQLException e) {
                  e.printStackTrace();
            } finally {
                  if (Sh_db != null) {
                        Sh_db.close();
                  }
            }

      }

      /**
       * ケース数、バラ数、空に初期化
       */
      private void Case_Bara_Num_Init() {
            case_num_edit.setText(""); // ケース数
            case_sum_text.setText(""); // ケース数 合計
            bara_num_edit.setText(""); // バラ数
            tana_sum_text.setText(""); // 棚卸し 合計数
      }

      /**
       * 入力している ロケーションと、QR, バーコード, 手打ち入力で取得したロケーションが違った場合アラートを出す
       */
      private void Location_Num_Check(String Get_Loca_Num) {

            String Input_Loca = location_name.getText().toString();
            if (Input_Loca.isEmpty() || Input_Loca.equals("")) {

            } else {
                  if (!(Input_Loca.equals(Get_Loca_Num))) {

                        // ******************** オリジナルアラートログの表示 処理 開始 ********************//
                        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                        final View bilde_layout_02 = inflater.inflate(R.layout.dialog,
                                    (ViewGroup) findViewById(R.id.alertdialog_layout_01));

                        // *********** コンポーネント 初期化

                        dia_chack_01_01_edit = bilde_layout_02.findViewById(R.id.dia_chack_01_01_edit); // 時間入力
                        dia_chack_01_01_edit.setFocusable(true);
                        dia_chack_01_01_edit.setFocusableInTouchMode(true);
                        dia_chack_01_01_edit.setEnabled(true);

                        dia_touroku_btn_001 = bilde_layout_02.findViewById(R.id.dia_touroku_btn_001); // 登録ボタン
                        dia_touroku_btn_002 = bilde_layout_02.findViewById(R.id.dia_touroku_btn_002); // キャンセルボタン

                        // ****** エディットテキスト ナンバー入力設定
                        dia_chack_01_01_edit.setInputType(InputType.TYPE_CLASS_NUMBER);

                        // === 説明テキスト
                        location_check_setumei = bilde_layout_02.findViewById(R.id.location_check_setumei);

                        // --------------- アラートダイヤログ タイトル 設定 ---------------//
                        AlertDialog.Builder bilder = new AlertDialog.Builder(ReadBerCode.this);
                        // タイトル
                        TextView titleView;
                        titleView = new TextView(ReadBerCode.this);
                        titleView.setText("ロケーションチェック");
                        titleView.setTextSize(22);
                        titleView.setTextColor(Color.WHITE);
                        titleView.setBackgroundColor(getResources().getColor(R.color.colorPinku));
                        titleView.setPadding(20, 30, 20, 30);
                        titleView.setGravity(Gravity.CENTER);

                        // ダイアログに 「タイトル」 を セット
                        bilder.setCustomTitle(titleView);

                        // カスタムレイアウト を セット
                        bilder.setView(bilde_layout_02);

                        location_check_setumei.setText("入力されたロケーション" + "【" + Input_Loca + "】" + "\n" +
                                    "マスターで取得したロケーション" + "【" + Get_Loca_Num + "】" + "\n" +
                                    "の値が違います。入力されたロケーション番号を修正しますか？");

                        AlertDialog dialog = bilder.create();
                        dialog.show();

                        dia_chack_01_01_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                              @Override
                              public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                                    // ****** ソフトキーボードが押されたら
                                    if (actionId == EditorInfo.IME_ACTION_DONE
                                                || actionId == EditorInfo.IME_ACTION_NEXT) {

                                          String Get_Dialog_Loca_Tmp = dia_chack_01_01_edit.getText().toString();

                                          if (Get_Dialog_Loca_Tmp.isEmpty() || Get_Dialog_Loca_Tmp.equals("")) {
                                                return false;
                                          } else {
                                                Get_Dialog_Loca = Get_Dialog_Loca_Tmp;
                                          }

                                    }

                                    return false;
                              }
                        });

                        /**
                         * ロケーションcheck ダイアログ（変更）ボタン
                         */
                        dia_touroku_btn_001.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {

                                    Get_Dialog_Loca = dia_chack_01_01_edit.getText().toString();

                                    if (Get_Dialog_Loca.isEmpty() || Get_Dialog_Loca.equals("")) {

                                          // *********** キャンセル
                                          dialog.dismiss();

                                    } else {
                                          /**
                                           * ダイアログで、ロケーションコード変更処理
                                           */
                                          location_name.setText(Get_Dialog_Loca);
                                          toastMake("ロケーションコードを変更しました。", 0, -200);

                                          case_num_edit.requestFocus(); // EditTextにフォーカスを移動
                                          getWindow().setSoftInputMode(
                                                      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                                          dialog.dismiss();
                                    }

                              }
                        });

                        /**
                         * ロケーションcheck ダイアログ（キャンセル）ボタン
                         */
                        dia_touroku_btn_002.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {

                                    case_num_edit.requestFocus(); // EditTextにフォーカスを移動
                                    getWindow().setSoftInputMode(
                                                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                                    // *********** キャンセル
                                    dialog.dismiss();
                              }
                        });

                  }

            }

      }

      /**
       * 現在日時をyyyy/MM/dd HH:mm:ss形式で取得する.
       */
      public static String getNowDate() {

            final DateFormat df = new SimpleDateFormat("yyyyMMdd");
            final Date date = new Date(System.currentTimeMillis());

            return df.format(date);
      }

}
