CREATE TABLE board (
                       boardNo INT (10) AUTO_INCREMENT NOT NULL,
                       title VARCHAR2(100) NOT NULL,
                       author VARCHAR2(20) NOT NULL,
                       contents VARCHAR2(5000) NOT NULL,
                       views VARCHAR2(10) NOT NULL DEFAULT 0,
                       useYN VARCHAR2(10) NOT NULL DEFAULT 1,
                       createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       updateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       PRIMARY KEY (boardNo)
);

INSERT INTO board(TITLE, AUTHOR, CONTENTS)
VALUES
('[공지] 2021년 설선물 신청 안내', '이진호', 'GS ITM 구성원 여러분들께,



        2021년 설을 맞이하여 명절선물을 준비하였습니다.

첨부드린 선물목록을 확인하시고 반드시 기간내에 e-HR에서 신청을 완료하여 주시기를 부탁 드립니다.'),
('[인사/안내] 2020년 귀속 연말정산 FAQ 안내', '정진호', 'GSITM 구성원들께,

​

​

​2020년 귀속 근로소득 연말정산과 관련하여, 많이 질문주셨던 사항을 정리하여 아래와 같이 공유드립니다.'),
('[교육공지] 온라인교육 2월 수강신청 안내', '김진호', '​​​​GS ITM 구성원 여러분들께,



2월 온라인 교육 수강신청이 1월27일(수)에 마감될 예정입니다.

아래 내용을 참고하셔서 교육훈련신청서를 작성하시고 마감일까지 결재를 마쳐주시기를 부탁 드립니다.



마감일이 지나고 올라온 경우, 3월 교육 신청으로 처리될 예정이오니

반드시 마감일까지 신청해주시기를 부탁 드립니다.'),
('[QA팀] 확정CP 적용 공지', '정진호', '안녕하십니까 QA팀입니다.





사업관리시스템에 프로젝트의 기준을 명확하게 관리하기 위하여 CP구분에 "확정CP"를 추가하여 적용합니다.





1. 적용 목적 : 프로젝트 최초 승인 기준을 명확히 관리하고, 프로젝트 평가시 기준으로 하고자 함.





2. 적용 시점 : 2021-01-18 (월) 18:00      '),
('[인사/안내] 2020년 귀속 연말정산 진행 안내', '최진호', 'GSITM 구성원분들께,



2020년 귀속 근로소득 연말정산을 아래와 같이 실시하오니,관련 서류를 기한 내에 제출 부탁드립니다.'),
('[사내공모] 서비스사업센터 에너지고객사업팀 인프라운영 업무 (21/01/13~21/01/24)', '유진호', '서비스사업센터 에너지고객사업팀에서 GS에너지 인프라운영 업무 사내 공모를 하오니 구성원 여러분께서는 모집 내역을 참고하시어 지원하여 주시기 바랍니다.


​');
