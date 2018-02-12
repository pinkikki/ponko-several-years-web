# several-years-web

## Memo

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

### message.propertiesの配置場所を変更する場合

application.propertiesに以下を追加
```properties
spring.messages.basename=i18n/messages
spring.messages.cache-seconds=-1
spring.messages.encoding=UTF-8
```

JavaConfigでやる場合
```java
@Bean
ReloadableResourceBundleMessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasenames("classpath:i18n/messages"); //（※２）
    messageSource.setCacheSeconds(0);
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
}
```

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
  * Streamで取得すればよいのでは？

* EntityManager#clearを呼び出さないと、メモリにどんどんEntityが溜まるような…
＜参考＞https://terasolunaorg.github.io/guideline/public_review/ArchitectureInDetail/DataAccessJpa.html#data-access-jpa-howtouse-querymethod-modifying

* 検索項目がブランクの場合は、検索条件から省きたい場合ｍ、Specificationが妥当？
  * findOne / findAll / countしかSpecificationは指定できないのか？

* Bean Validationのエラーメッセージを`MessageSource`から取得する場合、本PJのMessageConfigみたいな実装をするが、`WebMvcConfigurerAdapter`って継承しないでもよいのではないか（実際に継承しないで実施してもうまくいった）恐らく、getValidatorをoverrideするために、`WebMvcConfigurerAdapter`を継承しているのだが、SpringのDIコンテナにvalidatorという名称で入れておけば問題ないのではないかな。
  * `LocalValidatorFactoryBean`は`InitializingBean`を継承しているため、`AbstractAutowireCapableBeanFactory`のL1687で`LocalValidatorFactoryBean#afterPropertiesSet`が呼び出される。その中で、恐らくMessageResourceを使用する設定をしているため、getValidatorをoverriderしなくてもいいのかも。ちなみに、getValidatorだけ（`LocalValidatorFactoryBean`を@Beanで定義しない）でも`MessageResource`を使用できる。なお、getValidatorを呼び出しているのは、`WebMvcConfigurerComposite`のL165あたり