-- Job Types
-- DELETE FROM jobtypes;
INSERT INTO jobtypes (jt_id, jt_name) VALUES (1, 'executive');
INSERT INTO jobtypes (jt_id, jt_name) VALUES (2, 'development');
INSERT INTO jobtypes (jt_id, jt_name) VALUES (3, 'it support');
INSERT INTO jobtypes (jt_id, jt_name) VALUES (4, 'data and ai');
INSERT INTO jobtypes (jt_id, jt_name) VALUES (5, 'business');
INSERT INTO jobtypes (jt_id, jt_name) VALUES (6, 'hr');

-- Jobs
-- DELETE FROM jobs;
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (1, 'co-founder', 1, 1);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (2, 'chief executive officer', 1, 1);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (3, 'chief technology officer', 2, 1);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (4, 'chief product officer', 2, 1);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (5, 'engineering manager', 3, 2);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (6, 'technical lead', 3, 2);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (7, 'hr manager', 3, 5);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (8, 'ai/ml manager', 3, 3);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (9, 'business operations manager', 3, 4);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (10, 'senior fullstack developer', 4, 2);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (11, 'senior backend developer', 4, 2);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (12, 'senior frontend developer', 4, 2);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (13, 'senior devops engineer', 4, 3);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (14, 'senior cloud engineer', 4, 3);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (15, 'senior data scientist', 4, 4);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (16, 'senior machine learning engineer', 4, 4);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (17, 'senior business analyst', 4, 5);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (18, 'senior marketing specialist', 4, 5);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (19, 'hr specialist', 4, 6);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (20, 'junior fullstack developer', 5, 2);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (21, 'junior backend developer', 5, 2);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (22, 'junior frontend developer', 5, 2);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (23, 'junior devops engineer', 5, 3);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (24, 'junior cloud engineer', 5, 3);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (25, 'junior system administrator', 5, 3);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (26, 'junior data scientist', 5, 4);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (27, 'junior machine learning engineer', 5, 4);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (28, 'junior business analyst', 5, 5);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (29, 'junior marketing specialist', 5, 5);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (30, 'intern - development', 6, 2);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (31, 'intern - it support', 6, 3);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (32, 'intern - data and ai', 6, 4);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (33, 'intern - business', 6, 5);
INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (34, 'intern - hr', 6, 6);

-- Employees
-- ...existing code...
INSERT INTO employees (emp_id, emp_first_name, emp_last_name, emp_email, emp_password, emp_phone_number, emp_hire_date, emp_salary, emp_mng_id, emp_job_id) VALUES (1, 'Elon', 'Musk', 'elon.musk@example.com', 'encrypted_password_here', '123-456-7890', '2023-01-01', 1000000, NULL, 2);
