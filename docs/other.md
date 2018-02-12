# 色々メモ

### 入力値の復元

エラーとなった項目のみが、リクエストパラメータから復元されるもよう
それ以外は、リクエストスコープ（model.addAttributeされているもの）から取得

### BeanUtils

* springのBeanUtilsは型が一致しない場合、nullになる。
* springのBeanUtilsはMap対応していない
* commonsのBeanUtilsはnullのBigDecimalの値がnullの場合に例外をthrow

### @PostConstruct / @PreDestroy

Bean の構築後、破棄前にフックをかけることができる。

* @PostConstructは構築後
* @PreDestroyは破棄前

に呼ばれるメソッドにつけるアノテーション
