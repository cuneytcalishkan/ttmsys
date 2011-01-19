-- phpMyAdmin SQL Dump
-- version 2.10.2
-- http://www.phpmyadmin.net
--
-- Anamakine: localhost
-- Üretim Zamanı: 19 Ocak 2011 saat 17:34:07
-- Sunucu sürümü: 5.0.45
-- PHP Sürümü: 5.2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Veritabanı: `ttms`
--

-- --------------------------------------------------------

--
-- Tablo yapısı: `court`
--

CREATE TABLE `court` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Tablo döküm verisi `court`
--

INSERT INTO `court` VALUES (3, 'Court no:1');
INSERT INTO `court` VALUES (2, 'Court no:2');
INSERT INTO `court` VALUES (4, 'Court no:3');
INSERT INTO `court` VALUES (5, 'Court no:4');
INSERT INTO `court` VALUES (6, 'Court no:5');
INSERT INTO `court` VALUES (7, 'Court no:6');
INSERT INTO `court` VALUES (8, 'Court no:7');
INSERT INTO `court` VALUES (9, 'Court no:8');
INSERT INTO `court` VALUES (10, 'Court no:9');
INSERT INTO `court` VALUES (11, 'Court no:10');

-- --------------------------------------------------------

--
-- Tablo yapısı: `draw`
--

