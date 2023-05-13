
select * from 火葬受付;


select 予約日, 火葬枠番号 from 火葬受付;

/* 予約日、火葬枠番号 予約日が、今日から ~ １週間以内の火葬枠番号取得  */
select 予約日,火葬枠番号,count(火葬枠番号) as 枠合計 from 火葬受付 
	where 予約日 > NOW() and 予約日 <= (NOW() + interval 7 DAY)
	group by 火葬枠番号, 予約日;

/* 予約日、火葬枠番号 予約日が、今日から ~ １週間以内の火葬枠番号取得  */
select 予約日,火葬枠番号,count(火葬枠番号) as 枠合計 from 火葬受付 
	where 予約日 > NOW() and 予約日 <= (NOW() + interval 7 DAY)
	group by 予約日;

select 予約日,火葬枠番号 from 火葬受付;

select * from 火葬台帳;


select * from 施設;

/* 採番　マックス値　取得 */
select * from 採番;

/* 採番テーブル 開始値　取得 */
select 採番名, max(開始値), 年度 from 採番 
	group by 年度 having 採番名 = '予約番号' and 年度 = 2022;

/* 現在値 取得 */
select 採番名, max(現在値), 年度 from 採番 
	group by 年度 having 採番名 = '予約番号' and 年度 = 2022;
	
update 採番 set 現在値 = 23 where 採番名 = '予約番号';


select * from 業者;
select * from 担当者;

 
/* PDF 出力　予約確認書情報　出力 */
select * from 火葬受付;
select * from 火葬台帳;

/* 業者名 */　
select 死亡者住所２ from 火葬台帳;
select 申請者住所２ from 火葬台帳;

select * from 火葬区分;

/*  */
select ku.受付番号, ku.受付日時,ku.予約日,ku.火葬枠番号, ku.待合利用区分, ku.霊柩車利用区分,
		ku.式場利用区分, ku.通夜利用区分, 
	kd.申請者姓, kd.申請者名, kd.申請者姓かな, kd.申請者名かな 
	from 火葬受付 as ku
	left outer join 火葬台帳 kd on kd.整理番号 = ku.受付番号
	where ku.受付番号 = 2;


select * from 業者;
select * from 施設;


/*　 FAX　 初期設定  */
select * from 初期設定;
update 初期設定 set 斎場ＦＡＸ = "0480-65-8235" where 斎場名 = "メモリアルトネ";

select 斎場名, 斎場ＦＡＸ from 初期設定 where 斎場名 like '%メモリアルトネ%'; 
select 斎場名, 斎場ＦＡＸ from 初期設定 where 斎場名 = "メモリアルトネ";


