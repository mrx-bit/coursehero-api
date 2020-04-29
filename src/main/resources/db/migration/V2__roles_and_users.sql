INSERT INTO coursehero.role (name) VALUES ('ADMIN');
INSERT INTO coursehero.role (name) VALUES ('PARENT');
INSERT INTO coursehero.role (name) VALUES ('CHILD');

INSERT INTO coursehero.user (birthdate, email, enabled, name, parent_id, password, reg_date, role_id) VALUES (null, 'admin@gmail.com', true, null, null, '$2a$10$.MI1.v.lowCycSfmVyWqe.Kx2pXQz805gE5PCzhmRJjNBiBTZS.V2', '2020-04-28 18:11:20.554000', 1);
INSERT INTO coursehero.user (birthdate, email, enabled, name, parent_id, password, reg_date, role_id) VALUES (null, 'parent@gmail.com', true, null, null, '$2a$10$7mM5rJR4.yn225/UE/mPHuF4KcFxFNLG31z5ChMjfDJxuTPzmDjQS', '2020-04-28 18:11:20.679000', 2);
INSERT INTO coursehero.user (birthdate, email, enabled, name, parent_id, password, reg_date, role_id) VALUES (null, 'child@gmail.com', true, null, 2, '$2a$10$kJiRQS2BqTUdmuf74m0Nj.71Q3YINC.RixR2fXyh1CFIuYmr6vy0i', '2020-04-28 18:11:20.799000', 3);