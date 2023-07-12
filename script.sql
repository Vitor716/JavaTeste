CREATE DATABASE Teste

/* Criação das tabelas */
CREATE TABLE IF NOT EXISTS person(
	person_id serial NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	department_id int NULL,
	FOREIGN KEY (department_id) REFERENCES department(department_id)
);

CREATE TABLE IF NOT EXISTS department(
	department_id serial NOT NULL PRIMARY KEY,
	title VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS task(
	task_id serial NOT NULL PRIMARY KEY ,
	title VARCHAR(200) NOT NULL,
	description VARCHAR(200) NOT NULL,
	period TIMESTAMP DEFAULT NULL,
	duration int NOT NULL,
	finished BOOLEAN NOT NULL,
	department_id int NULL,
	person_id int NULL,
	
	FOREIGN KEY (department_id) REFERENCES department(department_id),
	FOREIGN KEY (person_id) REFERENCES person(person_id)
);

CREATE SEQUENCE person_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
/* Insert dos dados */

/* Departamento */
INSERT INTO department (title)
VALUES ('Financeiro');

INSERT INTO department (title)
VALUES ('Comercial');

INSERT INTO department (title)
VALUES ('Desenvolvimento');

/* Pessoa */
INSERT INTO person (name, department_id)
VALUES ('Camila', 1);

INSERT INTO person (name, department_id)
VALUES ('Pedro', 2);


INSERT INTO person (name, department_id)
VALUES ('Fabiano', 3);


INSERT INTO person (name, department_id)
VALUES ('Raquel', 3);


INSERT INTO person (name, department_id)
VALUES ('Patricia', 3);

INSERT INTO person (name, department_id)
VALUES ('Joaquim', 1);

/* Tarefa */
INSERT INTO task (title, description, period, duration, finished, department_id, person_id)
VALUES ('Validar NF Janeiro', 'Validar notas recebidas no mês de Janeiro', '2022-02-15 10:00:00', 14, true, 1, 1);

INSERT INTO task (title, description, period, duration, finished, department_id, person_id)
VALUES ('Bug 352', 'Corrigir bug 352 na versão 1.25', '2022-05-10 10:00:00', 14, false, 3, null);

INSERT INTO task (title, description, period, duration, finished, department_id, person_id)
VALUES ('Liberação da versão 1.24', 'Disponibilizar pacote para testes', '2022-02-02 10:00:00', 2, false, 3, 3);

INSERT INTO task (title, description, period, duration, finished, department_id, person_id)
VALUES ('Reunião A', 'Reunião com cliente A para apresentação do produto', '2022-02-05 10:00:00', 5, false, 3, 3);

INSERT INTO task (title, description, period, duration, finished, department_id, person_id)
VALUES ('Bug 401', 'Corrigir bug 401 na versão 1.20', '2022-02-01 10:00:00', 2, false, 3, 4);

/* Select que retorna nome do departamento, quantidade de tarefas finalizadas e quantidade de tarefas não finalizadas */
SELECT
    d.title AS department_name,
    SUM(CASE WHEN t.finished = true THEN 1 ELSE 0 END) AS tasks_finished,
    SUM(CASE WHEN t.finished = false THEN 1 ELSE 0 END) AS tasks_unfinished
FROM
    department d
LEFT JOIN
    task t ON t.department_id = d.department_id
GROUP BY
    d.title;
	
	
/* Select que retorna título da tarefa, prazo, se tiver pessoa alocada na tarefa exibir como “Encaminhado para + nome do pessoa” caso contrário “Pendente” e total de horas que essa pessoa já gastou. Ordenar por prazo decrescente. */
SELECT
    t.title AS task_title,
    t.period AS task_deadline,
    CASE WHEN p.person_id IS NOT NULL THEN CONCAT('Encaminhado para ', p.name) ELSE 'Pendente' END AS allocation_status,
    SUM(t.duration) AS total_hours_spent
FROM
    task t
LEFT JOIN
    person p ON t.person_id = p.person_id
GROUP BY
    t.task_id, t.title, t.period, p.person_id, p.name
ORDER BY
    t.period DESC;
