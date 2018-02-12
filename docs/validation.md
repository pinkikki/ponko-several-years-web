## validationに関するメモ

### jsr310

`@DateTimeFormat`を付与する事で、patternの指定が可能
`@DateTimeFormat`を付与しないと、デフォルトのパターンになってそう。
※デフォルトはなんだ？？？

そもそも、validationは、

* PropertyEditor
* ConversionService（Formatterが含まれている？

の2つで、変換が実施される
 →　たぶん使われている。`AbstractPropertyBindingResult#findEditor`とかかな。`RequestPartMethodArgumentResolver#validateIfApplicable`とかからソースを追ってみた。
 
PropertyEditor→ConversionServiceの順で実施されている
`TypeConverterDelegate#convertIfNecessary`で実施されている。

Filed Fomratteringは`ConversionService`の中で実施される

`@DateTimeFormat`のデフォルトパターンはシステム共通で変更できない気がする。

* `DateTimeFormatAnnotationFormatterFactory#getFormatter`
* `DateFormatter`

が該当モジュール

### BindingResult

Controllerの引数に`@Validated`を指定した時は、`BindingResult`も引数にないと例外が発生する。

該当ソースは以下

`ModelAttributeMethodProcessor#resolveArgument`のL116あたり