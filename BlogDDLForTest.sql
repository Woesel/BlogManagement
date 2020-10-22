drop database if exists BlogFinalsTest;
create database BlogFinalsTest;

use BlogFinalsTest;

-- /////////////////////////////////////
-- /////////////USER

CREATE TABLE `User`
(
 `userId`        INT PRIMARY KEY NOT NULL AUTO_INCREMENT ,
 `username`      varchar(45) NOT NULL ,
 `password`      varchar(100) NOT NULL ,
 `firstName`     varchar(45) NOT NULL ,
 `lastName`      varchar(45) NOT NULL ,
 `phone`         char(10) NOT NULL ,
 `photoFileName` varchar(255) NOT NULL ,
 `enabled`       bit NOT NULL 
);

-- /////////////////////////////////////
-- /////////////ROLE

create table `Role`(
`roleId` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`role` varchar(45) NOT NULL
);

-- /////////////////////////////////////
-- /////////////USER ROLE
create table `User_Role`(
`userId` INT NOT NULL,
`roleId` INT NOT NULL,
PRIMARY KEY(`userId`,`roleId`),
FOREIGN KEY (`userId`) REFERENCES `User`(`userId`),
FOREIGN KEY (`roleId`) REFERENCES `Role`(`roleId`));

-- /////////////////////////////////////
-- /////////////BLOG
CREATE TABLE `Blog`
(
 `blogId`     INT PRIMARY KEY NOT NULL AUTO_INCREMENT ,
 `title`      varchar(45) NOT NULL ,
 `content`    text NOT NULL ,
 `verified`   bit NOT NULL ,
 `staticPage` bit NOT NULL ,
 `blogPosted` datetime NOT NULL ,
 `expiryDate` datetime,
 `blogCreated` datetime NOT NULL,
 `photoFileName` varchar(255) NOT NULL,
 `userId`     INT NOT NULL ,
 
FOREIGN KEY (`userId`) REFERENCES `User` (`userId`)
);

-- /////////////////////////////////////
-- /////////////HASHTAG
CREATE TABLE `Hashtag`
(
 `hashtagId`  INT PRIMARY KEY NOT NULL AUTO_INCREMENT ,
 `name`      varchar(45) NOT NULL 
);

-- /////////////////////////////////////
-- /////////////BLOG HASHTAG
CREATE TABLE `Blog_Hashtag`
(
 `blogId`    int NOT NULL ,
 `hashtagId` int NOT NULL ,

PRIMARY KEY (`blogId`, `hashtagId`),
FOREIGN KEY (`blogId`) REFERENCES `Blog` (`blogId`),
FOREIGN KEY (`hashtagId`) REFERENCES `Hashtag` (`hashtagId`)
);
use blogfinalstest;
SELECT * from hashtag;
SELECT * from blog;
select * from blog_hashtag;

SELECT h.* from Hashtag h JOIN Blog_Hashtag bh  ON bh.hashtagId = h.hashtagId WHERE bh.blogId=1;
SELECT h.* from Blog_Hashtag bh JOIN Hashtag h ON bh.hashtagId = h.hashtagId WHERE blogId=1;

SELECT * FROM Blog WHERE blogId=1;

SELECT * from blog;
SELECT CURRENT_TIMEZONE ( );