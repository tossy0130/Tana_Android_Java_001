# Tana Android Java 001

## 日本語

### 概要

このリポジトリは、Android Java の基本機能や実装パターンを学習・検証するためのサンプル集です。

主に、SQLite、APK削除処理、条件分岐、基本画面構成、Spinner、ZXing を使ったバーコード・QRコード関連機能などをフォルダごとに整理しています。

Android Java でアプリ開発を進める際の、実験用・学習用・メモ用リポジトリです。

---

### 主な内容

- Android Java の基本構成
- SQLite を使ったローカルDB操作
- APK削除・アプリ管理系の検証
- 条件分岐・番号判定系のサンプル
- Spinner を使った選択UI
- ZXing を使ったバーコード・QRコード処理
- Android アプリ開発の学習メモ

---

### ディレクトリ構成

```text
Tana_Android_Java_001/
├── Sqllite/
├── apk_delete/
├── case_num/
├── main/
├── spiner/
└── zxing/
```

---

### 各フォルダの説明

#### Sqllite/

Android Java で SQLite を扱うためのサンプルです。

ローカルDBの作成、データ登録、取得、更新、削除など、Android アプリ内でデータを保存・管理する基本処理の確認に使います。

---

#### apk_delete/

APK削除やアプリ管理に関する処理を検証するためのサンプルです。

Android 端末上のアプリ情報や削除関連の動作確認を目的としています。

---

#### case_num/

数値や条件によって処理を分岐するためのサンプルです。

`switch` 文や `if` 文など、Android Java の基本的な条件分岐処理の確認に使います。

---

#### main/

Android Java アプリの基本構成を確認するためのサンプルです。

Activity、レイアウト、ボタン操作など、最小構成の動作確認用として利用できます。

---

#### spiner/

Android の Spinner を使った選択UIのサンプルです。

プルダウン選択、選択値の取得、画面への反映などを確認するためのフォルダです。

---

#### zxing/

ZXing を使ったバーコード・QRコード関連機能のサンプルです。

QRコード読み取りやバーコード処理など、Android アプリにスキャン機能を組み込むための検証用です。

---

### 使用技術

- Java
- Android
- SQLite
- Spinner
- ZXing
- Android Studio

---

### 想定用途

このリポジトリは、以下のような用途を想定しています。

- Android Java の基礎学習
- Android Studio での小さな機能検証
- SQLite のローカルDB処理の確認
- Spinner UI の動作確認
- ZXing による QRコード・バーコード処理の検証
- 過去に作成した Android Java サンプルの整理

---

### 注意点

このリポジトリは学習・検証用です。

Android Studio や Gradle のバージョンによって、ビルド設定の修正が必要になる場合があります。

また、ZXing や SQLite 関連の実装では、使用しているライブラリや Android SDK のバージョンにより設定方法が変わる可能性があります。

APIキー、認証情報、個人情報などは GitHub に公開しないよう注意してください。

---

### 今後の改善案

- 各フォルダに個別 README を追加
- Android Studio での起動手順を追加
- 各サンプルのスクリーンショットを追加
- SQLite サンプルのCRUD説明を追加
- Spinner サンプルの画面説明を追加
- ZXing サンプルの導入手順を追加
- Gradle / Android SDK バージョン情報を整理
- Java 版から Kotlin 版への移植サンプルを追加

---

## English

### Overview

This repository is a collection of sample projects and notes for learning and testing basic Android Java features.

It includes examples related to SQLite, APK deletion or app management, conditional branching, basic app structure, Spinner UI, and barcode / QR code features using ZXing.

This repository is intended for learning, experimentation, and organizing Android Java sample code.

---

### Main Contents

- Basic Android Java app structure
- Local database operations with SQLite
- APK deletion and app management experiments
- Conditional branching and number-based logic samples
- Selection UI using Spinner
- Barcode and QR code features using ZXing
- Android app development learning notes

---

### Directory Structure

```text
Tana_Android_Java_001/
├── Sqllite/
├── apk_delete/
├── case_num/
├── main/
├── spiner/
└── zxing/
```

---

### Folder Description

#### Sqllite/

Sample code for using SQLite in Android Java.

This folder is useful for checking basic local database operations such as creating a database, inserting data, retrieving data, updating data, and deleting data.

---

#### apk_delete/

Sample code for testing APK deletion or app management related behavior.

This folder is intended for checking Android app information and deletion-related operations.

---

#### case_num/

Sample code for branching logic based on numbers or conditions.

It can be used to check basic Java control flow such as `switch` statements and `if` statements.

---

#### main/

Sample code for checking the basic structure of an Android Java application.

It can be used for testing minimal app behavior such as Activity setup, layout display, and button actions.

---

#### spiner/

Sample code for using Android Spinner UI.

This folder is useful for checking dropdown selection, retrieving selected values, and reflecting selection results on the screen.

---

#### zxing/

Sample code for barcode and QR code related features using ZXing.

It can be used as a reference for adding scanning features to Android applications.

---

### Technologies Used

- Java
- Android
- SQLite
- Spinner
- ZXing
- Android Studio

---

### Use Cases

This repository can be used for:

- Learning Android Java basics
- Testing small features in Android Studio
- Checking local database operations with SQLite
- Testing Spinner UI behavior
- Testing QR code and barcode processing with ZXing
- Organizing older Android Java sample projects

---

### Notes

This repository is for learning and experimentation.

Depending on the Android Studio and Gradle versions, build settings may need to be updated.

For ZXing and SQLite-related implementations, setup methods may vary depending on the library version and Android SDK version.

Do not publish API keys, credentials, or personal information to GitHub.

---

### Future Improvements

- Add a README file for each folder
- Add Android Studio setup and run instructions
- Add screenshots for each sample
- Add CRUD explanation for the SQLite sample
- Add UI explanation for the Spinner sample
- Add setup instructions for the ZXing sample
- Organize Gradle and Android SDK version information
- Add Kotlin versions of the samples
