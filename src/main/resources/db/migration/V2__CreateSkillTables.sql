CREATE TABLE skill_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE skill (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category_id INTEGER
);

ALTER TABLE skill
ADD CONSTRAINT fk_skill_skcat
FOREIGN KEY (category_id) REFERENCES skill_category(id);

CREATE TABLE employee_skill (
    employee_id INTEGER REFERENCES employee(id) ON DELETE CASCADE,
    skill_id INTEGER REFERENCES skill(id) ON DELETE CASCADE,
    proficiency VARCHAR(16) NOT NULL,
    is_primary BOOLEAN NOT NULL,
    PRIMARY KEY (employee_id, skill_id)
);