CREATE TABLE `draw` (
  `id` bigint(20) NOT NULL auto_increment,
  `awayDraw_id` bigint(20) default NULL,
  `awayTeam_id` bigint(20) default NULL,
  `homeDraw_id` bigint(20) default NULL,
  `homeTeam_id` bigint(20) default NULL,
  `winnerTeam_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=66 ;

--
-- Tablo döküm verisi `draw`
--

INSERT INTO `draw` VALUES (1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `draw` VALUES (2, NULL, 3, NULL, 2, NULL);
INSERT INTO `draw` VALUES (3, NULL, NULL, 2, NULL, NULL);
INSERT INTO `draw` VALUES (4, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `draw` VALUES (5, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `draw` VALUES (6, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `draw` VALUES (7, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `draw` VALUES (8, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `draw` VALUES (9, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `draw` VALUES (10, NULL, 35, NULL, 22, NULL);
INSERT INTO `draw` VALUES (11, NULL, 40, NULL, 24, NULL);
INSERT INTO `draw` VALUES (12, NULL, 43, NULL, 38, NULL);
INSERT INTO `draw` VALUES (13, NULL, 27, NULL, 34, NULL);
INSERT INTO `draw` VALUES (14, NULL, 6, NULL, 13, NULL);
INSERT INTO `draw` VALUES (15, NULL, NULL, NULL, 47, NULL);
INSERT INTO `draw` VALUES (16, 11, NULL, 10, NULL, NULL);
INSERT INTO `draw` VALUES (17, 13, NULL, 12, NULL, NULL);
INSERT INTO `draw` VALUES (18, 15, NULL, 14, NULL, NULL);
INSERT INTO `draw` VALUES (19, 17, NULL, 16, NULL, NULL);
INSERT INTO `draw` VALUES (20, NULL, NULL, 18, NULL, NULL);
INSERT INTO `draw` VALUES (21, 20, NULL, 19, NULL, NULL);
INSERT INTO `draw` VALUES (22, NULL, 13, NULL, 34, NULL);
INSERT INTO `draw` VALUES (23, NULL, 40, NULL, 35, NULL);
INSERT INTO `draw` VALUES (24, NULL, 6, NULL, 22, NULL);
INSERT INTO `draw` VALUES (25, NULL, 27, NULL, 43, NULL);
INSERT INTO `draw` VALUES (26, NULL, 47, NULL, 38, NULL);
INSERT INTO `draw` VALUES (27, NULL, NULL, NULL, 24, NULL);
INSERT INTO `draw` VALUES (28, 23, NULL, 22, NULL, NULL);
INSERT INTO `draw` VALUES (29, 25, NULL, 24, NULL, NULL);
INSERT INTO `draw` VALUES (30, 27, NULL, 26, NULL, NULL);
INSERT INTO `draw` VALUES (31, 29, NULL, 28, NULL, NULL);
INSERT INTO `draw` VALUES (32, NULL, NULL, 30, NULL, NULL);
INSERT INTO `draw` VALUES (33, 32, NULL, 31, NULL, NULL);
INSERT INTO `draw` VALUES (34, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `draw` VALUES (35, NULL, 26, NULL, 9, NULL);
INSERT INTO `draw` VALUES (36, NULL, 21, NULL, 29, NULL);
INSERT INTO `draw` VALUES (37, NULL, 11, NULL, 20, NULL);
INSERT INTO `draw` VALUES (38, NULL, 32, NULL, 5, NULL);
INSERT INTO `draw` VALUES (39, NULL, 51, NULL, 33, NULL);
INSERT INTO `draw` VALUES (40, 36, NULL, 35, NULL, NULL);
INSERT INTO `draw` VALUES (41, 38, NULL, 37, NULL, NULL);
INSERT INTO `draw` VALUES (42, NULL, NULL, 39, NULL, NULL);
INSERT INTO `draw` VALUES (43, 41, NULL, 40, NULL, NULL);
INSERT INTO `draw` VALUES (44, NULL, NULL, 42, NULL, NULL);
INSERT INTO `draw` VALUES (45, 44, NULL, 43, NULL, NULL);
INSERT INTO `draw` VALUES (46, NULL, 23, NULL, 37, NULL);
INSERT INTO `draw` VALUES (47, NULL, 42, NULL, 31, NULL);
INSERT INTO `draw` VALUES (48, NULL, 25, NULL, 46, NULL);
INSERT INTO `draw` VALUES (49, NULL, 41, NULL, 39, NULL);
INSERT INTO `draw` VALUES (50, 47, NULL, 46, NULL, NULL);
INSERT INTO `draw` VALUES (51, 49, NULL, 48, NULL, NULL);
INSERT INTO `draw` VALUES (52, 51, NULL, 50, NULL, NULL);
INSERT INTO `draw` VALUES (53, NULL, 48, NULL, 17, NULL);
INSERT INTO `draw` VALUES (54, NULL, 28, NULL, 49, NULL);
INSERT INTO `draw` VALUES (55, NULL, 7, NULL, 18, NULL);
INSERT INTO `draw` VALUES (56, 54, NULL, 53, NULL, NULL);
INSERT INTO `draw` VALUES (57, NULL, NULL, 55, NULL, NULL);
INSERT INTO `draw` VALUES (58, 57, NULL, 56, NULL, NULL);
INSERT INTO `draw` VALUES (59, NULL, 30, NULL, 16, NULL);
INSERT INTO `draw` VALUES (60, NULL, 8, NULL, 50, NULL);
INSERT INTO `draw` VALUES (61, NULL, 12, NULL, 4, NULL);
INSERT INTO `draw` VALUES (62, NULL, NULL, NULL, 19, NULL);
INSERT INTO `draw` VALUES (63, 60, NULL, 59, NULL, NULL);
INSERT INTO `draw` VALUES (64, 62, NULL, 61, NULL, NULL);
INSERT INTO `draw` VALUES (65, 64, NULL, 63, NULL, NULL);

-- --------------------------------------------------------

--
-- Tablo yapısı: `game`
--

CREATE TABLE `game` (
  `id` bigint(20) NOT NULL auto_increment,
  `awayTeamScore` int(11) NOT NULL,
  `homeTeamScore` int(11) NOT NULL,
  `setid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK21C0128E9AA4FA` (`setid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Tablo döküm verisi `game`
--


-- --------------------------------------------------------

--
-- Tablo yapısı: `matchstatistics`
--

CREATE TABLE `matchstatistics` (
  `id` bigint(20) NOT NULL auto_increment,
  `aces` int(11) NOT NULL,
  `doubleFauls` int(11) NOT NULL,
  `firstSPtsWon` int(11) NOT NULL,
  `returnPtsWon` int(11) NOT NULL,
  `secondSPtsWon` int(11) NOT NULL,
  `totalPtsWon` int(11) NOT NULL,
  `team_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK97316CA824EDBC5F` (`team_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Tablo döküm verisi `matchstatistics`
--

INSERT INTO `matchstatistics` VALUES (1, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `matchstatistics` VALUES (2, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `matchstatistics` VALUES (3, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `matchstatistics` VALUES (4, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `matchstatistics` VALUES (5, 0, 0, 0, 0, 0, 0, NULL);

-- --------------------------------------------------------

--
-- Tablo yapısı: `membershiprequest`
--

CREATE TABLE `membershiprequest` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=58 ;

--
-- Tablo döküm verisi `membershiprequest`
--


-- --------------------------------------------------------

--
-- Tablo yapısı: `mset`
--

CREATE TABLE `mset` (
  `id` bigint(20) NOT NULL auto_increment,
  `awayTeamScore` int(11) NOT NULL,
  `homeTeamScore` int(11) NOT NULL,
  `matchid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK3348D5DF39840` (`matchid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Tablo döküm verisi `mset`
--


-- --------------------------------------------------------

--
-- Tablo yapısı: `registereduser`
--

CREATE TABLE `registereduser` (
  `usertype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=63 ;

--
-- Tablo döküm verisi `registereduser`
--

INSERT INTO `registereduser` VALUES ('umpire', 1, 'Umpire', 'u', 'ump', 'u');
INSERT INTO `registereduser` VALUES ('registereduser', 2, 'Registered', 'ru', 'User', 'ru');
INSERT INTO `registereduser` VALUES ('manager', 3, 'Manager', 'm', 'man', 'm');
INSERT INTO `registereduser` VALUES ('player', 4, 'Player', 'p', 'pl', 'p');
INSERT INTO `registereduser` VALUES ('referee', 5, 'Referee', 'r', 'rf', 'r');
INSERT INTO `registereduser` VALUES ('player', 16, 'Çağla', 'cagla', 'Büyükakçay', 'cagla');
INSERT INTO `registereduser` VALUES ('manager', 7, 'Fırat', 'fir', 'Şahindal', 'fir');
INSERT INTO `registereduser` VALUES ('player', 17, 'Irina', 'irina', 'Begu', 'irina');
INSERT INTO `registereduser` VALUES ('player', 10, 'John', 'john', 'Andrews', 'john');
INSERT INTO `registereduser` VALUES ('player', 11, 'Boris', 'boris', 'Becker', 'boris');
INSERT INTO `registereduser` VALUES ('player', 12, 'Mike', 'mike', 'Bryan', 'mike');
INSERT INTO `registereduser` VALUES ('player', 13, 'Roy', 'roy', 'Emerson', 'roy');
INSERT INTO `registereduser` VALUES ('player', 14, 'Phil', 'ph', 'Dent', 'phil');
INSERT INTO `registereduser` VALUES ('player', 15, 'Albert', 'alb', 'Costa', 'alb');
INSERT INTO `registereduser` VALUES ('player', 18, 'Alice', 'ali', 'Canepa', 'ali');
INSERT INTO `registereduser` VALUES ('player', 19, 'Maret', 'mar', 'Ani', 'mar');
INSERT INTO `registereduser` VALUES ('manager', 20, 'Pat', 'pat', 'Du Pre', 'pat');
INSERT INTO `registereduser` VALUES ('manager', 21, 'Thierry', 'champ', 'Champion', 'champ');
INSERT INTO `registereduser` VALUES ('referee', 22, 'Tim', 'tim', 'Gullikson', 'tim');
INSERT INTO `registereduser` VALUES ('referee', 23, 'Richard', 'rich', 'Gasquet', 'rich');
INSERT INTO `registereduser` VALUES ('referee', 24, 'Neale', 'nea', 'Fraser', 'nea');
INSERT INTO `registereduser` VALUES ('referee', 25, 'Robert', 'rob', 'Falkenburg', 'rob');
INSERT INTO `registereduser` VALUES ('umpire', 26, 'Jan', 'jan', 'Gunnarsson', 'jan');
INSERT INTO `registereduser` VALUES ('umpire', 27, 'Lew', 'hoad', 'Hoad', 'hoad');
INSERT INTO `registereduser` VALUES ('umpire', 28, 'Nicolas', 'nico', 'Kiefer', 'nico');
INSERT INTO `registereduser` VALUES ('umpire', 29, 'Rene', 'rene', 'Lacoste', 'rene');
INSERT INTO `registereduser` VALUES ('umpire', 30, 'Johan', 'joh', 'Kriek', 'joh');
INSERT INTO `registereduser` VALUES ('registereduser', 31, 'Kristie', 'kris', 'Boogert', 'kris');
INSERT INTO `registereduser` VALUES ('registereduser', 32, 'Angela', 'angel', 'Buxton', 'angel');
INSERT INTO `registereduser` VALUES ('registereduser', 33, 'Belinda', 'bel', 'Cortwell', 'bel');
INSERT INTO `registereduser` VALUES ('player', 34, 'Melinda', 'mel', 'Czink', 'mel');
INSERT INTO `registereduser` VALUES ('player', 35, 'Lottie', 'lot', 'Dod', 'lot');
INSERT INTO `registereduser` VALUES ('player', 36, 'Sandra', 'san', 'Dopfer', 'san');
INSERT INTO `registereduser` VALUES ('player', 37, 'Jo', 'jo', 'Durie', 'jo');
INSERT INTO `registereduser` VALUES ('player', 38, 'Robyn', 'eb', 'Ebbern', 'eb');
INSERT INTO `registereduser` VALUES ('player', 39, 'Chris', 'chris', 'Evert', 'chris');
INSERT INTO `registereduser` VALUES ('player', 40, 'Patty', 'fen', 'Fendick', 'fen');
INSERT INTO `registereduser` VALUES ('player', 41, 'Donna', 'don', 'Faber', 'don');
INSERT INTO `registereduser` VALUES ('player', 42, 'Sara', 'sara', 'Errani', 'sara');
INSERT INTO `registereduser` VALUES ('player', 43, 'Eva', 'eva', 'Dyrberg', 'eva');
INSERT INTO `registereduser` VALUES ('player', 44, 'Amy', 'amy', 'Frazier', 'amy');
INSERT INTO `registereduser` VALUES ('player', 45, 'Arthur', 'art', 'Ashe', 'art');
INSERT INTO `registereduser` VALUES ('player', 46, 'Paul', 'paul', 'Annacone', 'paul');
INSERT INTO `registereduser` VALUES ('player', 47, 'Victor', 'vic', 'Amaya', 'vic');
INSERT INTO `registereduser` VALUES ('player', 48, 'Mario', 'mario', 'Ancic', 'mario');
INSERT INTO `registereduser` VALUES ('player', 49, 'Juan', 'juan', 'Aguileri', 'juan');
INSERT INTO `registereduser` VALUES ('player', 50, 'Andre', 'and', 'Agassi', 'and');
INSERT INTO `registereduser` VALUES ('player', 51, 'Luis', 'luis', 'Ayala', 'luis');
INSERT INTO `registereduser` VALUES ('player', 52, 'Jeremy', 'jerr', 'Bates', 'jerr');
INSERT INTO `registereduser` VALUES ('player', 53, 'James', 'jam', 'Blake', 'jam');
INSERT INTO `registereduser` VALUES ('player', 54, 'Galo', 'gal', 'Blanco', 'gal');
INSERT INTO `registereduser` VALUES ('player', 55, 'Tom', 'tom', 'Brown', 'tom');
INSERT INTO `registereduser` VALUES ('player', 56, 'Bob', 'bob', 'Bryan', 'bob');
INSERT INTO `registereduser` VALUES ('player', 57, 'Phil', 'ph', 'Dent', 'ph');
INSERT INTO `registereduser` VALUES ('player', 58, 'Mark', 'mark', 'Cox', 'mark');
INSERT INTO `registereduser` VALUES ('player', 59, 'Ross', 'ross', 'Case', 'ross');
INSERT INTO `registereduser` VALUES ('player', 60, 'Roger', 'rog', 'Federer', 'rog');
INSERT INTO `registereduser` VALUES ('player', 61, 'Ken', 'ken', 'Flach', 'ken');
INSERT INTO `registereduser` VALUES ('player', 62, 'Rafael', 'raf', 'Nadal', 'raf');

-- --------------------------------------------------------

--
-- Tablo yapısı: `registereduser_team`
--

CREATE TABLE `registereduser_team` (
  `RegisteredUser_id` bigint(20) NOT NULL,
  `trackList_id` bigint(20) NOT NULL,
  KEY `FK5AB625EF650E583F` (`RegisteredUser_id`),
  KEY `FK5AB625EFA14F2ED3` (`trackList_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `registereduser_team`
--

INSERT INTO `registereduser_team` VALUES (2, 1);
INSERT INTO `registereduser_team` VALUES (3, 9);
INSERT INTO `registereduser_team` VALUES (3, 4);
INSERT INTO `registereduser_team` VALUES (3, 12);
INSERT INTO `registereduser_team` VALUES (3, 33);
INSERT INTO `registereduser_team` VALUES (5, 26);
INSERT INTO `registereduser_team` VALUES (5, 16);

-- --------------------------------------------------------

--
-- Tablo yapısı: `team`
--

CREATE TABLE `team` (
  `teamtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL auto_increment,
  `tournament_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK27B67D1A0F591F` (`tournament_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=52 ;

--
-- Tablo döküm verisi `team`
--

INSERT INTO `team` VALUES ('singles', 12, 5);
INSERT INTO `team` VALUES ('singles', 19, 5);
INSERT INTO `team` VALUES ('doubles', 18, 4);
INSERT INTO `team` VALUES ('singles', 4, 5);
INSERT INTO `team` VALUES ('doubles', 5, 6);
INSERT INTO `team` VALUES ('singles', 6, 2);
INSERT INTO `team` VALUES ('doubles', 7, 4);
INSERT INTO `team` VALUES ('singles', 8, 5);
INSERT INTO `team` VALUES ('doubles', 9, 6);
INSERT INTO `team` VALUES ('doubles', 11, 6);
INSERT INTO `team` VALUES ('singles', 13, 2);
INSERT INTO `team` VALUES ('doubles', 17, 4);
INSERT INTO `team` VALUES ('singles', 16, 5);
INSERT INTO `team` VALUES ('doubles', 20, 6);
INSERT INTO `team` VALUES ('doubles', 21, 6);
INSERT INTO `team` VALUES ('singles', 22, 2);
INSERT INTO `team` VALUES ('doubles', 23, 3);
INSERT INTO `team` VALUES ('singles', 24, 2);
INSERT INTO `team` VALUES ('doubles', 25, 3);
INSERT INTO `team` VALUES ('doubles', 26, 6);
INSERT INTO `team` VALUES ('singles', 27, 2);
INSERT INTO `team` VALUES ('doubles', 28, 4);
INSERT INTO `team` VALUES ('doubles', 29, 6);
INSERT INTO `team` VALUES ('singles', 30, 5);
INSERT INTO `team` VALUES ('doubles', 31, 3);
INSERT INTO `team` VALUES ('doubles', 32, 6);
INSERT INTO `team` VALUES ('doubles', 33, 6);
INSERT INTO `team` VALUES ('singles', 34, 2);
INSERT INTO `team` VALUES ('singles', 35, 2);
INSERT INTO `team` VALUES ('doubles', 48, 4);
INSERT INTO `team` VALUES ('doubles', 37, 3);
INSERT INTO `team` VALUES ('singles', 38, 2);
INSERT INTO `team` VALUES ('doubles', 39, 3);
INSERT INTO `team` VALUES ('singles', 40, 2);
INSERT INTO `team` VALUES ('doubles', 41, 3);
INSERT INTO `team` VALUES ('doubles', 42, 3);
INSERT INTO `team` VALUES ('singles', 43, 2);
INSERT INTO `team` VALUES ('doubles', 49, 4);
INSERT INTO `team` VALUES ('doubles', 46, 3);
INSERT INTO `team` VALUES ('singles', 47, 2);
INSERT INTO `team` VALUES ('singles', 50, 5);
INSERT INTO `team` VALUES ('doubles', 51, 6);

-- --------------------------------------------------------

--
-- Tablo yapısı: `team_registereduser`
--

CREATE TABLE `team_registereduser` (
  `teams_id` bigint(20) NOT NULL,
  `players_id` bigint(20) NOT NULL,
  KEY `FKC2BE878F2177FC2E` (`players_id`),
  KEY `FKC2BE878F17510A66` (`teams_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `team_registereduser`
--

INSERT INTO `team_registereduser` VALUES (1, 4);
INSERT INTO `team_registereduser` VALUES (18, 61);
INSERT INTO `team_registereduser` VALUES (3, 4);
INSERT INTO `team_registereduser` VALUES (4, 10);
INSERT INTO `team_registereduser` VALUES (5, 10);
INSERT INTO `team_registereduser` VALUES (5, 17);
INSERT INTO `team_registereduser` VALUES (6, 17);
INSERT INTO `team_registereduser` VALUES (7, 11);
INSERT INTO `team_registereduser` VALUES (7, 10);
INSERT INTO `team_registereduser` VALUES (8, 11);
INSERT INTO `team_registereduser` VALUES (9, 19);
INSERT INTO `team_registereduser` VALUES (9, 18);
INSERT INTO `team_registereduser` VALUES (10, 19);
INSERT INTO `team_registereduser` VALUES (11, 13);
INSERT INTO `team_registereduser` VALUES (12, 15);
INSERT INTO `team_registereduser` VALUES (13, 18);
INSERT INTO `team_registereduser` VALUES (16, 62);
INSERT INTO `team_registereduser` VALUES (17, 39);
INSERT INTO `team_registereduser` VALUES (17, 62);
INSERT INTO `team_registereduser` VALUES (18, 12);
INSERT INTO `team_registereduser` VALUES (19, 61);
INSERT INTO `team_registereduser` VALUES (20, 34);
INSERT INTO `team_registereduser` VALUES (20, 15);
INSERT INTO `team_registereduser` VALUES (21, 61);
INSERT INTO `team_registereduser` VALUES (21, 16);
INSERT INTO `team_registereduser` VALUES (22, 34);
INSERT INTO `team_registereduser` VALUES (23, 34);
INSERT INTO `team_registereduser` VALUES (23, 48);
INSERT INTO `team_registereduser` VALUES (24, 35);
INSERT INTO `team_registereduser` VALUES (25, 35);
INSERT INTO `team_registereduser` VALUES (25, 43);
INSERT INTO `team_registereduser` VALUES (26, 59);
INSERT INTO `team_registereduser` VALUES (26, 40);
INSERT INTO `team_registereduser` VALUES (27, 36);
INSERT INTO `team_registereduser` VALUES (28, 59);
INSERT INTO `team_registereduser` VALUES (28, 13);
INSERT INTO `team_registereduser` VALUES (29, 36);
INSERT INTO `team_registereduser` VALUES (29, 62);
INSERT INTO `team_registereduser` VALUES (30, 59);
INSERT INTO `team_registereduser` VALUES (31, 37);
INSERT INTO `team_registereduser` VALUES (31, 19);
INSERT INTO `team_registereduser` VALUES (32, 37);
INSERT INTO `team_registereduser` VALUES (32, 56);
INSERT INTO `team_registereduser` VALUES (33, 38);
INSERT INTO `team_registereduser` VALUES (33, 12);
INSERT INTO `team_registereduser` VALUES (34, 38);
INSERT INTO `team_registereduser` VALUES (35, 16);
INSERT INTO `team_registereduser` VALUES (36, 38);
INSERT INTO `team_registereduser` VALUES (36, 51);
INSERT INTO `team_registereduser` VALUES (37, 16);
INSERT INTO `team_registereduser` VALUES (37, 18);
INSERT INTO `team_registereduser` VALUES (38, 39);
INSERT INTO `team_registereduser` VALUES (39, 39);
INSERT INTO `team_registereduser` VALUES (39, 46);
INSERT INTO `team_registereduser` VALUES (40, 40);
INSERT INTO `team_registereduser` VALUES (41, 40);
INSERT INTO `team_registereduser` VALUES (41, 17);
INSERT INTO `team_registereduser` VALUES (42, 36);
INSERT INTO `team_registereduser` VALUES (42, 44);
INSERT INTO `team_registereduser` VALUES (43, 41);
INSERT INTO `team_registereduser` VALUES (44, 41);
INSERT INTO `team_registereduser` VALUES (44, 50);
INSERT INTO `team_registereduser` VALUES (45, 42);
INSERT INTO `team_registereduser` VALUES (46, 42);
INSERT INTO `team_registereduser` VALUES (46, 51);
INSERT INTO `team_registereduser` VALUES (47, 42);
INSERT INTO `team_registereduser` VALUES (48, 15);
INSERT INTO `team_registereduser` VALUES (48, 14);
INSERT INTO `team_registereduser` VALUES (49, 53);
INSERT INTO `team_registereduser` VALUES (49, 52);
INSERT INTO `team_registereduser` VALUES (50, 53);
INSERT INTO `team_registereduser` VALUES (51, 53);
INSERT INTO `team_registereduser` VALUES (51, 43);

-- --------------------------------------------------------

--
-- Tablo yapısı: `tmatch`
--

CREATE TABLE `tmatch` (
  `id` bigint(20) NOT NULL auto_increment,
  `awayTeamScore` int(11) NOT NULL,
  `homeTeamScore` int(11) NOT NULL,
  `mDate` date NOT NULL,
  `mTime` varchar(255) NOT NULL,
  `report` varchar(255) default NULL,
  `court_id` bigint(20) default NULL,
  `statistics_id` bigint(20) default NULL,
  `tournament_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCC2001D11A0F591F` (`tournament_id`),
  KEY `FKCC2001D1C024DD9A` (`statistics_id`),
  KEY `FKCC2001D1C55A36B5` (`court_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Tablo döküm verisi `tmatch`
--

INSERT INTO `tmatch` VALUES (1, 0, 0, '2011-11-11', '13:13', NULL, 2, 4, 6);
INSERT INTO `tmatch` VALUES (2, 0, 0, '2011-02-02', '12:12', NULL, 8, 5, 6);

-- --------------------------------------------------------

--
-- Tablo yapısı: `tmatch_referee`
--

CREATE TABLE `tmatch_referee` (
  `matches_id` bigint(20) NOT NULL,
  `referees_id` bigint(20) NOT NULL,
  KEY `FKC9AA20B247EB44C2` (`referees_id`),
  KEY `FKC9AA20B24B568647` (`matches_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `tmatch_referee`
--

INSERT INTO `tmatch_referee` VALUES (1, 5);
INSERT INTO `tmatch_referee` VALUES (2, 23);
INSERT INTO `tmatch_referee` VALUES (2, 22);

-- --------------------------------------------------------

--
-- Tablo yapısı: `tmatch_team`
--

CREATE TABLE `tmatch_team` (
  `matches_id` bigint(20) NOT NULL,
  `teams_id` bigint(20) NOT NULL,
  KEY `FKE6BFE82B4B568647` (`matches_id`),
  KEY `FKE6BFE82B17510A66` (`teams_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `tmatch_team`
--

INSERT INTO `tmatch_team` VALUES (2, 33);
INSERT INTO `tmatch_team` VALUES (2, 51);

-- --------------------------------------------------------

--
-- Tablo yapısı: `tmatch_umpire`
--

CREATE TABLE `tmatch_umpire` (
  `matches_id` bigint(20) NOT NULL,
  `umpires_id` bigint(20) NOT NULL,
  KEY `FK6F2E02B24B568647` (`matches_id`),
  KEY `FK6F2E02B2610E45F4` (`umpires_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `tmatch_umpire`
--

INSERT INTO `tmatch_umpire` VALUES (1, 1);
INSERT INTO `tmatch_umpire` VALUES (2, 28);
INSERT INTO `tmatch_umpire` VALUES (2, 29);

-- --------------------------------------------------------

--
-- Tablo yapısı: `tournament`
--

CREATE TABLE `tournament` (
  `id` bigint(20) NOT NULL auto_increment,
  `endDate` date NOT NULL,
  `name` varchar(255) NOT NULL,
  `prize` double NOT NULL,
  `report` varchar(255) default NULL,
  `startDate` date NOT NULL,
  `type` varchar(255) NOT NULL,
  `draw_id` bigint(20) default NULL,
  `manager_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK3B743609ECB67EFF` (`draw_id`),
  KEY `FK3B743609CF2F20B5` (`manager_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Tablo döküm verisi `tournament`
--

INSERT INTO `tournament` VALUES (7, '2011-02-01', 'Friendly Match', 1, '', '2010-12-31', 'Men''s Singles', 9, 3);
INSERT INTO `tournament` VALUES (2, '2011-02-27', 'Women''s Tournament', 10000, '', '2011-02-08', 'Women''s Singles', 33, 3);
INSERT INTO `tournament` VALUES (3, '2011-03-11', 'Women''s Doubles Tournament', 20000, '', '2011-02-10', 'Women''s Doubles', 52, 3);
INSERT INTO `tournament` VALUES (4, '2011-03-02', 'Men''s Doubles Tournament', 20000, '', '2011-02-03', 'Men''s Doubles', 58, 3);
INSERT INTO `tournament` VALUES (5, '2011-05-02', 'Men''s Tournament', 10000, '', '2011-04-03', 'Men''s Singles', 65, 3);
INSERT INTO `tournament` VALUES (6, '2011-06-11', 'Mixed Doubles Tournament', 20000, '', '2011-04-24', 'Mixed Doubles', 45, 3);
INSERT INTO `tournament` VALUES (8, '2059-12-31', 'Fırat''s Tournament', 9999999, 'Saygılar.', '1985-03-20', 'Women''s Doubles', 34, 7);

-- --------------------------------------------------------

--
-- Tablo yapısı: `tournamentjoinrequest`
--

CREATE TABLE `tournamentjoinrequest` (
  `id` bigint(20) NOT NULL auto_increment,
  `manager_id` bigint(20) default NULL,
  `team_id` bigint(20) default NULL,
  `tournament_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK9F7AEC3C1A0F591F` (`tournament_id`),
  KEY `FK9F7AEC3C24EDBC5F` (`team_id`),
  KEY `FK9F7AEC3CCF2F20B5` (`manager_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=51 ;

--
-- Tablo döküm verisi `tournamentjoinrequest`
--


-- --------------------------------------------------------

--
-- Tablo yapısı: `tournament_court`
--

CREATE TABLE `tournament_court` (
  `tournaments_id` bigint(20) NOT NULL,
  `courts_id` bigint(20) NOT NULL,
  KEY `FKE70C8FB532C1D2D8` (`courts_id`),
  KEY `FKE70C8FB527E7EBBE` (`tournaments_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `tournament_court`
--

INSERT INTO `tournament_court` VALUES (2, 8);
INSERT INTO `tournament_court` VALUES (3, 3);
INSERT INTO `tournament_court` VALUES (4, 6);
INSERT INTO `tournament_court` VALUES (4, 2);
INSERT INTO `tournament_court` VALUES (4, 5);
INSERT INTO `tournament_court` VALUES (4, 8);
INSERT INTO `tournament_court` VALUES (4, 10);
INSERT INTO `tournament_court` VALUES (3, 5);
INSERT INTO `tournament_court` VALUES (3, 2);
INSERT INTO `tournament_court` VALUES (3, 9);
INSERT INTO `tournament_court` VALUES (3, 10);
INSERT INTO `tournament_court` VALUES (2, 9);
INSERT INTO `tournament_court` VALUES (2, 3);
INSERT INTO `tournament_court` VALUES (2, 10);
INSERT INTO `tournament_court` VALUES (5, 7);
INSERT INTO `tournament_court` VALUES (5, 10);
INSERT INTO `tournament_court` VALUES (5, 9);
INSERT INTO `tournament_court` VALUES (5, 6);
INSERT INTO `tournament_court` VALUES (6, 2);
INSERT INTO `tournament_court` VALUES (6, 5);
INSERT INTO `tournament_court` VALUES (6, 7);
INSERT INTO `tournament_court` VALUES (6, 9);
INSERT INTO `tournament_court` VALUES (6, 10);
INSERT INTO `tournament_court` VALUES (6, 8);
INSERT INTO `tournament_court` VALUES (7, 6);
INSERT INTO `tournament_court` VALUES (7, 2);
INSERT INTO `tournament_court` VALUES (8, 4);
INSERT INTO `tournament_court` VALUES (8, 3);

-- --------------------------------------------------------

--
-- Tablo yapısı: `tournament_referee`
--

CREATE TABLE `tournament_referee` (
  `tournaments_id` bigint(20) NOT NULL,
  `referees_id` bigint(20) NOT NULL,
  KEY `FK8CBEE0CA47EB44C2` (`referees_id`),
  KEY `FK8CBEE0CA27E7EBBE` (`tournaments_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `tournament_referee`
--

INSERT INTO `tournament_referee` VALUES (2, 22);
INSERT INTO `tournament_referee` VALUES (2, 24);
INSERT INTO `tournament_referee` VALUES (3, 24);
INSERT INTO `tournament_referee` VALUES (3, 23);
INSERT INTO `tournament_referee` VALUES (3, 25);
INSERT INTO `tournament_referee` VALUES (4, 22);
INSERT INTO `tournament_referee` VALUES (4, 23);
INSERT INTO `tournament_referee` VALUES (4, 25);
INSERT INTO `tournament_referee` VALUES (5, 25);
INSERT INTO `tournament_referee` VALUES (5, 22);
INSERT INTO `tournament_referee` VALUES (5, 24);
INSERT INTO `tournament_referee` VALUES (6, 25);
INSERT INTO `tournament_referee` VALUES (6, 22);
INSERT INTO `tournament_referee` VALUES (6, 23);
INSERT INTO `tournament_referee` VALUES (7, 23);
INSERT INTO `tournament_referee` VALUES (7, 22);
INSERT INTO `tournament_referee` VALUES (8, 5);

-- --------------------------------------------------------

--
-- Tablo yapısı: `tournament_umpire`
--

CREATE TABLE `tournament_umpire` (
  `tournaments_id` bigint(20) NOT NULL,
  `umpires_id` bigint(20) NOT NULL,
  KEY `FK64F4DF9A27E7EBBE` (`tournaments_id`),
  KEY `FK64F4DF9A610E45F4` (`umpires_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `tournament_umpire`
--

INSERT INTO `tournament_umpire` VALUES (2, 30);
INSERT INTO `tournament_umpire` VALUES (2, 29);
INSERT INTO `tournament_umpire` VALUES (2, 28);
INSERT INTO `tournament_umpire` VALUES (3, 26);
INSERT INTO `tournament_umpire` VALUES (3, 1);
INSERT INTO `tournament_umpire` VALUES (3, 27);
INSERT INTO `tournament_umpire` VALUES (4, 28);
INSERT INTO `tournament_umpire` VALUES (4, 30);
INSERT INTO `tournament_umpire` VALUES (4, 27);
INSERT INTO `tournament_umpire` VALUES (4, 1);
INSERT INTO `tournament_umpire` VALUES (5, 29);
INSERT INTO `tournament_umpire` VALUES (5, 27);
INSERT INTO `tournament_umpire` VALUES (5, 1);
INSERT INTO `tournament_umpire` VALUES (5, 30);
INSERT INTO `tournament_umpire` VALUES (6, 26);
INSERT INTO `tournament_umpire` VALUES (6, 28);
INSERT INTO `tournament_umpire` VALUES (6, 29);
INSERT INTO `tournament_umpire` VALUES (6, 27);
INSERT INTO `tournament_umpire` VALUES (7, 28);
INSERT INTO `tournament_umpire` VALUES (7, 27);
INSERT INTO `tournament_umpire` VALUES (8, 29);
INSERT INTO `tournament_umpire` VALUES (8, 27);