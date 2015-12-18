CREATE DATABASE if not exists employees
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_unicode_ci;

use employees;

insert into `departments` values
(1, 'HR'),
(2, 'Tech'),
(3, 'Finance'),
(4, 'Development');

insert into `employee` values
(1, 1, 'Vasyl', 1),
(2, 1, 'Vasyl', 2),
(3, 1, 'Igor', 3),
(4, 0, 'Marta', 4),
(5, 1, 'Julia', 1),
(6, 1, 'Andrij', 2),
(7, 1, 'Oleg', 3),
(8, 0, 'Ivan', 4),
(9, 0, 'Ivan', 1),
(10, 1, 'Vasyl', 1),
(11, 1, 'Vasyl', 2),
(12, 1, 'Igor', 3),
(13, 0, 'Marta', 4),
(14, 1, 'Julia', 1),
(15, 1, 'Andrij', 2),
(16, 1, 'Oleg', 3),
(17, 0, 'Ivan', 4),
(18, 0, 'Ivan', 1),
(19, 1, 'Vasyl', 1),
(20, 1, 'Vasyl', 2),
(21, 1, 'Igor', 3),
(22, 0, 'Marta', 4),
(23, 1, 'Julia', 1),
(24, 1, 'Andrij', 2),
(25, 1, 'Oleg', 3),
(26, 0, 'Ivan', 4),
(27, 0, 'Ivan', 1),
(28, 1, 'Vasyl', 1),
(29, 1, 'Vasyl', 2),
(30, 1, 'Igor', 3),
(31, 0, 'Marta', 4),
(32, 1, 'Julia', 1),
(33, 1, 'Andrij', 2),
(34, 1, 'Oleg', 3),
(35, 0, 'Ivan', 4),
(36, 0, 'Ivan', 1);