insert into user (id, user_id, password, name, email) values (1, 'jaewoong', '12341234', 'choijaewoong', 'jwchoi0129@gmail.com'),(2, 'asdf', 'asdf', 'asdf', 'jwchoi0129@gmail.com'),(3, 'qwer', 'qwer', 'qwer', 'torerov39@gmail.com');

INSERT INTO QUESTION (id, writer_id, title, contents) VALUES(1, 1,'국내에서 Ruby on Rails와 Play가 ','Ruby on Rails(이하 RoR)는');
INSERT INTO QUESTION (id, writer_id, title, contents) VALUES(2, 2,'산지기가 쓴 글','산지기는 군생활 때 나의 별명. 자바지기의 유래는 산지기');
INSERT INTO QUESTION (id, writer_id, title, contents) VALUES(3, 2,'산지기가 쓴 글2','산지기는 군생활 때 나의 별명. 자바지기의 유래는 산지기');

INSERT INTO COMMENT (id, writer_id, question_id, contents) VALUES(1, 2, 2, '댓글 2-1');
INSERT INTO COMMENT (id, writer_id, question_id, contents) VALUES(2, 2, 2, '댓글 2-2');
INSERT INTO COMMENT (id, writer_id, question_id, contents) VALUES(3, 1, 3, '댓글 3-1');
INSERT INTO COMMENT (id, writer_id, question_id, contents) VALUES(4, 2, 3, '댓글 3-2');
INSERT INTO COMMENT (id, writer_id, question_id, contents) VALUES(5, 2, 3, '댓글 3-3');