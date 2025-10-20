```java
public class Basic {

	public static void main(String[] args) {
		
		
		
		//Concepts: filter(), collect()
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		List<Integer> evens = numbers.stream()
		    .filter(n -> n % 2 == 0)
		    .collect(Collectors.toList());
		System.out.println(evens); // [2, 4, 6]

		
		
		
		// Concepts: map(), method reference
		List<String> names = Arrays.asList("nikhil", "anand", "kini");
		List<String> upper = names.stream()
		    .map(String::toUpperCase)
		    .collect(Collectors.toList());
		System.out.println(upper); // [NIKHIL, ANAND, KINI]

		
		
		
		
		//Concepts: mapToInt(), primitive streams (IntStream)
		int sum = numbers.stream()
			    .mapToInt(Integer::intValue)
			    .sum();
		System.out.println(sum); // 21
		
		
		
		
		
		//Concepts: max(), Optional
		Optional<Integer> max = numbers.stream()
			    .max(Integer::compareTo);
		max.ifPresent(System.out::println); // 6
		
		
		
		
		
		//Concepts: filter(), count()
		List<String> name2 = Arrays.asList("Alice", "Bob", "Angela", "David");
		long count = name2.stream()
		    .filter(name -> name.startsWith("A"))
		    .count();
		System.out.println(count); // 2

		
		
		
		//Concepts: sorted()
		List<String> sorted = names.stream()
			    .sorted()
			    .collect(Collectors.toList());
		System.out.println(sorted); // [Alice, Angela, Bob, David]
		
		
		
		
		//Concepts: distinct()
		List<Integer> nums = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
		List<Integer> distinct = nums.stream()
		    .distinct()
		    .collect(Collectors.toList());
		System.out.println(distinct); // [1, 2, 3, 4, 5]

		
		
		

		//Concepts: findFirst()
		Optional<Integer> first = numbers.stream()
			    .filter(n -> n > 3)
			    .findFirst();
		first.ifPresent(System.out::println); // 4
		
		
		
		
		
		//Concepts: Collectors.groupingBy()
		List<String> words = Arrays.asList("one", "two", "three", "four");
		Map<Integer, List<String>> grouped = words.stream()
		    .collect(Collectors.groupingBy(String::length));
		System.out.println(grouped);
		// {3=[one, two], 4=[four], 5=[three]}
		
		
		
		
		//Concepts: average()
		OptionalDouble avg = numbers.stream()
			    .mapToInt(Integer::intValue)
			    .average();
			avg.ifPresent(System.out::println); // 3.5


			
			
		// Concepts: Collectors.joining()
		String result = names.stream()
			    .collect(Collectors.joining(", "));
		System.out.println(result); // Alice, Bob, Angela, David

		
		
		
		
		//Concepts: Collectors.partitioningBy()
		Map<Boolean, List<Integer>> partition = numbers.stream()
			    .collect(Collectors.partitioningBy(n -> n % 2 == 0));
		System.out.println(partition);
			// {false=[1, 3, 5], true=[2, 4, 6]}

			
		
		
		//Concepts: flatMap()
		List<List<Integer>> nested = Arrays.asList(
			    Arrays.asList(1, 2),
			    Arrays.asList(3, 4),
			    Arrays.asList(5)
			);
		List<Integer> flat = nested.stream()
			    .flatMap(List::stream)
			    .collect(Collectors.toList());
		System.out.println(flat); // [1, 2, 3, 4, 5]

		
		
		
		
		//Concepts: reduce()
		int product = numbers.stream()
			    .reduce(1, (a, b) -> a * b);
			System.out.println(product); // 720


	}

}
```


