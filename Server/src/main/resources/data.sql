INSERT INTO `user` (age, bpm, email, height, nickname, weight) VALUES(20, 100, 'qotnqls1998@gmail.com', 165, '배수빈', 49);
insert into `user` (age, bpm, email, height, nickname, weight) VALUES(28, 70, 'dudgjs304321@gmail.com', 173, '조영헌', 68);

INSERT INTO `exercise_list` (`exercise_name`) VALUES ('수영');
INSERT INTO `exercise_list` (`exercise_name`) VALUES ('등산');
INSERT INTO `exercise_list` (`exercise_name`) VALUES ('달리기');

INSERT INTO `prefer_exercise` (`exercise_list_seq`, `user_seq`) VALUES ('1', '1');
INSERT INTO `prefer_exercise` (`exercise_list_seq`, `user_seq`) VALUES ('2', '1');
INSERT INTO `prefer_exercise` (`exercise_list_seq`, `user_seq`) VALUES ('1', '2');
