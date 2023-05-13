
class A01 {

      public static void main(String args[]) {

    
          //---------------- ケース数　エディットテキストで　ボタンを押された時の処理 ---------------------
        case_num_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                // エンターボタンが押されたら
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){

                    // ソフトキーボード 非表示
                    if (getCurrentFocus() != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }

                    String Case_Num = case_num_edit.getText().toString();
                    System.out.println("Case_Num:::" + Case_Num);
                    // === ケース数が「空」か「０」じゃない場合
                    if(Case_Num.isEmpty() || Case_Num.equals("0")) {

                        toastMake("ケース数を入力してください。", 0, -200);
                        return false;

                    } else {

                        // === 入り数取得
                        String Iri_num = irisuu_text.getText().toString();
                        if(!(Iri_num.equals("0"))) {
                            int Iri_num_i = Integer.parseInt(Iri_num);
                            int Case_num_i = Integer.parseInt(Case_Num);

                            int Case_SUM = Iri_num_i * Case_num_i;
                            case_sum_text.setText(String.valueOf(Case_SUM));

                        } else {
                            toastMake("入り数が「０」です。", 0, -200);
                            case_num_edit.setText("0");
                            case_sum_text.setText("0");
                            return false;
                        }

                    }

                }

                return false;
            }
        });




      }

}