```java
public class StudentStreamExcersice {
	public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student(1, "Alice", "Math", 88),
            new Student(2, "Bob", "Math", 95),
            new Student(3, "Clara", "Math", 91),
            new Student(4, "David", "Science", 76),
            new Student(5, "Eve", "Science", 90),
            new Student(6, "Frank", "Science", 83),
            new Student(7, "Grace", "English", 82),
            new Student(8, "Hank", "English", 89),
            new Student(9, "Ivy", "English", 92)
        );
        
        // Convert List → Map
        Map<Integer, Student> studentIdMap = students.stream()
        		.collect(Collectors.toMap(
        				Student::getId,			// Key mapper
        				Function.identity()		// Value mapper (the Student itself)
        			));
        // Print Map
        studentIdMap.forEach((id, student) ->
        System.out.println(id + "->" + student)
        );
        // 1->Student [id=1, name=Alice, subject=Math, marks=88]
        
        
        
        
        
        
        //Use: Collectors.groupingBy()
        Map<String, List<Student>> grouped = students.stream()
        		.collect(Collectors.groupingBy(s -> {
        			if (s.getMarks() < 40)
        				return "Fail";
        			else if (s.getMarks() <= 70)
        				return "Pass";
        			else
        				return "Distinction";
        		}));
        // Print the results
        grouped.forEach((category, list) -> {
                System.out.println(category + ": " +
                    list.stream().map(Student::getName) // to map and get Name of student
                    .collect(Collectors.joining(", "))); // joining the list of Names
            }); 
       //Distinction: Alice, Bob, Clara, David, Eve, Frank, Grace, Hank, Ivy
        
        
        
        
        
        
        //➡ Use: Collectors.groupingBy(Function, Collectors.counting())
        Function<Student, String> gradeClassifier = s -> {
        	if (s.getId() >= 90) return "A";
        	else if (s.getMarks() >= 75) return "B";
        	else return "C";
        };
        // core logic
        Map<String, Long> gradeCount = students.stream()
        		.collect(Collectors.groupingBy(gradeClassifier,
        				Collectors.counting())); // count student
        //print
        gradeCount.forEach((grade, count) -> 
        System.out.println("Grade "+ grade + ": "+ count + " students"));
        //Grade B: 9 students
        
        
        
        
        
        //➡ Use: Collectors.groupingBy(), Collectors.maxBy()
        // Group by subject and find max marks per subject
        Map<String, Optional<Student>> topScores = students.stream()
        		.collect(Collectors.groupingBy(
        				Student::getSubject,
        				Collectors.maxBy(Comparator.comparingInt(Student::getMarks))
        				));
        //or
        Map<String, Student> topScorers2 = students.stream()
        		.collect(Collectors.groupingBy(
        				Student::getSubject,
        				Collectors.collectingAndThen(
        						Collectors.maxBy(Comparator.comparingInt(Student::getMarks)),
        						Optional::get)
        				
        				));
        //print
        topScores.forEach((sub, studOpt) -> studOpt.ifPresent(
        		s -> System.out.println(sub +" -> "+ s)));
        // English -> Student [id=9, name=Ivy, subject=English, marks=92]
        // Group by subject and get top 2 scorers in each
        Map<String, List<Student>> topTwoScorer = students.stream()
        		.collect(Collectors.groupingBy(
        				Student::getSubject,
        				Collectors.collectingAndThen(Collectors.toList(),
        						list -> list.stream().sorted(Comparator.comparingInt(Student::getMarks).reversed())
        						.limit(2)
        						.collect(Collectors.toList())
        						)
        				));
        //print
        topTwoScorer.forEach((sub, lis) -> {
        	System.out.println(sub +" -> "+ lis);
        });
        //Math -> [Student [id=2, name=Bob, subject=Math, marks=95], Student [id=3, name=Clara, subject=Math, marks=91]]
        
        
        
        
        
        
        //➡ Use: Collectors.groupingBy(Attendance::getStudentId, Collectors.mapping(Attendance::getDate, Collectors.toList()))
        // Group attendance by student ID → list of dates
        List<Attendance> records = Arrays.asList(
                new Attendance("S001", LocalDate.of(2025, 10, 1)),
                new Attendance("S001", LocalDate.of(2025, 10, 2)),
                new Attendance("S002", LocalDate.of(2025, 10, 1)),
                new Attendance("S003", LocalDate.of(2025, 10, 3)),
                new Attendance("S002", LocalDate.of(2025, 10, 4))
            );
        
        Map<String, List<LocalDate>> attendenceMap = records.stream()
        		.collect(Collectors.groupingBy(Attendance::getStudentId,
        				Collectors.mapping(Attendance::getDate,
        						Collectors.toList())
        				));
        //print
        attendenceMap.forEach((id, dates) ->
        			System.out.println(id + " -> "+ dates)
        		);
        //S001 -> [2025-10-01, 2025-10-02]
        
        
        
        
        
        
        
        //Compute average marks of students per subject. 
        //➡ Use: Collectors.groupingBy(Student::getSubject, Collectors.averagingInt(Student::getMarks))
        Map<String, Double> avgMarks = students.stream()
        		.collect(Collectors.groupingBy(
        				Student::getSubject,
        				Collectors.averagingInt(Student::getMarks)
        				));
        //print
        avgMarks.forEach((sub, avg) -> System.out.printf("%s -> %.2f%n", sub, avg));
        //English -> 87.67
        
        //adv Use Collectors.summarizingInt()
        Map<String, IntSummaryStatistics> stats = students.stream()
        	    .collect(Collectors.groupingBy(
        	        Student::getSubject,
        	        Collectors.summarizingInt(Student::getMarks)
        	    ));

        	stats.forEach((subject, summary) ->
        	    System.out.printf("%s → Avg: %.2f, Max: %d, Min: %d, Count: %d%n",
        	        subject, summary.getAverage(), summary.getMax(),
        	        summary.getMin(), summary.getCount())
        	);
        	// English → Avg: 87.67, Max: 92, Min: 82, Count: 3
        
        
        
        	
        	
        	
        	
        // given List<Student>, determine which subject has the most students. 
        //➡ Use: Collectors.groupingBy(Student::getSubject, Collectors.counting()), then use max().
        // Step 1: Count number of students per subject
        Map<String, Long> subjectCounts = students.stream()
        		.collect(Collectors.groupingBy(
        				Student::getSubject,
        				Collectors.counting()
        				));
        // Step 2: Find subject with max count
        Optional<Map.Entry<String, Long>> maxEntry = subjectCounts.entrySet().stream()
        		.max(Map.Entry.comparingByValue());
        //print
        maxEntry.ifPresent(entry -> System.out.println("Subject with most students: " +
                entry.getKey() + " (" + entry.getValue() + " students)"));
        //Subject with most students: English (3 students)
        //or
        students.stream()
        .collect(Collectors.groupingBy(Student::getSubject, Collectors.counting()))
        		.entrySet().stream()
        		.max(Map.Entry.comparingByValue())
        		.ifPresent(e -> System.out.println(e.getKey()+" -> "+ e.getValue()));
        //English -> 3
        
        
        
        
        
        
        
        
        //Convert List<Student> into Map<StudentId, Student> sorted by marks (descending). 
        //➡ Use: Collectors.toMap() + LinkedHashMap
        Map<Integer, Student> sortedMap = students.stream()
        		.sorted(Comparator.comparingInt(Student::getMarks).reversed()
        				.thenComparing(Student::getName))   // sort by marks and then by name
        		.collect(Collectors.toMap(
        				Student::getId,		 				// key
        				Function.identity(),				// value
        				(a, b) -> a,		// merge function (in case of duplicate IDs)
        				LinkedHashMap::new  // preserve insertion order
        				));
        //print
        sortedMap.forEach((id, student) -> System.out.println(id + "->" + student));
        //2->Student [id=2, name=Bob, subject=Math, marks=95]
        
       
        
        
        
        
        
        //Partition students into two groups: Pass (marks ≥ 40),Fail (marks < 40)
        //➡ Use: Collectors.partitioningBy()
        Map<Boolean, List<Student>> partitioned = students.stream()
        		.collect(Collectors.partitioningBy(s -> s.getMarks() >= 40));
        //print
        System.out.println("Pass ->");
        partitioned.get(true).forEach(System.out::println);
        System.out.println("Fail ->");
        partitioned.get(false).forEach(System.out::println);
        // Pass ->
        // Student [id=1, name=Alice, subject=Math, marks=88]
        
        
        
        
        
        
        
        //Find the date when most students attended.
        //➡ Use: Collectors.groupingBy(Attendance::getDate, Collectors.counting()), then find max.
        Map<LocalDate, Long> dateCounts = records.stream()
        		.collect(Collectors.groupingBy(Attendance::getDate, Collectors.counting()
        				));
        Optional<Map.Entry<LocalDate, Long>> maxEntry2 = dateCounts.entrySet().stream()
        		.max(Map.Entry.comparingByValue());
        
        maxEntry2.ifPresent(ent -> System.out.println("Most frequent attendance date: " +
                ent.getKey() + " (" + ent.getValue() + " students)"));
        //Most frequent attendance date: 2025-10-01 (2 students)
        
        
        
    }
	
	
}

class Student {
	private Integer id;
    private String name;
    private String subject;
    private int marks;

    public Student(Integer id, String name, String subject, int marks) {
    	this.id = id;
        this.name = name;
        this.subject = subject;
        this.marks = marks;
    }
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public int getMarks() {
        return marks;
    }

    @Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", subject=" + subject + ", marks=" + marks + "]";
	}
}

class Attendance {
    public Attendance(String studentId, LocalDate date) {
		super();
		this.studentId = studentId;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Attendance [studentId=" + studentId + ", date=" + date + "]";
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	private String studentId;
    private LocalDate date;
}
```