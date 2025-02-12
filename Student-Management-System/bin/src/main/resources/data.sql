-- Insert courses
INSERT INTO Course (id, course_name, description, course_type, duration, version) VALUES
                                                                                      (1, 'Java Basics', 'Introduction to Java', 'Programming', 30, 0),
                                                                                      (2, 'Spring Boot', 'Spring Boot Framework', 'Framework', 40, 0);

-- Insert sample students
INSERT INTO Student (id, name, date_of_birth, gender, unique_student_code, email, mobile_number, parents_name, version) VALUES
                                                                                                                            (1, 'John Doe', '2000-05-15', 'Male', 'STU1001', 'john.doe@example.com', '9876543210', 'Mr. & Mrs. Doe', 0),
                                                                                                                            (2, 'Jane Smith', '1999-11-22', 'Female', 'STU1002', 'jane.smith@example.com', '9876543211', 'Mr. & Mrs. Smith', 0);




