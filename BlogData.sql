USE BlogFinals;

DESC role;

INSERT INTO `Role`(`role`) 
VALUES ("ROLE_ADMIN"),
		("ROLE_USER");
DESC user;
insert into `user`(`username`,`password`, `firstName`, `lastName`, `phone`, `photoFileName`,`enabled`)
    values("ten10", "1234", "ten", "zin", "1234567890", "c://image.png", 1),
    ("bhungu", "6789", "kel", "sang", "0987654321", "D://image.png", 1);
    
insert into `user_role`(`userId`,`roleId`)
    values(1,1),(1,2),(2,2);
    DESC hashtag;
     USE BlogFinals;
INSERT INTO hashtag (name) VALUES ("#awesome"),
								   ("#great");
   
DESC blog;

insert into blog(title, content, verified, staticPage,blogPosted, expiryDate, blogCreated, photoFileName, userId) Values
("First Blog", "xxxxxxxxxxxx", 1, 1, "2020-02-02 ", "2020-02-02", "2020-02-02", "c:/hey.img", 1);

insert into blog_hashtag(blogId, hashtagId) values ();

select * from blog;
select * from hashtag;
Select * from blog_hashtag;
SELECT * FROM Blog WHERE blogId=3;

SELECT h.* from Blog_Hashtag bh JOIN Hashtag h ON bh.hashtagId = h.hashtagId WHERE blogId=3; 