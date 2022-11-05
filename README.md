# Focus_Media

사용된 AWS Stack :  EC2, 탄력적 IP, RDS, 로드 밸런서(ALB), Auto Scaling, Parameter Store

배포 설정
1. EC2 설정
    - ubuntu 기반의 인스턴스 생성
    - 키 페어를 발급 받기
    - 인스턴스 연결을 위해 로컬에 키페어 저장
    - ssh client를 위해 로컬에서 EC2 연결
    - EC2 내에서 깃헙 연결용 ssh key 생성
    - 깃헙과 연결
    - 보안그룹 설정
    
2. 탄력적 IP 설정
    - EC2에서 탄력적 IP 생성
    - 위에서 생성한 인스턴스에 생성된 탄력적 IP 고정
    - 이제 고정 퍼블릭 IP로 인스턴스 접근 가능
    
3. RDS 설정
    - RDS를 사용한 데이터 베이스 생성하기
    - MYSQL을 사용
    - 사용자 이름과 암호 설정
    - MYSQL WorkBench를 사용하여 RDS 엔드포인트로 연결
    
4. 로드 밸런서 설정
    - EC2의 과부화를 분산 시키기 위해 로드밸런서 설정
    - 로드 밸런스 그룹 설정
    - ALB 선택, 로드 밸런서 생성
    - 보안 그룹 설정
    - EC2 보안 그룹 설정에 로드 밸런서 포트 포함 시키기 혹은 로드 밸런서의 보안그룹을 포함 시키기
    
5. 오토 스켕일링 설정
    - 오토 스케일링 그룹 생성
    - 시작 템플릿으로 생성
        - 생성될 인스턴스의 종류와 가용영역을 설정, 최대 인스턴스 수와 최소 인스턴스 수 등 원하는 옵션 선택
    - 생성 해 놓은 로드 밸런스 그룹 적용
    - 동적 크리 조정 정책 설정
    
6. Parameter Store를 사용해 어플리케이션 내에서 정보 숨기기
    - build.gradle에 implementation 'org.springframework.cloud:spring-cloud-starter-aws-parameter-store-config' 추가
    - dependencyManagement {
	        imports {
		              mavenBom "org.springframework.cloud:spring-cloud-starter-parent:Hoxton.SR12"
	         }
       }
    - bootstrap.yml 파일을 resources 파일에 생성 및 필요한 정보 기입
    - application.properties에 민감한 정보를 주석 처리 하기 및 AWS Pramaeter 서비스에서 생성한 이름으로 정보 치환하기
    
7. 배포하기
    - 프리티어를 사용하기 때문에 부하를 줄이기 위해 직접 로컬에서 프로젝트 빌드
    - 깃헙 deploy 브랜치를 설정, 빌드된 프로젝트를 생성된 브랜치로 push
    - EC2 내에 원하는 경로 설정
    - EC2 에서 deploy 브랜치로 클론 받기
    
8. 서버 실행
    - sudo apt update 로 업데이트
    - sudo apt install openjdk-11-jre-headless EC2 내에 자바 설치
    - java -jar {자르 파일 이름}사용 하여 서버 실행
    - 서버 자체가 문제 없이 실행이 된다면 nohup을 사용하여 인스턴스가 종료되도 서버가 작동 하도록 설정하기
