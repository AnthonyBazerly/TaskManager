-- Job Types
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM jobtypes) THEN
    INSERT INTO jobtypes (jt_id, jt_name) VALUES (1, 'Executive');
    INSERT INTO jobtypes (jt_id, jt_name) VALUES (2, 'Development');
    INSERT INTO jobtypes (jt_id, jt_name) VALUES (3, 'IT Support');
    INSERT INTO jobtypes (jt_id, jt_name) VALUES (4, 'Data and AI');
    INSERT INTO jobtypes (jt_id, jt_name) VALUES (5, 'Business');
    INSERT INTO jobtypes (jt_id, jt_name) VALUES (6, 'HR');
  END IF;
END
$$;

-- Jobs
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM jobs) THEN
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (1, 'Co-Founder', 1, 1);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (2, 'Chief Executive Officer (CEO)', 1, 1);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (3, 'Chief Technology Officer (CTO)', 2, 1);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (4, 'Chief Product Officer (CPO)', 2, 1);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (5, 'Engineering Manager', 3, 2);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (6, 'Technical Lead', 3, 2);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (7, 'HR Manager', 3, 5);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (8, 'AI/ML Manager', 3, 3);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (9, 'Business Operations Manager', 3, 4);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (10, 'Senior Fullstack Developer', 4, 2);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (11, 'Senior Backend Developer', 4, 2);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (12, 'Senior Frontend Developer', 4, 2);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (13, 'Senior DevOps Engineer', 4, 3);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (14, 'Senior Cloud Engineer', 4, 3);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (15, 'Senior Data Scientist', 4, 4);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (16, 'Senior Machine Learning Engineer', 4, 4);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (17, 'Senior Business Analyst', 4, 5);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (18, 'Senior Marketing Specialist', 4, 5);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (19, 'HR Specialist', 4, 6);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (20, 'Junior Fullstack Developer', 5, 2);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (21, 'Junior Backend Developer', 5, 2);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (22, 'Junior Frontend Developer', 5, 2);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (23, 'Junior DevOps Engineer', 5, 3);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (24, 'Junior Cloud Engineer', 5, 3);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (25, 'Junior System Administrator', 5, 3);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (26, 'Junior Data Scientist', 5, 4);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (27, 'Junior Machine Learning Engineer', 5, 4);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (28, 'Junior Business Analyst', 5, 5);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (29, 'Junior Marketing Specialist', 5, 5);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (30, 'Intern - Development', 6, 2);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (31, 'Intern - IT Support', 6, 3);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (32, 'Intern - Data and AI', 6, 4);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (33, 'Intern - Business', 6, 5);
    INSERT INTO jobs (job_id, job_name, job_rank, job_jt_id) VALUES (34, 'Intern - HR', 6, 6);
  END IF;
END
$$;