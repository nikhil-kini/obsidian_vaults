> Note: `@ManyToOne ( fetch=LAZY )` fetch profile in joins in entity defines how much query is run to get the requested data by API. Generally N+1 queries are run on LAZY profile, but can be made to run single query ( fetch=EAGER ) or using JPQL HQL config or QueryDSL library

> "now" is generally termed eager or immediate. "later" is generally termed lazy or delayed.

**NOTE: Don't use `@Data` The `toSting() `ans `hashCode() `method will include the relationship table entities which will cause stack overflow error due to recursive relationships between table. so use**
```java
@Entity
@Setter
@Getter
@ToString(exclude = {"user", "comments", "likes"} )
@EqualsAndHashCode(exclude = {"user", "comments", "likes"} )
public class Post {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	
	private String content;
	private String mediaUrl;
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne ( fetch=LAZY ) @JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Like> likes = new ArrayList<>();
	
}
```



Example 1:

![[Pasted image 20251027144951.png]]
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
}

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer stock;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();
}


@Entity
@Table(name = "`Order`") // because 'Order' is a reserved SQL keyword
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();
}


@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

```


Example 2:
![[Pasted image 20251027145455.png]]

```java
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;
    private String email;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();
}

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Double balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();
}

public enum AccountType {
    SAVINGS,
    CURRENT
}

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Double amount;
    
    @CreationTimestamp // Can also be auto generated in SQL when setting mig script CURRENT_TIMESTAMP
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}

public enum TransactionType {
    CREDIT,
    DEBIT
}

```

Example 3:

College Management Entities: 
Student 
Course
Professor 
Enrollment (joining table with grade, semester) 

Relationships: 
Student ↔ Course (M:N via Enrollment) 
Course → Professor (M:1)
![[Pasted image 20251028160117.png]]

```java

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professorId;

    private String name;
    private String email;
    private String department;

    @OneToMany(mappedBy = "professor")
    private List<Course> courses = new ArrayList<>();
}

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;
    private Integer credits;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments = new ArrayList<>();
}


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;
    private String email;
    private LocalDate dob;
    private Integer enrollmentYear;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments = new ArrayList<>();
}


//// Note ManyToMany relationship through tabel Enrollment, @ManyToOne + @ManyToOne is used to achive this

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String semester;
    private String grade;
}


```


Example 4:
Social Media Platform

Entities:

User
Post
Comment
Like
Follow

Relationships:

User → Post (1:N)
Post → Comment (1:N)
Post → Like (1:N)
User ↔ User (M:N for Follow)

![[Pasted image 20251027184449.png]]

```java
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String email;
    private String bio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "Follow",
        joinColumns = @JoinColumn(name = "follower_id"),
        inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<User> following = new HashSet<>();

    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>();
}

@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String content;
    private String mediaUrl;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();
}

@Entity
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String text;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne @JoinColumn(name = "parentComment_id")
    private Comment parentComment;
}

@Entity
public class Like {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne @JoinColumn(name = "post_id")
    private Post post;

    private LocalDateTime createdAt = LocalDateTime.now();
}


```

equivalent table will be created
```sql
CREATE TABLE User (
    userId BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    bio TEXT,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Post (
    postId BIGINT PRIMARY KEY AUTO_INCREMENT,
    content TEXT,
    mediaUrl VARCHAR(255),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES User(userId)
);

CREATE TABLE Comment (
    commentId BIGINT PRIMARY KEY AUTO_INCREMENT,
    text TEXT,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    post_id BIGINT,
    user_id BIGINT,
    parentComment_id BIGINT,
    FOREIGN KEY (post_id) REFERENCES Post(postId),
    FOREIGN KEY (user_id) REFERENCES User(userId),
    FOREIGN KEY (parentComment_id) REFERENCES Comment(commentId)
);

CREATE TABLE Likes (
    likeId BIGINT PRIMARY KEY AUTO_INCREMENT,
    post_id BIGINT,
    user_id BIGINT,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES Post(postId),
    FOREIGN KEY (user_id) REFERENCES User(userId)
);

%% NOTE the tabel is added by the JPA using @JoinTable %%
CREATE TABLE Follow (
    follower_id BIGINT,
    following_id BIGINT,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (follower_id, following_id),
    FOREIGN KEY (follower_id) REFERENCES User(userId),
    FOREIGN KEY (following_id) REFERENCES User(userId)
);
```


NOTE: 
If you expose this entity through REST (like using Spring Boot), serializing it will cause infinite loops because:

- `followers` refers to `following`, which refers to `followers`, etc.

✅ **Fix:** Use Jackson annotations:
```java
@JsonIgnore 
private Set<User> followers;
```
or use DTOs for response mapping.


## Optional Performance Tip

For very large social networks:

- The `follow` table can have millions of records.
- You can model `Follow` as a **separate entity** instead of `@ManyToMany` for better control:

```java
@Entity
public class Follow {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;

    private LocalDateTime followedAt = LocalDateTime.now();
}

```


