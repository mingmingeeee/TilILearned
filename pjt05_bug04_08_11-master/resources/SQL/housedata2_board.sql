CREATE DATABASE  IF NOT EXISTS `housedata2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `housedata2`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: housedata2
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  KEY `board_to_members_user_id_fk` (`user_id`),
  CONSTRAINT `board_to_members_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'admin','첫번째 글입니다.','안녕하세요.!',0,'2022-10-11 23:41:28'),(2,'Aram1234','2번째글','안녕하세요..!\r\n처음 글을 써봅니다.. 잘 부탁드려요..~',2,'2022-10-12 00:52:00'),(3,'borabora','가용자금 확인 및 대출 계획 세우기','가용자금 확인 및 대출 계획 세우기\r\n                                                 \r\n1. 주택 거래시 예상 비용 확인하기!\r\n재무 상태 파악하고 예산 계획 세우기\r\n\r\n2. 내 상황에 맞는 주거 대출 찾아보기\r\n\r\n[출처] [주택 매매] STEP1. 가용자금 확인 및 대출 계획 세우기|작성자 네이버 부동산',3,'2022-10-12 00:54:12'),(4,'goonghyuk','국민 주거안정 실현방안 발표','국토교통부가 8월 16일 첫 대규모 주택 공급 대책인 \'국민 주거 안정 실현방안\'을 발표했습니다.\r\n무주택 서민, 취약계층에 내 집 마련과 주거 상향을 위해 향후 5년 간 270만호를 공급한다고 합니다.',1,'2022-10-12 00:58:20'),(5,'goonghyuk','내집 마련 타이밍 사전청약 노려볼까?','\r\n국토교통부는 2022년 7월 15일 부터  남양주 왕숙·왕숙2, 고양 창릉, 화성 태안3, 평택 고덕에서  총 4.8천호 규모의 공공 분양주택 사전청약을 \r\n시행한다고 발표했습니다. \r\n\r\n사전청약 지구별 공급물량에 대해 함께 살펴보겠습니다.\r\n\r\n사전청약이란?\r\n공공택지에서 공급되는 분양주택의 공급 시기를 초기화하는 제도 \r\n\r\n지구지정 → 지구계획 승인 → 사전청약 → 본청약(공공주택사업) → 주택착공 → 사업승인\r\n\r\n\r\n공급물량이\r\n어떻게 되나요?\r\n<2022년 7월 공공 사전청약 지구>\r\n구분 / 대상지 및 공급물량(호수) \r\n3기 신도시(3,221호) : 남양주왕숙(1,398호), 남양주왕숙2(429호), 고양창릉(1,394호) \r\n2기 신도시 등(1,542호) : 화성태안3(632호), 평택고덕(910호) \r\n [출처] https://naver.me/5THKIXQw',4,'2022-10-12 01:00:05'),(6,'Hyeeun','홈네트워크 기기 해킹 예방법!','최근 아파트에 설치된 스마트홈 기기인 월패드 해킹으로 인해 사생활 유출 사고가 크게 발생했는데요.\r\n\r\n홈네트워크 기기 보안수칙 \r\n[이용자 보안수칙]\r\n\r\n① 기기는 반드시 암호를 설정하고, 1234, ABCD 등 유추하기 쉬운 암호 사용하지 않기\r\n\r\n② 기기는 주기적으로 최신 보안 업데이트 하기 (매뉴얼 또는 제조 기업문의 등)\r\n\r\n③ 카메라 기능 미이용시 카메라 렌즈 가리기\r\n[출처] https://naver.me/5kX8dmYq',1,'2022-10-12 01:01:46'),(7,'moooon','청년월세지원','코로나19 장기화 등으로 인한 청년층의 주거비 부담 경감을 위한  \"청년 월세 한시 특별지원\" 사업 (이하 \'청년 월세지원 사업\')이 \r\n2022년 8월부터 본격 시행된다고 합니다.\r\n\r\n[출처] https://naver.me/GhPuohpg',2,'2022-10-12 01:03:18'),(8,'moooon','다들 집은 구하셨나요.. ㅠㅠ','집구하기..',5,'2022-10-12 01:03:32'),(9,'haluday1','집 마련하고 싶습니다..!','저도 전망 좋고, 가격도 싼 집에서 살고 싶습니다....',0,'2022-10-12 01:04:44'),(10,'saeron','빅스텝 영향... 서울 아파트값 하락','서울 아파트값이 하락 전환됐다. 지난주 기준금리를 한 번에 0.5%P 인상하는 빅스텝이 단행되면서 주택 매수 심리가 더 얼어붙는 분위기다. 강남과 용산이 소폭 올라 상승세가 유지됐지만, 외곽지역인 노원, 도봉, 강서, 구로 등은 하락폭이 더 확대됐다. 서울 재건축도 0.05% 하락해 2020년 5월(5.22일, -0.06%) 이후 주간 기준 가장 많이 떨어졌다. 신도시와 경기ㆍ인천도 지난주보다 하락폭이 더 커졌다. ㅠㅠ\r\n[출처] https://me2.do/5jY51m2E',6,'2022-10-12 01:06:40'),(11,'admin','안녕하세요 관리자 입니다.','만나서 반갑습니다.',2,'2022-10-12 01:07:33'),(12,'Ryuho','처음으로 게시판에 글 작성합니다','잘 부탁드려요..~',1,'2022-10-12 05:34:22'),(13,'Ryuho','구해줘! 홈즈~','방송중 일(밤) 10시45분 - \'뭘 좋아할지 몰라 이집저집 준비 해봤어\' 바쁜 현대인들의 집찾기를 위해 직접 나선 스타들! 그들의 리얼한 발품중개배틀 <구해줘! 홈즈> 많은 관심 부탁드려요~',0,'2022-10-12 05:35:36'),(14,'narajang3456','언양아파트매매합니다','아파트매매합니다\r\n(1202호 24평)\r\n동향이고 탁트여 전망도 좋습니다\r\n연락주세요!!!',4,'2022-10-12 05:37:02'),(15,'ssafy2','안녕하세요 ^^','반가워요 앞으로 잘 부탁드립니다.~~',0,'2022-10-12 05:38:25'),(16,'hangil','약국 임대합니다~','학원/사무실/카페 가능\r\n전월세 조정 가능',0,'2022-10-12 05:39:11'),(17,'wonsik','부산 그린코아 집 어떤가요?','시설이나, 주변 환경 좋은가요?',4,'2022-10-12 05:52:16'),(18,'wonsik','새로운 글','새로운 글',0,'2022-10-12 05:52:57'),(19,'narajang3456','관심지역','관심지역 추가하는 법 알려주세요!',0,'2022-10-12 05:53:35'),(20,'narajang3456','회원정보 수정 페이지 어떻게 찾나요?','제곧내',0,'2022-10-12 05:54:07'),(21,'gijuhan1997','부동산 시장 곧 좋아지려나요??','앞으로의 부동산 전망이 궁금합니다.!!!',0,'2022-10-12 05:56:15'),(22,'gijuhan1997','게시판은 아무글이나 쓰면 되나요?','게시글...',0,'2022-10-12 05:56:34'),(23,'borabora','집 구하려는 데 부산에 추천할 만한 곳이 있나요?','추천해주시면 감사하겠습니다!!!!!!!',0,'2022-10-12 06:10:12'),(24,'borabora','집 관리 다들 어떻게 하시나요?','알려주세요!',0,'2022-10-12 06:11:18');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-12 17:11:01
