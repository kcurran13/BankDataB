-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.25-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table bankdb.accounts: ~13 rows (approximately)
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` (`AccNo`, `ClearingNo`, `AccOwner`, `Balance`, `AccName`) VALUES
	('11223344', 3000, 1, 14229, 'Checking'),
	('12345678', 3000, 6, 105930, 'EONBILLPAY'),
	('22334455', 3000, 2, 3855, 'KalliChecking'),
	('23456789', 3000, 6, 90000, 'EONSALARY'),
	('33445566', 3000, 1, 26040, 'Savings'),
	('44556677', 3000, 4, 5029, 'Checking'),
	('55667788', 3000, 5, 102029, 'myAccount1'),
	('60250200', 3000, 2, 9500, 'KallisSaving'),
	('66778899', 3000, 5, 15599, 'myAccount2'),
	('77889900', 3000, 3, 38410, 'Kort'),
	('88990011', 3000, 4, 110042, 'Saving'),
	('99001122', 3000, 3, 555587, 'Spar');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;

-- Dumping data for table bankdb.plannedtransactions: ~4 rows (approximately)
/*!40000 ALTER TABLE `plannedtransactions` DISABLE KEYS */;
INSERT INTO `plannedtransactions` (`transID`, `Receiver`, `AccNo`, `TransAmount`, `Date`) VALUES
	(11, '11223344', '23456789', 10000, '2019-04-25 14:05:08'),
	(12, '11223344', '23456789', 10000, '2019-05-25 14:05:11'),
	(13, '11223344', '23456789', 10000, '2019-06-25 14:05:13'),
	(14, '11223344', '23456789', 10000, '2019-07-25 14:05:26');
/*!40000 ALTER TABLE `plannedtransactions` ENABLE KEYS */;

-- Dumping data for table bankdb.transactions: ~49 rows (approximately)
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` (`TransID`, `AccNo`, `Date`, `TransAmount`, `Receiver`, `Balance`) VALUES
	(92, '11223344', '2019-03-25 00:00:00', -123, '55667788', 7643),
	(93, '55667788', '2019-03-25 00:00:00', 123, '11223344', 102029),
	(94, '33445566', '2019-03-26 09:53:16', -150, '11223344', 28340),
	(95, '11223344', '2019-03-26 09:53:16', 150, '33445566', 7793),
	(96, '11223344', '2019-03-26 10:42:09', -123, '88990011', 7670),
	(97, '88990011', '2019-03-26 10:42:09', 123, '11223344', 108907),
	(98, '11223344', '2019-03-26 10:42:14', -135, '88990011', 7535),
	(99, '88990011', '2019-03-26 10:42:14', 135, '11223344', 109042),
	(100, '11223344', '2019-03-26 10:42:16', -1000, '88990011', 6535),
	(101, '88990011', '2019-03-26 10:42:16', 1000, '11223344', 110042),
	(102, '11223344', '2019-03-26 10:42:44', -100, '88990011', 6435),
	(103, '11223344', '2019-03-26 10:42:45', -100, '88990011', 6335),
	(104, '11223344', '2019-03-26 10:42:45', -100, '88990011', 6235),
	(105, '11223344', '2019-03-26 10:42:46', 100, '88990011', 6335),
	(106, '11223344', '2019-03-26 10:42:46', 100, '88990011', 6435),
	(107, '11223344', '2019-03-26 10:42:47', -100, '88990011', 6335),
	(111, '33445566', '2019-03-26 13:09:00', 150, '11223344', 28490),
	(113, '33445566', '2019-03-26 13:11:15', -1000, '99001122', 27490),
	(114, '99001122', '2019-03-26 13:11:15', 1000, '33445566', 555587),
	(115, '23456789', '2019-03-26 13:12:47', -10000, '11223344', 90000),
	(116, '11223344', '2019-03-26 13:12:47', 10000, '23456789', 14685),
	(117, '33445566', '2019-03-26 13:15:39', 1000, '99001122', 28490),
	(118, '99001122', '2019-03-26 13:15:39', -1000, '33445566', 554587),
	(119, '33445566', '2019-03-26 13:16:28', -1000, '99001122', 27490),
	(120, '99001122', '2019-03-26 13:16:28', 1000, '33445566', 555587),
	(121, '33445566', '2019-03-26 13:19:24', -100, '12345678', 27390),
	(122, '33445566', '2019-03-26 13:19:26', 100, '12345678', 27490),
	(123, '33445566', '2019-03-26 13:20:56', -150, '12345678', 27340),
	(124, '12345678', '2019-03-26 13:20:56', 150, '33445566', 105980),
	(125, '33445566', '2019-03-26 13:20:57', 150, '12345678', 27490),
	(126, '12345678', '2019-03-26 13:20:57', -150, '33445566', 105830),
	(127, '33445566', '2019-03-28 10:27:35', -350, '44556677', 27140),
	(128, '44556677', '2019-03-28 10:27:35', 350, '33445566', 5029),
	(129, '33445566', '2019-03-29 10:48:14', -1000, '77889900', 26140),
	(130, '77889900', '2019-03-29 10:48:14', 1000, '33445566', 38410),
	(131, '60250200', '2019-03-29 10:50:53', -500, '22334455', 9500),
	(132, '22334455', '2019-03-29 10:50:53', 500, '60250200', 3499),
	(133, '22334455', '2019-03-29 10:51:11', -100, '12345678', 3399),
	(134, '12345678', '2019-03-29 10:51:11', 100, '22334455', 105930),
	(135, '22334455', '2019-03-29 10:51:13', -100, '12345678', 3299),
	(136, '12345678', '2019-03-29 10:51:13', 100, '22334455', 106030),
	(137, '22334455', '2019-03-29 10:51:16', 100, '12345678', 3399),
	(138, '12345678', '2019-03-29 10:51:16', -100, '22334455', 105930),
	(139, '33445566', '2019-03-31 09:17:13', -100, '66778899', 26040),
	(140, '66778899', '2019-03-31 09:17:13', 100, '33445566', 15599),
	(141, '11223344', '2019-03-31 09:17:13', -456, '22334455', 14229),
	(142, '22334455', '2019-03-31 09:17:13', 456, '11223344', 3855);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;

-- Dumping data for table bankdb.users: ~4 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `FirstName`, `LastName`, `PersonNum`, `UserLogIn`, `UserPass`) VALUES
	(1, 'Kate', 'Curran Larsson', '123456789001', 'katetest', 'password'),
	(2, 'Kalli', 'Curran', '123456789002', 'kallitest', 'password'),
	(3, 'Spencer', 'Curran', '123456789003', 'spencertest', 'password'),
	(4, 'Timothy', 'Curran', '123456789004', 'timtest', 'password'),
	(5, 'Mary', 'Lewis-Curran', '123456789005', 'marytest', 'password'),
	(6, 'E.ON', 'BillsDepartment', '223344556677', 'na', 'na');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
