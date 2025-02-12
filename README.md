********************************Student Management System (Spring Boot) 📚************b********************************

                                                 
****Overview****
The Student Management System is a RESTful API built using Spring Boot that streamlines student and course management. It enables administrators to admit students, manage courses, and assign students to courses, while students can update their profiles, search for assigned courses, and leave courses.

****Features****
****Admin Operations****
✅ Admit Students – Add students with details (name, DOB, gender, unique student code) and multiple addresses (permanent, correspondence, current).
✅ Manage Courses – Upload course details including name, description, type, duration, and topics.
✅ Assign Courses – Enroll students in multiple courses.
✅ Search Students – Retrieve students by name.
✅ View Course Enrollments – Get a list of students assigned to a specific course.

****Student Operations****
✅ Update Profile – Modify email, mobile number, parents’ names, and addresses.
✅ Search Courses – Find assigned courses and topics.
✅ Leave a Course – Unenroll from a course.

****Authentication & Security****
🔐 Admin Login – Admins authenticate via credentials using Postman.
🔐 Student Login – Students authenticate using their unique student code and date of birth.

****Tech Stack****
Spring Boot – Backend framework
Spring Data JPA & Hibernate – ORM & database handling
MySQL/PostgreSQL – Relational database
Swagger – API documentation
JUnit & Mockito – Unit testing
DTO & Model Mapping – Clean API response structure
