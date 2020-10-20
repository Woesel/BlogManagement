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
    USE BlogFinals;
    SELECT r.* FROM User_Role ur JOIN Role r ON ur.roleId = r.roleId WHERE ur.userId = 2;
    SELECT r.* FROM user_role ur JOIN role r ON ur.role_id = r.id WHERE ur.user_id = 1;