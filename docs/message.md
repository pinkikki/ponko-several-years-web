# messageメモ

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

### 疑問点

* Bean Validationのエラーメッセージを`MessageSource`から取得する場合、本PJのMessageConfigみたいな実装をするが、`WebMvcConfigurerAdapter`って継承しないでもよいのではないか（実際に継承しないで実施してもうまくいった）恐らく、getValidatorをoverrideするために、`WebMvcConfigurerAdapter`を継承しているのだが、SpringのDIコンテナにvalidatorという名称で入れておけば問題ないのではないかな。
  * `LocalValidatorFactoryBean`は`InitializingBean`を継承しているため、`AbstractAutowireCapableBeanFactory`のL1687で`LocalValidatorFactoryBean#afterPropertiesSet`が呼び出される。その中で、恐らくMessageResourceを使用する設定をしているため、getValidatorをoverriderしなくてもいいのかも。ちなみに、getValidatorだけ（`LocalValidatorFactoryBean`を@Beanで定義しない）でも`MessageResource`を使用できる。なお、getValidatorを呼び出しているのは、`WebMvcConfigurerComposite`のL165あたり