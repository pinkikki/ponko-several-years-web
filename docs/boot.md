## spring bootのメモ

### Auto Cnofiguration

`spring-boot-autoconfigure`内の、
`META-INF/spring.factories`に、auto-configurationするクラス群が記載されている。

上記からauto-configuration対象を除外するには、以下のように設定する模様。

```java
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBootDemoApplication {
    // ...
}
```

もしくは、`application.properties`に以下のような設定を追加する

```properties
spring.autoconfigure.exclude=...
```

ちなみに、`spring-autoconfigure-metadata.properties`には、auto-configurationする条件等がまとめられている。

その他にも色々な`@Profile`や`@Conditional`等の説明として、以下を参考にした

＜参考サイト＞
https://qiita.com/kazuki43zoo/items/8645d9765edd11c6f1dd