# jpaメモ

### SPRING DATA JPA

### Repositoryが呼ばれる仕組み

springがDIコンテナを作成時に、XXXRepositoryを継承したproxyクラスを作成して、それをコンテナに登録する。
アプリが呼び出す際は、このproxyクラスがDIされている。
`ProxyFactory`が、proxyクラスを作成しているクラス

### EntityManagerの生存期間

基本的には、トランザクションと同様。
但し、Controllerやjsp等でLazy Fetchを使用するために、`OpenEntityManagerInViewInterceptor`を使用している場合は、
OpenEntityManagerInViewInterceptorの終わりが生存期間となる。

<参考＞
https://terasolunaorg.github.io/guideline/public_review/ArchitectureInDetail/DataAccessJpa.html

#### JOIN FETCH

* JOIN FETCHに指定された関連Entityは、Query実行後にロードされる。
* JOIN FETCHに指定されなかった関連Entityは、関連付アノテーション（@OneTOMany等）のfetch属性に指定されている値（LAZY / EAGER）に準ずる

#### 動的検索条件

項目がブランクの場合は検索条件に含めない等は、Specificationを利用する？
基本的には項目ごとに作成する気もするけど、共通的に一個作成してしまえばよいかな


#### INSERTの注意

存在しているレコードの主キーを指定して#saveを呼び出すと、update文になる

### 疑問点

* 大量データの検索時に、全部メモリ展開しない仕組みはないのかな。
  * Streamで取得すればよいのか

* EntityManager#clearを呼び出さないと、メモリにどんどんEntityが溜まるような…
＜参考＞https://terasolunaorg.github.io/guideline/public_review/ArchitectureInDetail/DataAccessJpa.html#data-access-jpa-howtouse-querymethod-modifying

* 検索項目がブランクの場合は、検索条件から省きたい場合ｍ、Specificationが妥当？
  * findOne / findAll / countしかSpecificationは指定できないのか？