## 例外ハンドリングに関するメモ

基本的には、以下の4つの方法がある

* Controllerの各メソッドでtry-catch
  * Controller内の各メソッド単位で個別にハンドリングを実施したい場合
* Controllerに＠ExceptionHandlerを付与したメソッドを用意
  * Controller単位でハンドリングしたい場合
* @ControllerAdviceを付与したクラスで@ExcpetionHandlerを付与したメソッドを用意
  * 全てのController共通でハンドリングしたい場合
* HandlerExceptionResolverを継承したクラスを@BeanでDIコンテナに登録
  * `DispatcherServlet#processHandlerException`で制御している
  * 適用順は以下
    * ExceptionHandlerExceptionResolver
      * Controllerとかで@ExceptionHandlerが付与されているメソッドの呼び出し
    * ResponseStatusExceptionResolver
      * @ResponseStatusが付与されたExceptionをハンドリング
    * DefaultHandlerExceptionResolver
      * Springで定義されている例外に適用するHandlerExceptionResolver
    * 独自HandlerExceptionResolver
  * 各Resolverは、Exceptionをキャッチしているわけではない。`DispacherServlet`でキャッチして、各Resolverに渡している
 * web.xmlに提議

 
 なお、デフォルトのエラーページはerror.htmlになるもよう
 ステータスコードに合わせたhtml、例えば404.htmlとかを用意すれば、ステータスコード毎のページに遷移できそう。
 
 なんか、ステータスコードとviewだけをマッピングして返却するだけの方法が徹底入門に記載されていたような。
 要確認