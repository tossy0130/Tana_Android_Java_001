/* カラムの中の最長文字列を取得 */
SELECT MAX(LENGTH(RZMF_c_03))
FROM RZMF_table;

select MAX(LENGTH(TNMF_c_02))
From TNMF_table;

/* 一番長い　カラム内のレコードを抽出 */
SELECT TNMF_c_02
FROM TNMF_table
WHERE LENGTH(TNMF_c_02) = (SELECT MAX(LENGTH(TNMF_c_02)) FROM TNMF_table);

/* QR コード （商品コード検索） */
SELECT SHMF_c_01, SHMF_c_02, SHMF_c_03, SHMF_c_04, SHMF_c_05,RZMF_c_03, SOMF_c_02 FROM SHMF_table 
	left outer JOIN RZMF_table ON SHMF_table.SHMF_c_01 = RZMF_table.RZMF_c_01
	left outer JOIN SOMF_table ON SHMF_table.SHMF_c_04 = SOMF_table.SOMF_c_01 
	WHERE SHMF_table.SHMF_c_01 = "0110083";

/* バーコード 検索 */
SELECT SHMF_c_01, SHMF_c_02, SHMF_c_03, SHMF_c_04, SHMF_c_05,RZMF_c_03, SOMF_c_02 FROM SHMF_table 
	left outer JOIN RZMF_table ON SHMF_table.SHMF_c_01 = RZMF_table.RZMF_c_01
	left outer JOIN SOMF_table ON SHMF_table.SHMF_c_04 = SOMF_table.SOMF_c_01 
	WHERE SHMF_table.SHMF_c_03 = "4960778000472";