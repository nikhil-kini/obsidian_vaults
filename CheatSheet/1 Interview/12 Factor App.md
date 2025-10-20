
## [I. Codebase](https://12factor.net/codebase)

### One codebase tracked in revision control, many deploys

![[Pasted image 20250804151431.png]]
There is always a one-to-one correlation between the codebase and the app:

- If there are multiple codebases, it’s not an app – it’s a distributed system. Each component in a distributed system is an app, and each can individually comply with twelve-factor.
- Multiple apps sharing the same code is a violation of twelve-factor. The solution here is to factor shared code into libraries which can be included through the [dependency manager](https://12factor.net/dependencies).

GIT

## [II. Dependencies](https://12factor.net/dependencies)

### Explicitly declare and isolate dependencies

**A twelve-factor app never relies on implicit existence of system-wide packages.** It declares all dependencies, completely and exactly, via a _dependency declaration_ manifest. Furthermore, it uses a _dependency isolation_ tool during execution to ensure that no implicit dependencies “leak in” from the surrounding system. The full and explicit dependency specification is applied uniformly to both production and development.

Maven (pom.xml)
## [III. Config](https://12factor.net/config)

### Store config in the environment

**The twelve-factor app stores config in _environment variables_** (often shortened to _env vars_ or _env_). Env vars are easy to change between deploys without changing any code; unlike config files, there is little chance of them being checked into the code repo accidentally; and unlike custom config files, or other config mechanisms such as Java System Properties, they are a language- and OS-agnostic standard.


## [IV. Backing services](https://12factor.net/backing-services)

### Treat backing services as attached resources

**The code for a twelve-factor app makes no distinction between local and third party services.** To the app, both are attached resources, accessed via a URL or other locator/credentials stored in the [config](https://12factor.net/config). A [deploy](https://12factor.net/codebase) of the twelve-factor app should be able to swap out a local MySQL database with one managed by a third party (such as [Amazon RDS](http://aws.amazon.com/rds/)) without any changes to the app’s code. Likewise, a local SMTP server could be swapped with a third-party SMTP service (such as Postmark) without code changes. In both cases, only the resource handle in the config needs to change.
![[Pasted image 20250804152801.png]]

## [V. Build, release, run](https://12factor.net/build-release-run)

### Strictly separate build and run stages
A [codebase](https://12factor.net/codebase) is transformed into a (non-development) deploy through three stages:

- The _build stage_ is a transform which converts a code repo into an executable bundle known as a _build_. Using a version of the code at a commit specified by the deployment process, the build stage fetches vendors [dependencies](https://12factor.net/dependencies) and compiles binaries and assets.
- The _release stage_ takes the build produced by the build stage and combines it with the deploy’s current [config](https://12factor.net/config). The resulting _release_ contains both the build and the config and is ready for immediate execution in the execution environment.
- The _run stage_ (also known as “runtime”) runs the app in the execution environment, by launching some set of the app’s [processes](https://12factor.net/processes) against a selected release.

**The twelve-factor app uses strict separation between the build, release, and run stages.** For example, it is impossible to make changes to the code at runtime, since there is no way to propagate those changes back to the build stage.

Deployment tools typically offer release management tools, most notably the ability to roll back to a previous release. For example, the [Capistrano](https://github.com/capistrano/capistrano/wiki) deployment tool stores releases in a subdirectory named `releases`, where the current release is a symlink to the current release directory. Its `rollback` command makes it easy to quickly roll back to a previous release.
![[Pasted image 20250804153441.png]]

## [VI. Processes](https://12factor.net/processes)

### Execute the app as one or more stateless processes

**Twelve-factor processes are stateless and [share-nothing](http://en.wikipedia.org/wiki/Shared_nothing_architecture).** Any data that needs to persist must be stored in a stateful [backing service](https://12factor.net/backing-services), typically a database.

The twelve-factor app never assumes that anything cached in memory or on disk will be available on a future request or job – with many processes of each type running, chances are high that a future request will be served by a different process.

Some web systems rely on [“sticky sessions”](http://en.wikipedia.org/wiki/Load_balancing_%28computing%29#Persistence) – that is, caching user session data in memory of the app’s process and expecting future requests from the same visitor to be routed to the same process. Sticky sessions are a violation of twelve-factor and should never be used or relied upon. Session state data is a good candidate for a datastore that offers time-expiration, such as [Memcached](http://memcached.org/) or [Redis](http://redis.io/).

## [VII. Port binding](https://12factor.net/port-binding)

### Export services via port binding

**The twelve-factor app is completely self-contained** and does not rely on runtime injection of a webserver into the execution environment to create a web-facing service. The web app **exports HTTP as a service by binding to a port**, and listening to requests coming in on that port.

Note also that the port-binding approach means that one app can become the [backing service](https://12factor.net/backing-services) for another app, by providing the URL to the backing app as a resource handle in the [config](https://12factor.net/config) for the consuming app.

## [VIII. Concurrency](https://12factor.net/concurrency)

### Scale out via the process model

**In the twelve-factor app, processes are a first class citizen.** Processes in the twelve-factor app take strong cues from [the unix process model for running service daemons](https://adam.herokuapp.com/past/2011/5/9/applying_the_unix_process_model_to_web_apps/). Using this model, the developer can architect their app to handle diverse workloads by assigning each type of work to a _process type_. For example, HTTP requests may be handled by a web process, and long-running background tasks handled by a worker process.

This does not exclude individual processes from handling their own internal multiplexing, via threads inside the runtime VM, or the async/evented model found in tools such as [EventMachine](https://github.com/eventmachine/eventmachine), [Twisted](http://twistedmatrix.com/trac/), or [Node.js](http://nodejs.org/). But an individual VM can only grow so large (vertical scale), so the application must also be able to span multiple processes running on multiple physical machines.

The process model truly shines when it comes time to scale out. The [share-nothing, horizontally partitionable nature of twelve-factor app processes](https://12factor.net/processes) means that adding more concurrency is a simple and reliable operation. The array of process types and number of processes of each type is known as the _process formation_.

Twelve-factor app processes [should never daemonize](http://dustin.github.com/2010/02/28/running-processes.html) or write PID files. Instead, rely on the operating system’s process manager (such as [systemd](https://www.freedesktop.org/wiki/Software/systemd/), a distributed process manager on a cloud platform, or a tool like [Foreman](http://blog.daviddollar.org/2011/05/06/introducing-foreman.html) in development) to manage [output streams](https://12factor.net/logs), respond to crashed processes, and handle user-initiated restarts and shutdowns.
![[Pasted image 20250804155403.png]]

## [IX. Disposability](https://12factor.net/disposability)

### Maximize robustness with fast startup and graceful shutdown

**The twelve-factor app’s [processes](https://12factor.net/processes) are _disposable_, meaning they can be started or stopped at a moment’s notice.** This facilitates fast elastic scaling, rapid deployment of [code](https://12factor.net/codebase) or [config](https://12factor.net/config) changes, and robustness of production deploys.

Processes should strive to **minimize startup time**. Short startup time provides more agility for the [release](https://12factor.net/build-release-run) process and scaling up; and it aids robustness, because the process manager can more easily move processes to new physical machines when warranted.

Processes **shut down gracefully when they receive a [SIGTERM](http://en.wikipedia.org/wiki/SIGTERM)** signal from the process manager. For a web process, graceful shutdown is achieved by ceasing to listen on the service port (thereby refusing any new requests), allowing any current requests to finish, and then exiting.

For a worker process, graceful shutdown is achieved by returning the current job to the work queue. For example, on [RabbitMQ](http://www.rabbitmq.com/) the worker can send a [`NACK`](http://www.rabbitmq.com/amqp-0-9-1-quickref.html#basic.nack); on [Beanstalkd](https://beanstalkd.github.io), the job is returned to the queue automatically whenever a worker disconnects. Lock-based systems such as [Delayed Job](https://github.com/collectiveidea/delayed_job#readme) need to be sure to release their lock on the job record.

Processes should also be **robust against sudden death**, in the case of a failure in the underlying hardware.


## [X. Dev/prod parity](https://12factor.net/dev-prod-parity)

### Keep development, staging, and production as similar as possible

- **The time gap**: A developer may work on code that takes days, weeks, or even months to go into production.
- **The personnel gap**: Developers write code, ops engineers deploy it.
- **The tools gap**: Developers may be using a stack like Nginx, SQLite, and OS X, while the production deploy uses Apache, MySQL, and Linux.

**The twelve-factor app is designed for [continuous deployment](http://avc.com/2011/02/continuous-deployment/) by keeping the gap between development and production small.** Looking at the three gaps described above:

- Make the time gap small: a developer may write code and have it deployed hours or even just minutes later.
- Make the personnel gap small: developers who wrote code are closely involved in deploying it and watching its behavior in production.
- Make the tools gap small: keep development and production as similar as possible.

Summarizing the above into a table:

|Traditional app|Twelve-factor app|
|---|---|---|
|Time between deploys|Weeks|Hours|
|Code authors vs code deployers|Different people|Same people|
|Dev vs production environments|Divergent|As similar as possible|
**The twelve-factor developer resists the urge to use different backing services between development and production**, even when adapters theoretically abstract away any differences in backing services. Differences between backing services mean that tiny incompatibilities crop up, causing code that worked and passed tests in development or staging to fail in production.

Adapters to different backing services are still useful, because they make porting to new backing services relatively painless. But all deploys of the app (developer environments, staging, production) should be using the same type and version of each of the backing services.

## [XI. Logs](https://12factor.net/logs)

### Treat logs as event streams

**A twelve-factor app never concerns itself with routing or storage of its output stream.** It should not attempt to write to or manage logfiles. Instead, each running process writes its event stream, unbuffered, to `stdout`. During local development, the developer will view this stream in the foreground of their terminal to observe the app’s behavior.

The event stream for an app can be routed to a file, or watched via realtime tail in a terminal. Most significantly, the stream can be sent to a log indexing and analysis system such as [Splunk](http://www.splunk.com/), or a general-purpose data warehousing system such as [Hadoop/Hive](http://hive.apache.org/). These systems allow for great power and flexibility for introspecting an app’s behavior over time, including:

- Finding specific events in the past.
- Large-scale graphing of trends (such as requests per minute).
- Active alerting according to user-defined heuristics (such as an alert when the quantity of errors per minute exceeds a certain threshold).

## [XII. Admin processes](https://12factor.net/admin-processes)

### Run admin/management tasks as one-off processes

The [process formation](https://12factor.net/concurrency) is the array of processes that are used to do the app’s regular business (such as handling web requests) as it runs. Separately, developers will often wish to do one-off administrative or maintenance tasks for the app, such as:

- Running database migrations (e.g. `manage.py migrate` in Django, `rake db:migrate` in Rails).
- Running a console (also known as a [REPL](http://en.wikipedia.org/wiki/Read-eval-print_loop) shell) to run arbitrary code or inspect the app’s models against the live database. Most languages provide a REPL by running the interpreter without any arguments (e.g. `python` or `perl`) or in some cases have a separate command (e.g. `irb` for Ruby, `rails console` for Rails).
- Running one-time scripts committed into the app’s repo (e.g. `php scripts/fix_bad_records.php`).

One-off admin processes should be run in an identical environment as the regular [long-running processes](https://12factor.net/processes) of the app. They run against a [release](https://12factor.net/build-release-run), using the same [codebase](https://12factor.net/codebase) and [config](https://12factor.net/config) as any process run against that release. Admin code must ship with application code to avoid synchronization issues.

The same [dependency isolation](https://12factor.net/dependencies) techniques should be used on all process types. For example, if the Ruby web process uses the command `bundle exec thin start`, then a database migration should use `bundle exec rake db:migrate`. Likewise, a Python program using Virtualenv should use the vendored `bin/python` for running both the Tornado webserver and any `manage.py` admin processes.

Twelve-factor strongly favors languages which provide a REPL shell out of the box, and which make it easy to run one-off scripts. In a local deploy, developers invoke one-off admin processes by a direct shell command inside the app’s checkout directory. In a production deploy, developers can use ssh or other remote command execution mechanism provided by that deploy’s execution environment to run such a process.

JShell, the Java REPL introduced in Java 9, can be used to interact with Spring Boot applications. You can start your Spring Boot application and then use JShell to access and manipulate its beans and components. This allows for live testing of individual methods or components within the running application.

Java

```java
    // Example of using JShell to interact with a Spring Boot application    // Assuming your Spring Boot app is running and has a bean named 'myService'
    
    jshell> /import com.example.MyService    
	jshell> MyService myService = SpringApplication.run(MyApplication.class).getBean(MyService.class);    
	jshell> myService.doSomething();
```
	