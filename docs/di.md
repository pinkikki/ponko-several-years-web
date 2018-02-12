## DIに関するメモ

### DIコンテナに登録している型が重複した場合

```java
@Autowired
Plugin plugin;
```

上記では`NoUniqueBeanDefinitionException`が発生してしまう。

`@Qualifier`を付与して、名前を特定する必要がある。

```java
@Autowired
@Qualifier("eventPlugin")
Plugin plugin;
```

```java
    // name属性を指定しない場合は、"xxxPlugin"になるが、これだとDIで疎結合したのに実装名が明示されているみたいな感じなので、Bean名を指定しておく
    @Bean(name = "eventPlugin")
    Plugin xxxPlugin() {
        return new XxxPlugin();
    }

    // @Primaryを付与する事で、@Qualifierがなくても優先される
    @Bean
    @Primary
    Plugin yyyPlugin() {
        return new YyyPlugin();
    }
```

一番良いのは、↓のようにアノテーションベースにする事かなぁ

```java
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier
public @interface EventPlugin {
}
```

```java
@Autowired
@EventPlugin
Plugin plugin;
```

### スコープ

デフォルトはシングルトン