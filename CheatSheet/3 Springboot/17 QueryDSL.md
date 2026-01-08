[refer official doc for ref.](http://querydsl.com/static/querydsl/5.0.0/reference/html_single/)


Parent interface repo

BaseRepo -> AuthorRepo
|-> BookRepo
```java
@NoRepositoryBean

public interface BaseRepo<T, ID> extends JpaRepository<T, ID>{

T findByIdMandatory(ID id) throws IllegalArgumentException;

}
```

```java
public interface AuthorRepo extends BaseRepo<Author, Integer>{

public Optional<Author> findAuthorByEmail(String email);
public List<AuthorStatistic> getAuthorStatistics();
public List<Author> getAuthors();

}
```

```java
public interface BookRepo extends BaseRepo<Book, Integer> {

}
```


BaseRepoImpl -> AuthorRepoImpl (impl AuthorRepo)
|-> BookRepoImpl (impl BookRepo)

```java
public abstract class BaseRepoImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepo<T, ID>{
	
	EntityManager em;
	JPAQueryFactory jpaQueryFactory;
	
	protected final QAuthor author = QAuthor.author;
	protected final QBook book = QBook.book;

	public BaseRepoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
		this.jpaQueryFactory = new JPAQueryFactory(em);
	}
	
	@Override
    public T findByIdMandatory(ID id) throws IllegalArgumentException {
        return findById(id)
                .orElseThrow(()->new IllegalArgumentException("entity not found with id "+id));
    }



}public abstract class BaseRepoImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepo<T, ID>{
	
	EntityManager em;
	JPAQueryFactory jpaQueryFactory;
	
	protected final QAuthor author = QAuthor.author;
	protected final QBook book = QBook.book;

	public BaseRepoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
		this.jpaQueryFactory = new JPAQueryFactory(em);
	}
	
	@Override
    public T findByIdMandatory(ID id) throws IllegalArgumentException {
        return findById(id)
                .orElseThrow(()->new IllegalArgumentException("entity not found with id "+id));
    }

}
```

```java
public class AuthorRepoImpl extends BaseRepoImpl<Author, Integer> implements AuthorRepo {

	public AuthorRepoImpl(EntityManager em) {
		super(Author.class, em);
	}


	@Override
	public Optional<Author> findAuthorByEmail(String email) {
		return Optional.ofNullable(jpaQueryFactory.select(author)
				.from(author)
				.where(author.email.equalsIgnoreCase(email))
				.fetchFirst());
	}

	@Override
	public List<AuthorStatistic> getAuthorStatistics() {
		return jpaQueryFactory
				.from(author)
				.innerJoin(author.books, book)
				.groupBy(author.name)
				.select(Projections.constructor(AuthorStatistic.class,
						author.name, book.count())).fetch();
	}

	@Override
	public List<Author> getAuthors() {
		return jpaQueryFactory
				.select(author)
				.distinct()
				.from(author)
				.innerJoin(author.books, book)
				.fetchJoin().fetch();
	}

}

```

```java
public class BookRepoImpl extends BaseRepoImpl<Book, Integer> implements BookRepo {

	public BookRepoImpl(EntityManager em) {
		super(Book.class, em);
		// TODO Auto-generated constructor stub
	}
}
```

Use QuerlyDSL method along with JPA methods in service layer.