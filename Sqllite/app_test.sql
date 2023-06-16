 /* === バーコードロジック , QRを検索して、同じ倉庫番号だったら１件だけ出力 === */
 SELECT SHMF_c_01, SHMF_c_02, SHMF_c_03 ,SHMF_c_04,SHMF_c_05,t1.RZMF_c_02, t1.RZMF_c_03, SOMF_table.SOMF_c_02, SOMF_table.SOMF_c_03
  FROM SHMF_table
  LEFT OUTER JOIN (SELECT * FROM RZMF_table WHERE RZMF_c_02 = '3') t1
    ON SHMF_table.SHMF_c_01 = t1.RZMF_c_01
  left outer join SOMF_table on SOMF_table.SOMF_c_01 = t1.RZMF_c_02
 WHERE SHMF_c_03 = '0028632267998';

/* ===================================================== */
/* ====================== JAN重複なし====================== */
 /* ===================================================== */
/* === 01 RZMF 倉庫Ｃが 1.東北のみ === */
SELECT SHMF_c_01, SHMF_c_02, SHMF_c_03, SHMF_c_04,SHMF_c_05,RZMF_c_02,RZMF_c_03,SOMF_c_02,SOMF_c_03 FROM SHMF_table
                            left outer join RZMF_table on SHMF_table.SHMF_c_01 = RZMF_table.RZMF_c_01
                            left outer join SOMF_table on SOMF_table.SOMF_c_01 = RZMF_table.RZMF_c_02
                            WHERE RZMF_table.RZMF_c_02 = "1";
                           
/* === 02 RZMF 倉庫Ｃが 1.東北以外 === */
SELECT SHMF_c_01, SHMF_c_02, SHMF_c_03, SHMF_c_04,SHMF_c_05,RZMF_c_02,RZMF_c_03,SOMF_c_02,SOMF_c_03 FROM SHMF_table
                            left outer join RZMF_table on SHMF_table.SHMF_c_01 = RZMF_table.RZMF_c_01
                            left outer join SOMF_table on SOMF_table.SOMF_c_01 = RZMF_table.RZMF_c_02
                            WHERE RZMF_table.RZMF_c_02 <> "1";
                           
/* === 03 RZMF 倉庫Ｃが 1.東北 以外のみあり === */
SELECT * from RZMF_table;
SELECT * from RZMF_table WHERE RZMF_c_02 <> "1";SHMF_c_03
 
/* === 04 RZMF 倉庫Ｃが JAN重複なし  RZMF なし === */
SELECT * from SHMF_table;

/* 倉庫コードが　null のものを抽出 */
SELECT SHMF_table.SHMF_c_01 , SHMF_table.SHMF_c_03 , RZMF_table.RZMF_c_01,RZMF_table.RZMF_c_02
FROM SHMF_table
LEFT JOIN RZMF_table ON SHMF_table.SHMF_c_01 = RZMF_table.RZMF_c_01
WHERE RZMF_table.RZMF_c_02 IS NULL;
                           
/* === 重複している値を取得 === */
SELECT SHMF_c_03 
FROM SHMF_table
GROUP BY SHMF_c_03
HAVING COUNT(*) > 1;
                           
/* === 重複している値を取得 === */
SELECT SHMF_c_03
FROM SHMF_table
UNION
SELECT RZMF_c_01
FROM RZMF_table
GROUP BY RZMF_c_01
HAVING COUNT(*) > 1;