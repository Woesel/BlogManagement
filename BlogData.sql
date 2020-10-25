USE BlogFinals;
DESC role;

INSERT INTO `Role`(`role`) 
VALUES ("ROLE_ADMIN"),
		("ROLE_USER");
DESC user;
insert into `user`(`username`,`password`, `firstName`, `lastName`, `phone`, `photoFileName`,`enabled`)
    values("Sarah", "password", "ten", "zin", "1234567890", "https://pixy.org/src/109/1090092.png", 1),
    ("user", "password", "kel", "sang", "0987654321", "https://pixy.org/src/109/1090092.png", 1);
    
insert into `user_role`(`userId`,`roleId`)
    values(1,1),(1,2),(2,2);
    
-- =========================================================================
UPDATE user SET password = '$2a$10$0UOAPNGjwgCgEw.oxjPEXu76ePAsDt6v96cf/e6o6KUfszwDURXee' WHERE userId = 1;
UPDATE user SET password = '$2a$10$0UOAPNGjwgCgEw.oxjPEXu76ePAsDt6v96cf/e6o6KUfszwDURXee' WHERE userId = 2;

SELECT * from user;
    
    
    DESC hashtag;
     USE BlogFinals;
INSERT INTO hashtag (name) VALUES ("#awesome"),
								   ("#great");
   
DESC blog;

insert into blog(title, content, verified, staticPage,blogPosted, expiryDate, blogCreated, photoFileName, userId) Values
("First Blog", "xxxxxxxxxxxx", 1, 1, "2020-10-02 ", "2020-12-02", "2020-11-02", "c:/hey.img", 1);
insert into blog(title, content, verified, staticPage,blogPosted, expiryDate, blogCreated, photoFileName, userId) Values
("Second Blog", "The standard Lorem Ipsum passage, used since the 1500s Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris 
nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla 
pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", 
1, 1, "2020-10-02 ", "2020-11-02", "2020-12-02", "https://pixy.org/1090092/", 1),
("Second Blog", "The standard Lorem Ipsum passage, used since the 1500s Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris 
nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla 
pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", 
1, 1, "2020-02-02 ", "2020-02-02", "2020-02-02", "https://pixy.org/1090092/", 1);

insert into blog_hashtag(blogId, hashtagId) values (1,1),
													(2,1);
select * from blog_hashtag;
select * from blog;
select * from hashtag;
Select * from blog_hashtag;
SELECT * FROM Blog WHERE blogId=3;

SELECT h.* from Blog_Hashtag bh JOIN Hashtag h ON bh.hashtagId = h.hashtagId WHERE blogId=3; 

SELECT * from blog;