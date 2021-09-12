# 반찬 프로젝트
![스크린샷 2021-04-19 오후 2 24 07](https://user-images.githubusercontent.com/69034766/115194006-d38d9580-a127-11eb-9ad3-9543e8f00335.png)
## 팀원
- FE/ Daisy Swing 
- BE /Robin Cooper

## 🖥 DEMO

![127081973-cd486e88-5fc3-40c5-b0e2-b77f40a51eba](https://user-images.githubusercontent.com/56783350/127082440-09e852a1-7188-46e9-a9e3-ce82a2caec63.gif)


## 🖥 Web Preview
<img width="885" alt="스크린샷 2021-07-07 오전 1 17 24" src="https://user-images.githubusercontent.com/56783350/124634250-18240400-dec1-11eb-8a90-3da70405686c.png">


## ⚒주요 기능 
- 상품 조회 
- 상품 슬라이더 기능
- 상품 더 보기 기능 
- 상품 재고 조회(재고 없을 시 구매 불가)
- 상품 클릭시 해당 상품의 디테일 모달창으로 이동

## 💫컴포넌트 구조

<img width="852" alt="스크린샷 2021-07-07 오전 1 15 36" src="https://user-images.githubusercontent.com/56783350/124634032-d7c48600-dec0-11eb-81f7-283a1f4a6f68.png">

## 🤹 스타일 컴포넌트 구조 

<img width="1058" alt="스크린샷 2021-07-07 오전 1 19 10" src="https://user-images.githubusercontent.com/56783350/124634469-56b9be80-dec1-11eb-8f9e-d5b4d3a357f9.png">
<img width="941" alt="스크린샷 2021-07-07 오전 1 18 55" src="https://user-images.githubusercontent.com/56783350/124634431-4dc8ed00-dec1-11eb-9229-5d18b0cdd1ab.png">

## Back-end

### ✨ Tech Stack
<img width="852"  alt="사용 기술" src="https://user-images.githubusercontent.com/45054467/132987835-1b5ac2b8-a442-4759-a532-c0ce8f39a2b9.png">

### ✨ API 설계 (Mock API)
[Mock API docs 바로가기](https://documenter.getpostman.com/view/8052286/TzJuBe2M)

### 💖 DB 설계(데이터 모델링)
<img width="852"  alt="데이터 모델링" src="https://user-images.githubusercontent.com/45054467/132850907-14ef3a3c-4848-472c-b732-6d56091636a2.png">

### ✨ 배포 쉘 스크립트 작성
```
echo "반찬가게 git 배포 쉘 입니다"

KEY_PATH="EC2 pem key 경로 입력"
AWS_PATH="EC2 퍼플릭 ip 주소 입력"

GIT_AWS_PATH="/home/ubuntu/sidedish"
GIT_LOCAL_PATH="/Users/yunhyeji/banchan/sidedish"

LOCAL_FE_BUILD_PATH="/Users/yunhyeji/banchan/sidedish/FE/banchan-project"
AWS_FE_BUILD_PATH="/home/ubuntu/sidedish/FE/banchan-project"

LOCAL_BE_BUILD_PATH="/Users/yunhyeji/banchan/sidedish/backend/sidedish"
AWS_BE_BUILD_PATH="/home/ubuntu/production"

NOHUP_FILE_PATH="/home/ubuntu/nohup/log.out"

echo "kill nohup 시작"
PID=$(ssh -i $KEY_PATH $AWS_PATH pgrep java)
ssh -i $KEY_PATH $AWS_PATH kill -9 $PID
echo "$PID process kill 완료"

echo "git pull 시작"
ssh -i $KEY_PATH $AWS_PATH git -C $GIT_AWS_PATH pull origin team17-dev
git -C $GIT_LOCAL_PATH pull origin team17-dev
echo "git pull 완료"

echo "local에서 node_modules 및 build 파일 지우기 시작"
rm -rf $LOCAL_FE_BUILD_PATH/node_modules/
rm -rf $LOCAL_FE_BUILD_PATH/build/
rm -rf $LOCAL_FE_BUILD_PATH/yarn.lock
echo "node_modules 및 build 파일 지우기 완료"

echo "yarn install 및 build 시작"
cd $LOCAL_FE_BUILD_PATH
yarn install
yarn build
echo "yarn install 및 build 완료"

echo "프론트 build 파일 보내기 시작"
scp -r -i $KEY_PATH $LOCAL_FE_BUILD_PATH/build $AWS_PATH:$AWS_FE_BUILD_PATH
echo "프론트 build 파일 보내기 완료"

echo "백엔드 build 및 파일 보내기 시작"
cd $LOCAL_BE_BUILD_PATH
./gradlew build jar
ssh -i $KEY_PATH $AWS_PATH rm -rf $AWS_BE_BUILD_PATH/sidedish-0.0.1-SNAPSHOT.jar
scp -i $KEY_PATH $LOCAL_BE_BUILD_PATH/build/libs/sidedish-0.0.1-SNAPSHOT.jar $AWS_PATH:$AWS_BE_BUILD_PATH
echo "백엔드 build 및 파일 보내기 완료"

echo "nohup 돌리기"
ssh -i $KEY_PATH $AWS_PATH "nohup java -jar $AWS_BE_BUILD_PATH/sidedish-0.0.1-SNAPSHOT.jar > $NOHUP_FILE_PATH &"
echo " 모든 배포 완료"
```
